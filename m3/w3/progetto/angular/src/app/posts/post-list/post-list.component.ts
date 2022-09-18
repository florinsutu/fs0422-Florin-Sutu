import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Post } from 'src/app/class/post';
import { User } from 'src/app/class/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  constructor(private userSvc: UserService) { }

  userList: User[] = []

  @Input() postList!: Post[]
  @Input() currentUser!: User | null

  @Output() onAddToEdit = new EventEmitter()
  @Output() onLike = new EventEmitter()
  @Output() onDelete = new EventEmitter()

  ngOnInit(): void {
    this.userSvc.getAllUsers().subscribe({
      next: res => this.userList = res,
      error: error => console.log(error)
    })
  }

  findAuthor(id: number | undefined): string {

    let author = this.userList.find((user: User) => user.id == id)
    if (author)
    return author.name
    else return 'unknown author'
  }

  deletePost(post: Post) {
    this.onDelete.emit(post)
  }
  like(post: Post) {
    this.onLike.emit(post)
  }
  sendPost(post: Post) {
    this.onAddToEdit.emit(post)
  }

}
