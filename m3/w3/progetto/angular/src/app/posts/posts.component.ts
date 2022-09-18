import { Component, OnInit } from '@angular/core';
import { Post } from '../class/post';
import { User } from '../class/user';
import { AuthService } from '../services/auth.service';
import { PostService } from '../services/post.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  currentPost: Post = new Post('', '');
  currentUser!: User | null
  formAction: string = 'create'

  apiUrl: string = 'http://localhost:3000/post'

  constructor(private postSvc: PostService, private authSvc: AuthService) { }

  posts: Post[] = [];
  likesNum: number = 0;

  ngOnInit(): void {
    this.currentUser = this.authSvc.getLoggedUser()
    this.postSvc.getAllPosts().subscribe(
      {
        next: res => {
          this.posts = res;
          this.likesNum = this.posts.filter(p => p.like == true).length;
        },
        error: error => console.log(error)
      }
    )
  }

  deletePost(post: Post): void {
    this.postSvc.deletePost(post).subscribe(res => {
      let index: number = this.posts.findIndex(p => p.id === post.id)
      this.posts.splice(index, 1);
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: 'Post Deleated',
        showConfirmButton: false,
        timer: 2000
      })
    })
  }

  like(post: Post): void {
    post.like = !post.like;
    this.postSvc.editPost(post).subscribe(res => {
      let index: number = this.posts.findIndex(p => p.id === post.id)
      this.posts.splice(index, 1, post);
      this.likesNum = this.posts.filter(p => p.like == true).length;
    })
  }

  addPost(post: Post): void {
    post.userId = this.currentUser?.id
    this.postSvc.addPost(post).subscribe(res => {
      this.posts.push(res)
      this.currentPost = new Post('', '')
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
    console.log(post);
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
  }

}
