import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthResponse } from 'src/app/models/auth-response';
import { CommentDto } from 'src/app/models/commentDto';
import { Post } from 'src/app/models/post';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { formatDistance, parseISO} from 'date-fns';

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent {

  constructor(private userSvc: UserService) { }

  commentText: string = ''

  @Input() userList!: User[]
  @Input() postList!: Post[]
  @Input() currentUser!: AuthResponse

  @Output() onAddToEdit = new EventEmitter()
  @Output() onLike = new EventEmitter()
  @Output() onDelete = new EventEmitter()
  @Output() onSendComment = new EventEmitter()
  @Output() onOpenComments = new EventEmitter()

  isOwner(id: number): boolean {
    if (this.currentUser)
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

  like(postId: number) {
    this.onLike.emit(postId)
  }

  isLiked(likers: User[]) {

    for (let liker of likers) {
      if (liker.id == this.currentUser.id){
        return true;
      }
    }return false;
  }


  sendPost(post: Post) {
    this.onAddToEdit.emit(post)
  }

  openComments(post: Post) {
    this.onOpenComments.emit(post)
  }

  sendComment(commentText: string, postId: number | undefined) {
    if (postId) {
      let commentDto: CommentDto = {
        text: commentText,
        postId: postId,
        senderId: this.currentUser.id
      }
      this.onSendComment.emit(commentDto)
      this.commentText = ''
    }
  }

  calculateTime(date: any){
    return formatDistance(parseISO(date), Date.now(), {addSuffix: true})
  }

}
