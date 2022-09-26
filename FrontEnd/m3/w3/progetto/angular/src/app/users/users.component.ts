import { Component, OnInit } from '@angular/core';
import { User } from '../class/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  constructor(private userSvc: UserService) { }

  ngOnInit(): void {
    this.userSvc.getAllUsers().subscribe(users => {
    this.users = users ;
    })
  }

  deleteUser(user: User): void {
    this.userSvc.deleteUser(user).subscribe(res => {
      this.users = this.users.filter((u: User) => u.id != user.id )

    });
  }

  users: User[] = [];
}
