import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthResponse } from '../models/auth-response';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  loggedUser!: AuthResponse

  currentUser!: User;

  constructor(
    private userSvc: UserService,
    private authSvc: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.authSvc.getAccessData()
    this.userSvc.getUserById(String(this.loggedUser.id)).subscribe({
    next: (res) => {
      this.currentUser = res;
    },
    error: () => (console.log("user not found")),
  });
  }

  editProfile() {
    this.userSvc.editUser(this.currentUser).subscribe(user => {
      console.log(this.currentUser)
      this.currentUser = user
      Swal.fire({
        title: 'Your Profile has been updated',
        text: 'You will be redirected to login',
        icon: 'success',
        confirmButtonText: 'Ok'
      }).then(() => {
        this.authSvc.logOut()
        this.router.navigate(['/'])
      })
    })

  }

  deleteProfile() {
    Swal.fire({
      title: 'warning!',
      text: "You won't be able to revert this!",
      icon: 'warning',
      cancelButtonText: 'Cancel',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.userSvc.deleteUser(this.currentUser).subscribe(user => {
          Swal.fire({
            title: 'Error!',
            text: user.name + 'has been deleted',
            icon: 'success',
            confirmButtonText: 'Ok'
          }).then(() => {
            this.authSvc.logOut()
            this.router.navigate(['/'])
          })
        })
      } else {
        Swal.fire({
          title: 'Error!',
          text: 'User not deleted',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      }
    });
  }
}
