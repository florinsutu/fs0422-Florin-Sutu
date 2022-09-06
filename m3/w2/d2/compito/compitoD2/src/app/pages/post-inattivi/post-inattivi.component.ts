import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/modules/post';

@Component({
  selector: 'app-post-inattivi',
  templateUrl: './post-inattivi.component.html',
  styleUrls: ['./post-inattivi.component.scss']
})
export class PostInattiviComponent implements OnInit {

  constructor() { }


  ngOnInit(): void {
    this.getPosts();
  }

  posts: Post[] = [];

  inactivePosts:Post[] = []

  getPosts(){
    fetch("http://localhost:3000/posts")
    .then(response => response.json())
    .then(response => {
      this.posts = response
      this.inactivePosts = response.filter((posts:Post) => posts.active == false);
    })
  }

}



