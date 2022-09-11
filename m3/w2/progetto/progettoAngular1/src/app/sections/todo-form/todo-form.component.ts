import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from 'src/app/models/todo';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.scss']
})
export class TodoFormComponent{

  constructor() { }

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
