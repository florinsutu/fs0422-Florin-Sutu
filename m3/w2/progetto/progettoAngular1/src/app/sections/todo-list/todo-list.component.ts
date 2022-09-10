import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';

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
   }

   deleteTodo(todo: Todo): void {
    this.onDelete.emit(todo)
   }


}
