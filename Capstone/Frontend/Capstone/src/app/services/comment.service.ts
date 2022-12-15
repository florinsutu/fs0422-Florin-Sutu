import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../models/comment';
import { CommentDto } from '../models/commentDto';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  apiUrl: string = 'http://localhost:8080/api/comments'

  constructor(private http: HttpClient) { }

  getAllComments():Observable<Comment[]>{
    return this.http.get<Comment[]>(this.apiUrl)
  }

/*
  getCommentById(id:number):Observable<Comment>{
    return this.http.get<Comment>(this.api_CommentUrl +id)
  } */

  addComment(Comment:CommentDto):Observable<Comment>{
    return this.http.post<Comment>(this.apiUrl, Comment)
  }

  getCommentsOfPost(post:Post):Observable<Comment[]>{
    return this.http.get<Comment[]>(this.apiUrl+ '/post_id/'+post.id)
  }


  editComment(Comment:Comment):Observable<Comment>{
    return this.http.patch<Comment>(this.apiUrl + Comment.id, Comment)
  }


  deleteComment(Comment:Comment){
    return this.http.delete<Comment>(this.apiUrl + Comment.id)
  }
}
