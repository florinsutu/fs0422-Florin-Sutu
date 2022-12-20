import { BreakpointObserver } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { AuthResponse } from '../models/auth-response';
import { Message } from '../models/message';
import { MessageDto } from '../models/messageDto';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { MessageService } from '../services/message.service';
import { UserService } from '../services/user.service';

type Chat = {
  senderId: number;
  receiverId: number;
};

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
})
export class ChatComponent implements OnInit {
  loggedUser!: AuthResponse;
  textedUser: User = new User();
  messageList: Message[] = [];
  currentMessage: Message = new Message();
  userList: User[] = [];

  constructor(
    private authSvc: AuthService,
    private userSvc: UserService,
    private route: ActivatedRoute,
    private messageSvc: MessageService,
    private imgSvc: ImageProcessingService,
    private observer: BreakpointObserver,
    private router: Router
  ) {}

  checkLog(): boolean {
    return this.authSvc.isUserLogged();
  }

  ngOnInit(): void {
    if (this.checkLog()) {
      this.loggedUser = this.authSvc.getAccessData();
    }

    this.userSvc
      .getTextedUsers(this.loggedUser.id)
      .pipe(
        map((p: User[], i) =>
          p.map((post: User) => this.imgSvc.createImages(post))
        )
      )
      .subscribe({
        next: (res) => (this.userList = res as User[]),
        error: (error) => console.log(error),
      });
  }

  // View Management

  mode!: string;
  showButton!: boolean;
  showSidenav!: boolean;

  ngAfterViewInit() {
    this.observer.observe(['(max-width: 768px)']).subscribe((res) => {
      if (res.matches) {
        this.mode = 'mobile';

        if (this.router.url != '/site/chat') {
          this.showSidenav = false;
          this.showButton = true;
        } else {
          this.showSidenav = true;
          this.showButton = false;
        }
      } else {
        this.mode = 'desktop';
        this.showButton = false;
        this.showSidenav = true;
      }
    });
  }

  show() {
    this.showSidenav = true;
    this.showButton = false;
  }

  navigate() {
    if (this.mode == 'mobile') {
      this.showSidenav = false;
      this.showButton = true;
    }
  }

  // Search Users

  searched!: string;
  searchedUsers: User[] = [];

  filterOptionUser(searched: string) {
    if (searched.length !== 0) {
      let search: string = searched.toLowerCase();
      this.searchedUsers = this.userList.filter((user) =>
        user.username.toLowerCase().includes(search)
      );
    } else {
      this.searchedUsers = [];
    }
  }

  reset() {
    this.searched = '';
    this.searchedUsers = [];
  }
}
