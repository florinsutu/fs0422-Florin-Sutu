import { identifierName } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthResponse } from 'src/app/models/auth-response';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  constructor(private userSvc: UserService) { }

  @Input() userList!: User[]
  @Input() postList!: Post[]
  @Input() currentUser!: AuthResponse

  @Output() onAddToEdit = new EventEmitter()
  @Output() onLike = new EventEmitter()
  @Output() onDelete = new EventEmitter()

  ngOnInit(): void {
    console.log(this.userList);
  }

  isOwner(id: number): boolean {
    if(this.currentUser)
    return this.currentUser.id == id;
    else return false;
  }

  findAuthor(id: number | undefined): User {
    let author = this.userList.find((user: User) => user.id == id)
    if (author)
    return author
    else return new User();
  }

  deletePost(post: Post) {
    this.onDelete.emit(post)
  }
  like(post: Post) {
    this.onLike.emit(post) //però di fatto non è implementato, è solo lì per bellezza
  }
  sendPost(post: Post) {
    this.onAddToEdit.emit(post)
  }

}
