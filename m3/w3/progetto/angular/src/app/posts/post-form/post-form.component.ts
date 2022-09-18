import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from 'src/app/class/post';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {

  editPost(): void {
    this.onEditPost.emit(this.currentPost)
    this.refreshInput()
  }

  sendPost(): void {
   this.onNewPostCreated.emit(this.currentPost);
   this.refreshInput()
  }

  refreshInput(): void {
    this.currentPost = new Post('','')
  }

  constructor() { }

  ngOnInit(): void {
  }

  @Output() onNewPostCreated = new EventEmitter();

  @Output() onEditPost= new EventEmitter()

  @Input() currentPost!: Post

}
