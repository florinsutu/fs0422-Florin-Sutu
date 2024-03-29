import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import Swal from 'sweetalert2';

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
    Swal.fire({
      position: 'top-end',
      icon: 'info',
      title: 'Processing Request',
      showConfirmButton: false,
      timer: 2000
    })
  }

  sendTodo(): void {
   this.onNewTodoCreated.emit(this.currentToDo);
   this.refreshInput()
   Swal.fire({
    position: 'top-end',
    icon: 'info',
    title: 'Processing Request',
    showConfirmButton: false,
    timer: 2000
  })
  }

  refreshInput(): void {
    this.currentToDo = new Todo('')
  }

}
