import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { AuthResponse } from '../models/auth-response';
import { FileHandle } from '../models/file-model';
import { Post } from '../models/post';
import { User } from '../models/user';
import { UserUpdate } from '../models/user-update';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { PostsComponent } from '../posts/posts.component';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { UsersComponent } from '../users/users.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  loggedUser!: AuthResponse;
  imgPreview!: FileHandle | null;
  currentUser: User = new User();
  userPosts: Post[] = [];
  showForm: boolean = false;
  userFollowers: User[] = [];
  userFollowed: User[] = [];
  isFollowing: boolean = false;

  isPostClicked: boolean = false;
  clickedPostId: number = 0;

  openPostDialog(post: Post) {
    let dialogRef = this.dialog.open(PostsComponent, { data: post });
  }

  openFollowersDialog() {
    if (this.userFollowers.length > 0) {
      let dialogRef = this.dialog.open(UsersComponent, {
        data: this.userFollowers,
      });
    }
  }

  openFollowedDialog() {
    if (this.userFollowed.length > 0) {
      let dialogRef = this.dialog.open(UsersComponent, {
        data: this.userFollowed,
      });
    }
  }

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
    private sanitizer: DomSanitizer,
    private postSvc: PostService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loggedUser = this.authSvc.getAccessData();

    this.route.paramMap.subscribe({
      next: (res) => {
        let currentUserId = res.get('id');

        if (currentUserId) {
          this.userSvc
            .getUserById(currentUserId)
            .pipe(map((u: User, i) => this.imgSvc.createImages(u)))
            .subscribe({
              next: (res) => {
                this.currentUser = res as User;

                // Ricavo la lista di followers

                this.userSvc
                  .getFollowers(this.currentUser)
                  .pipe(
                    map((p: User[], i) =>
                      p.map((post: User) => this.imgSvc.createImages(post))
                    )
                  )
                  .subscribe({
                    next: (res) => {
                      this.userFollowers = res as User[];

                      for (let user of this.userFollowers) {
                        if (user.id == this.loggedUser.id)
                          this.isFollowing = true;
                      }
                    },
                    error: (error) => console.log(error),
                  });

                // Ricavo la lista di followed

                this.userSvc
                  .getFollowed(this.currentUser)
                  .pipe(
                    map((p: User[], i) =>
                      p.map((post: User) => this.imgSvc.createImages(post))
                    )
                  )
                  .subscribe({
                    next: (res) => (this.userFollowed = res as User[]),
                    error: (error) => console.log(error),
                  });

                // Ricavo la lista di post dell' utente

                this.postSvc
                  .getAllPostsByAuthorId(this.currentUser.id)
                  .pipe(
                    map((p: Post[], i) =>
                      p.map((post: Post) => this.imgSvc.createImages(post))
                    )
                  )
                  .subscribe({
                    next: (res) => (this.userPosts = res as Post[]),
                    error: (error) => console.log(error),
                  });
              },
              error: () => console.log('user not found'),
            });
        }
      },
      error: () => console.log('user not found'),
    });
  }

  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files) {
      const file = target.files[0];

      this.imgPreview = {
        file: file,
        url: this.sanitizer.bypassSecurityTrustUrl(
          window.URL.createObjectURL(file)
        ),
      };
    }
  }

  prepareFormData(user: User): FormData {
    const formData = new FormData();
    if (user.image) formData.append('imageFile', user.image, user.image?.name);
    return formData;
  }

  editProfilePic() {
    if (this.imgPreview) {
      this.currentUser.image = this.imgPreview.file;
      this.userSvc
        .editProfilePic(
          this.currentUser.id,
          this.prepareFormData(this.currentUser)
        )
        .pipe(map((u: User, i) => this.imgSvc.createImages(u)))
        .subscribe((user) => {
          this.currentUser = user as User;
          this.imgPreview = null;
          Swal.fire({
            title: 'Your Profile Picture has been updated',
            icon: 'success',
            confirmButtonText: 'Ok',
          });
        });
    } else {
      Swal.fire({
        title: 'Error!',
        text: 'You have to upload an Image before updating',
        icon: 'error',
        confirmButtonText: 'Ok',
      });
    }
  }

  editProfile() {
    let userUpdate: UserUpdate = {
      id: this.currentUser.id,
      name: this.currentUser.name,
      lastname: this.currentUser.lastname,
      username: this.currentUser.username,
      password: this.currentUser.password,
      email: this.currentUser.email,
      description: this.currentUser.description,
      isPrivate: this.currentUser.isPrivate,
    };

    this.userSvc.editUser(userUpdate).subscribe((user) => {
      this.currentUser = user;
      Swal.fire({
        title: 'Your Profile has been updated',
        text: 'You will be redirected to login',
        icon: 'success',
        confirmButtonText: 'Ok',
      }).then(() => {
        this.authSvc.logOut();
        this.router.navigate(['/']);
      });
    });
  }

  deleteProfile() {
    Swal.fire({
      title: 'warning!',
      text: "You won't be able to revert this!",
      icon: 'warning',
      cancelButtonText: 'Cancel',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!',
    }).then((result) => {
      if (result.value) {
        this.userSvc.deleteUser(this.currentUser).subscribe((user) => {
          Swal.fire({
            title: 'Error!',
            text: 'User has been deleted',
            icon: 'success',
            confirmButtonText: 'Ok',
          }).then(() => {
            this.authSvc.logOut();
            this.router.navigate(['/']);
          });
        });
      } else {
        Swal.fire({
          title: 'Error!',
          text: 'User not deleted',
          icon: 'error',
          confirmButtonText: 'Ok',
        });
      }
    });
  }

  toggleUpdateForm() {
    this.showForm = !this.showForm;
  }

  // Follow

  followUser() {
    this.userSvc
      .follow(this.currentUser.id, this.loggedUser.id)
      .subscribe((res) => {
        if (!this.isFollowing) {
          this.userFollowers.push(res);
          this.isFollowing = true;
          Swal.fire({
            position: 'top-end',
            icon: 'success',
            title: 'Now Following',
            showConfirmButton: false,
            timer: 2000,
          });
        } else {
          let index = this.userFollowers.findIndex(
            (u: User) => u.id == this.loggedUser.id
          );
          this.userFollowers.splice(index, 1);
          this.isFollowing = false;
          Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'Unfollowing',
            showConfirmButton: false,
            timer: 2000,
          });
        }
      });
  }
}
