import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.scss']
})
export class TodosComponent implements OnInit {

  allToDoes: Todo[] = [];

  constructor(private todoSvc: TodosService) { }

  ngOnInit(): void {
    this.todoSvc.getAllToDoes()
      .then(res => {
        this.allToDoes = res
      })
  }

  addToDo(){
    this.todoSvc.addToDo
  }
}
