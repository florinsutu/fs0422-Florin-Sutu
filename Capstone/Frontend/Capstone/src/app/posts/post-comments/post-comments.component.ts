import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AuthResponse } from 'src/app/models/auth-response';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { CommentService } from 'src/app/services/comment.service';
import { PostService } from 'src/app/services/post.service';
import { User } from 'src/app/models/user';
import { ImageProcessingService } from 'src/app/services/image-processing.service';
import { CommentDto } from 'src/app/models/commentDto';

@Component({
  selector: 'app-post-comments',
  templateUrl: './post-comments.component.html',
  styleUrls: ['./post-comments.component.scss']
})
export class PostCommentsComponent implements OnInit {

  constructor(private postSvc: PostService, private commentSvc: CommentService, private imgSvc: ImageProcessingService) { }

  ngOnInit(): void {

    this.commentSvc.getCommentsOfPost(this.post).subscribe({
      next: res =>{
        this.comments = res
        this.comments.map((comment: Comment) => this.imgSvc.createImages(comment.sender))
      } ,
      error: error => console.log(error)
    })
  }

  comments: Comment[] = [];
  commentText: string = ''

  @Input() post!: Post;
  @Input() currentUser!: AuthResponse
  @Output() onSendComment = new EventEmitter()

  updateComments(comment: Comment){
    comment.sender = this.imgSvc.createImages(comment.sender)as User
    this.comments.push(comment)
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

}
