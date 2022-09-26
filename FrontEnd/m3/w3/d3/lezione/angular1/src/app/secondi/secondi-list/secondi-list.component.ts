import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-secondi-list',
  templateUrl: './secondi-list.component.html',
  styleUrls: ['./secondi-list.component.scss']
})
export class SecondiListComponent implements OnInit {

  secondi: string[] = [
    'primo',
    'secondo',
    'terzo'
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
