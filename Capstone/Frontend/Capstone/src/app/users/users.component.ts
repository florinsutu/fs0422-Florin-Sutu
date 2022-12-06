import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { User } from '../models/user';
import { ImageProcessingService } from '../services/image-processing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  constructor(private userSvc: UserService, private imgSvc: ImageProcessingService) { }

  ngOnInit(): void {
    this.userSvc.getAllUsers()
    .pipe(
      map((p: User[], i) => p.map((post: User) => this.imgSvc.createImages(post)))
      )
        .subscribe(
          {
            next: res => this.users = res as User[],
            error: error => console.log(error)
          }
        )
  }

  deleteUser(user: User): void {
    this.userSvc.deleteUser(user).subscribe(res => {
      this.users = this.users.filter((u: User) => u.id != user.id )

    });
  }

  users: User[] = [];
}
