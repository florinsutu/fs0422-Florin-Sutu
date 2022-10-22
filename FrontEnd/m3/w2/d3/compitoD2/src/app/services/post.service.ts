import { Injectable } from '@angular/core';
import { Post } from '../modules/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor() { }

  allPosts:Post[] = [];

  getAllPosts():void{
    fetch("http://localhost:3000/posts")
    .then(response => response.json())
    .then(res => {
      this.allPosts = res
    })
  }

  

}
