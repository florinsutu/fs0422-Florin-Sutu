import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';

@Component({
  selector: 'app-completed-todos',
  templateUrl: './completed-todos.component.html',
  styleUrls: ['./completed-todos.component.scss']
})
export class CompletedTodosComponent implements OnInit {

  ngOnInit(): void {
    this.todoSvc.getAllToDoes()
      .then(res => this.completedToDoes = res.filter(todo => todo.completed === true))
  }

  constructor(private todoSvc: TodosService) { }

  completedToDoes: Todo[] = [];
  currentToDo: Todo = new Todo('')


  addToEdit(todo: Todo): void {
    todo = Object.assign({}, todo)
    this.currentToDo = todo
  }

  addToCompleted(todo: Todo): void {
    todo.completed = !todo.completed
    this.todoSvc.editToDo(todo)
    .then(res => {
      this.completedToDoes = this.completedToDoes.filter((todo: Todo) => todo.completed === true)
    }) //sarebbe bastato fare come nel ngOnInit, ma per simulare meglio l'attesa della promise...
  }

  deleteToDo(todo: Todo): void {
    if (todo.id) {
      this.todoSvc.deleteToDo(todo)
      .then(res => {
        this.completedToDoes = this.completedToDoes.filter((todo: Todo) => todo.id != res.id)
      })
    }
  }


}
