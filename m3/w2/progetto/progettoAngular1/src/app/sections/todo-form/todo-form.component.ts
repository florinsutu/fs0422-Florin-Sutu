import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss']
})
export class TodoFormComponent {

  constructor(private todoSvc: TodosService) { }

  @Output() onNewTodoCreated = new EventEmitter();

  @Output() onEditTodo= new EventEmitter()

  @Input() currentToDo!: Todo //per ricevere la todo da modificare


  editTodo(): void {
    this.onEditTodo.emit(this.currentToDo)
    this.refreshInput()
  }

  sendTodo(): void {
   this.onNewTodoCreated.emit(this.currentToDo);
   this.refreshInput()
  }

  refreshInput(): void {
    this.currentToDo = new Todo('')
  }

}
