import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthResponse } from 'src/app/models/auth-response';
import { FileHandle } from 'src/app/models/file-model';
import { Post } from 'src/app/models/post';
import { PostDto } from 'src/app/models/postDto';

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent {

  constructor() { }

  @Input() currentPost!: PostDto
  @Input() currentUser!: AuthResponse
  @Input() formAction!: string

  @Output() onNewPostCreated = new EventEmitter();
  @Output() onEditPost = new EventEmitter()

  editPost(): void {
    this.onEditPost.emit(this.currentPost)
    this.refreshInput()
  }

  sendPost(): void {
    this.onNewPostCreated.emit(this.currentPost);
    this.refreshInput()
  }

  refreshInput(): void {
    this.currentPost = {
      title: "",
      text: "",
    }
  }


  onFileSelected(event: Event): void {
    const target = event.target as HTMLInputElement;
    if (target.files) {
      const file = target.files[0];

      this.currentPost.image = file

    }
  }

}
