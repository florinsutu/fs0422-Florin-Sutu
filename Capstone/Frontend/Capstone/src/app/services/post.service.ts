import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../models/post';
import { PostDto } from '../models/postDto';

@Injectable({
  providedIn: 'root'
})
export class PostService {


  constructor(private http: HttpClient) { }

  apiUrl:string = 'http://localhost:8080/api/posts'

  getAllPosts():Observable<Post[]>{
    return this.http.get<Post[]>(this.apiUrl)
  }

  getAllPostsOfFollowedUsers(id: number) {
    return this.http.get<Post[]>(this.apiUrl+"/"+id+"/followed")
  }

  getAllPostsOfUnfollowedUsers(id: number) {
    return this.http.get<Post[]>(this.apiUrl+"/"+id+"/explore")
  }



  getPostById(id:string):Observable<Post>{
    return this.http.get<Post>(this.apiUrl+ '/' + id)
  }

  getAllPostsByAuthorId(id:number):Observable<Post[]>{
    return this.http.get<Post[]>(this.apiUrl+"/author/"+id)
  }

  addPost(post: FormData):Observable<Post>{
    return this.http.post<Post>(this.apiUrl, post)
  }

  likePost(postId: string, likerId: number) {
    return this.http.put<Post>(this.apiUrl+"/"+postId+"/like", likerId)
  }

  editPost(post:Post):Observable<Post>{
    return this.http.patch<Post>(this.apiUrl + '/' + post.id, post)
  }

  deletePost(post:Post){
    return this.http.delete<Post>(this.apiUrl + '/' + post.id)
  }
}
