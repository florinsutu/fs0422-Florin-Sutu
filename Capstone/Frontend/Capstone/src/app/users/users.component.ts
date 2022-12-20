import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import { AuthResponse } from '../models/auth-response';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss'],
})
export class UsersComponent implements OnInit {
  constructor(
    private authSvc: AuthService,
    private userSvc: UserService,
    private imgSvc: ImageProcessingService,
    @Inject(MAT_DIALOG_DATA) public data: User[]
  ) {}

  users: User[] = [];
  loggedUser!: AuthResponse;

  mode: string = 'explore';

  ngOnInit(): void {
    if (this.checkLog()) {
      this.loggedUser = this.authSvc.getAccessData();
    }

    if (this.data.length > 0) {
      this.mode = 'followList'
      this.users = this.data;
      this.searchedUsers = this.users;
      console.log(this.data);
    } else {
      this.userSvc
        .getAllUsers()
        .pipe(
          map((p: User[], i) =>
            p.map((post: User) => this.imgSvc.createImages(post))
          )
        )
        .subscribe({
          next: (res) => {
            this.users = res as User[];
            this.searchedUsers = this.users;
          },
          error: (error) => console.log(error),
        });
    }
  }

  checkLog(): boolean {
    return this.authSvc.isUserLogged();
  }

  deleteUser(user: User): void {
    this.userSvc.deleteUser(user).subscribe((res) => {
      this.users = this.users.filter((u: User) => u.id != user.id);
    });
  }

  // Search Users

  searched!: string;
  searchedUsers!: User[];

  filterOptionUser(searched: string) {
    if (searched.length !== 0) {
      let search: string = searched.toLowerCase();
      this.searchedUsers = this.users.filter((user) =>
        user.username.toLowerCase().includes(search)
      );
    } else {
      this.searchedUsers = this.users;
    }
  }
}
