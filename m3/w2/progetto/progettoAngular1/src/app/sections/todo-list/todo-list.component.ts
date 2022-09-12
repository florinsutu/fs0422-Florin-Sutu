import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.scss']
})
export class TodoListComponent {

  constructor(private todoSvc: TodosService) { }

  @Input() toDoList!: Todo[]

  @Output() onAddToEdit = new EventEmitter()

  @Output() onAddToCompleted = new EventEmitter()

  @Output() onDelete = new EventEmitter()

  sendTodo(todo: Todo): void {
    this.onAddToEdit.emit(todo)

   }

   addToCompleted(todo: Todo): void {
    this.onAddToCompleted.emit(todo)
    Swal.fire({
      position: 'top-end',
      icon: 'info',
      title: 'Processing Request',
      showConfirmButton: false,
      timer: 2000
    })
   }

   deleteTodo(todo: Todo): void {
    this.onDelete.emit(todo)
    Swal.fire({
      position: 'top-end',
      icon: 'info',
      title: 'Processing Request',
      showConfirmButton: false,
      timer: 2000
    })
   }


}
