import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/modules/post';

@Component({
  selector: 'app-post-attivi',
  templateUrl: './post-attivi.component.html',
  styleUrls: ['./post-attivi.component.scss']
})
export class PostAttiviComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    this.getPosts();
  }

  posts: Post[] = [];

  activePosts:Post[] = []

  getPosts(){
    fetch("http://localhost:3000/posts")
    .then(response => response.json())
    .then(response => {
      this.posts = response
      this.activePosts = response.filter((posts:Post) => posts.active == true);
    })
  }

}
