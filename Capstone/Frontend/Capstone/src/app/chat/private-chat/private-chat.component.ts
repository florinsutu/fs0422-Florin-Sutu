import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';
import { AuthResponse } from 'src/app/models/auth-response';
import { Message } from 'src/app/models/message';
import { MessageDto } from 'src/app/models/messageDto';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/user.service';
import { formatDistance, parseISO} from 'date-fns';
import * as moment from 'moment';

@Component({
  selector: 'app-private-chat',
  templateUrl: './private-chat.component.html',
  styleUrls: ['./private-chat.component.scss'],
})
export class PrivateChatComponent implements OnInit {
  constructor(
    private authSvc: AuthService,
    private userSvc: UserService,
    private route: ActivatedRoute,
    private messageSvc: MessageService,
    private imgSvc: ImageProcessingService
  ) {}


  loggedUser!: AuthResponse;
  textedUser: User = new User();
  messageList: Message[] = [];
  currentMessage: Message = new Message();

  ngOnInit(): void {
    if (this.checkLog()) {
      this.loggedUser = this.authSvc.getAccessData();

      this.route.paramMap.subscribe({
        next: (res) => {
          let textedUserId = res.get('id');

          if (textedUserId) {
            this.userSvc
              .getUserById(textedUserId)
              .pipe(map((u: User, i) => this.imgSvc.createImages(u)))
              .subscribe((res) => (this.textedUser = res as User));

            this.messageSvc
              .getAllMessagesOf(this.loggedUser.id + '', textedUserId)
              .subscribe((res) => (this.messageList = res));
          }
        },
      });
    }
  }



  sendMessage() {
    let message: MessageDto = {
      text: this.currentMessage.text,
      senderId: this.loggedUser.id,
      receiverId: this.textedUser.id,
    };

    this.messageSvc.addMessage(message).subscribe((res) => {
      this.messageList.push(res);
      this.currentMessage = new Message();
    });
  }

  checkLog(): boolean {
    return this.authSvc.isUserLogged();
  }

  assignRole(id: number): boolean {
    return this.textedUser.id == id;
  }

  calculateTime(date: any){
/*     return formatDistance(parseISO(date), Date.now(), {addSuffix: true}) */
return moment(date).format('LT');
  }
}
