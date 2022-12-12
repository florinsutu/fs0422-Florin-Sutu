import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { AuthResponse } from '../models/auth-response';
import { User } from '../models/user';
import { UserUpdate } from '../models/user-update';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  loggedUser!: AuthResponse

  currentUser: User = new User();

  isOwner() {
    if (this.currentUser && this.loggedUser)
      return this.currentUser.id == this.loggedUser.id;
    else return false;
  }

  constructor(
    private userSvc: UserService,
    private authSvc: AuthService,
    private route: ActivatedRoute,
    private router: Router,
    private imgSvc: ImageProcessingService,
  ) { }

  ngOnInit(): void {
    this.loggedUser = this.authSvc.getAccessData()
    let currentUserId = this.route.snapshot.paramMap.get('id')
    if (currentUserId) {
      this.userSvc.getUserById(currentUserId)
      .pipe(
        map((u: User, i) => this.imgSvc.createImages(u))
      )
        .subscribe({
          next: res => this.currentUser = res as User,
          error: () => (console.log("user not found")),
        });
    }

  }

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files) {
      const file = target.files[0];
      this.currentUser.image = file
    }
  }

  prepareFormData(user: User): FormData {
    const formData = new FormData();
    if (user.image)
      formData.append(
        'imageFile',
        user.image,
        user.image?.name
      )
    return formData
  }

  editProfilePic() {
    console.log(this.currentUser)
    this.userSvc.editProfilePic(this.currentUser.id, this.prepareFormData(this.currentUser)).subscribe(user => {
      this.currentUser = user;
    })
  }

  editProfile() {

    let userUpdate: UserUpdate = {

      id: this.currentUser.id,
      name: this.currentUser.name,
      lastname: this.currentUser.lastname,
      username: this.currentUser.username,
      password: this.currentUser.password,
      email: this.currentUser.email,
      descrpition: this.currentUser.description,
      isPrivate: this.currentUser.isPrivate
    }

    this.userSvc.editUser(userUpdate).subscribe(user => {
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
            text: 'User has been deleted',
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
