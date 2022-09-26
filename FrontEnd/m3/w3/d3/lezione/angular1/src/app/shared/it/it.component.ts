import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-it',
  templateUrl: './it.component.html',
  styleUrls: ['./it.component.scss']
})
export class ItComponent implements OnInit {

@Input() titolo!:string

  constructor() { }

  ngOnInit(): void {
  }

}
