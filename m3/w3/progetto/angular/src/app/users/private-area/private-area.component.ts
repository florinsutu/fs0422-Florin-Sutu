import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-private-area',
  templateUrl: './private-area.component.html',
  styleUrls: ['./private-area.component.scss']
})
export class PrivateAreaComponent implements OnInit {

  currentUser!: User

  constructor(
    private userSvc: UserService,
    private authSvc: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.currentUser = this.authSvc.getLoggedUser()
  }

  editProfile() {
    this.userSvc.editUser(this.currentUser).subscribe(user => {
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

