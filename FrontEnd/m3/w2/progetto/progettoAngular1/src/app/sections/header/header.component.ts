import { Component } from '@angular/core';

type IRoutes = {
  text: string;
  url: string[]
}

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent {

  constructor() { }

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
