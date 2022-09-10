import { Component, OnInit } from '@angular/core';

type IRoutes = {
  text: string;
  url: string[]
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  routes:IRoutes[] = [
    {
      text: 'All Tasks',
      url: ['/']
    },
    {
      text: 'Completed Tasks',
      url: ['/completedTodos']
    }
  ]

}
