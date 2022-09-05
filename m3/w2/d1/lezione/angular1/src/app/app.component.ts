import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title:string = 'Home';

  arr:string[] = [
    'mario',
    'luisa'

  ];

titleColor:string = 'red'

  returnNames():string[]{
    return this.arr;
  }

  //toggle

  mostra:boolean = false;

  toggle():void{
    this.mostra = !this.mostra;
  }

}
