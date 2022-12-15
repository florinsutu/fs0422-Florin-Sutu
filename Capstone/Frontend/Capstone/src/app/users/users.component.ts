import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { AuthResponse } from '../models/auth-response';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {
  constructor(
    private authSvc: AuthService,
    private userSvc: UserService,
    private imgSvc: ImageProcessingService
  ) { }

  users: User[] = [];
  loggedUser!: AuthResponse;

  ngOnInit(): void {

    if (this.checkLog()) {
      this.loggedUser = this.authSvc.getAccessData()
    }

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

  checkLog(): boolean {
    return this.authSvc.isUserLogged()
  }

  deleteUser(user: User): void {
    this.userSvc.deleteUser(user).subscribe(res => {
      this.users = this.users.filter((u: User) => u.id != user.id)

    });
  }

  // Search Users

  searched!:string
  searchedUsers: User[] = []

  filterOptionUser(searched:string){
    if(searched.length !== 0){
    let search:string = searched.toLowerCase()
   this.searchedUsers = this.users.filter(user => user.username.toLowerCase().includes(search))
  }else{
    this.searchedUsers=[]
  }
}


}
