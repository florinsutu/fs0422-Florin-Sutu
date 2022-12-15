import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { map } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { AuthResponse } from '../models/auth-response';
import { Comment } from 'src/app/models/comment';
import { CommentDto } from '../models/commentDto';
import { Post } from '../models/post';
import { PostDto } from '../models/postDto';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { CommentService } from '../services/comment.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { PostService } from '../services/post.service';
import { UserService } from '../services/user.service';
import { PostCommentsComponent } from './post-comments/post-comments.component';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  currentPost: Post = new Post();
  loggedUser!: AuthResponse
  formAction: string = 'create'
  userList: User[] = [];
  showForm: boolean = false;
  posts: Post[] = [];


  constructor(
    private postSvc: PostService,
    private authSvc: AuthService,
    private userSvc: UserService,
    private commentSvc: CommentService,
    private imgSvc: ImageProcessingService,
  ) { }

  checkLog(): boolean {
    return this.authSvc.isUserLogged()
  }

  ngOnInit(): void {

    this.userSvc.getAllUsers()
      .pipe(
        map((p: User[], i) => p.map((post: User) => this.imgSvc.createImages(post)))
      )
      .subscribe(
        {
          next: res => this.userList = res as User[],
          error: error => console.log(error)
        }
      )

    if (this.checkLog())
      this.loggedUser = this.authSvc.getAccessData()

    this.postSvc.getAllPosts()
      .pipe(
        map((p: Post[], i) => p.map((post: Post) => this.imgSvc.createImages(post)))
      )
      .subscribe(
        {
          next: res => this.posts = res as Post[],
          error: error => console.log(error)
        }
      )

  }

  //-------------- POSTS METHODS --------------------

  deletePost(post: Post): void {

    Swal.fire({
      title: 'warning!',
      text: "You won't be able to revert this!",
      icon: 'warning',
      cancelButtonText: 'Cancel',
      showCancelButton: true,
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.value) {
        this.postSvc.deletePost(post).subscribe(
          {
            next: res => {
              let index: number = this.posts.findIndex(p => p.id === post.id)
              this.posts.splice(index, 1);
              Swal.fire({
                position: 'top-end',
                icon: 'success',
                title: 'Post Deleated',
                showConfirmButton: false,
                timer: 2000
              })
            },
            error: error => console.log(error)
          }
        )
      } else {
        Swal.fire({
          text: 'Post not deleted',
          icon: 'error',
          confirmButtonText: 'Ok'
        })
      }
    });

  }

  addPost(post: PostDto): void {

    post.authorId = this.loggedUser?.id
    this.postSvc.addPost(this.prepareFormDate(post)).pipe(
      map((u: Post, i) => this.imgSvc.createImages(u))
    ).subscribe(res => {
      this.posts.push(res as Post)
      this.currentPost = new Post()
      this.showForm = false;
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'New Post Created',
        showConfirmButton: false,
        timer: 2000
      })
    })
  }

  editPost(post: Post) {
    this.postSvc.editPost(post).subscribe(res => {
      let index = this.posts.findIndex((todo: Post) => todo.id == res.id)
      this.posts.splice(index, 1, post)
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Post Edited',
        showConfirmButton: false,
        timer: 2000
      })
    })
  }

  addToEdit(todo: Post): void {
    todo = Object.assign({}, todo)
    this.currentPost = todo
    this.formAction = 'edit'
    this.showForm = true;
  }

  prepareFormDate(post: PostDto): FormData {

    const formData = new FormData();
    formData.append(
      'post',
      new Blob([JSON.stringify(post)], { type: 'application/json' })
    );
    if (post.image)
      formData.append(
        'imageFile',
        post.image,
        post.image?.name
      )
    return formData
  }

  toggleCreatePost() {
    this.formAction = 'create'
    this.showForm = !this.showForm;
  }

  likePost(postId: number) {
    this.postSvc.likePost(postId + "", this.loggedUser.id).subscribe(
      res => {
        let index = this.posts.findIndex((p: Post) => p.id == res.id)
        this.posts[index].likes = res.likes
      }

    )
  }

  //----------- COMMENTS METHODS ----------------------------

  showComments: boolean = false;
  newComment: Comment = new Comment();

  @ViewChild(PostCommentsComponent) child!: PostCommentsComponent;

  toogleOpenComments() {
    this.showComments = !this.showComments;
  }

  openComments(post: Post) {
    this.toogleOpenComments()
    this.currentPost = post;
  }

  sendComment(comment: CommentDto) {

    this.commentSvc.addComment(comment)
      .subscribe(res => {

        if(this.currentPost.id) // per disambiguare se il commento arriva dal child PostComments o no
          this.child.updateComments(res)

        Swal.fire({
          position: 'top-end',
          icon: 'success',
          title: 'Comment sended',
          showConfirmButton: false,
          timer: 2000
        })
      })
  }

}
