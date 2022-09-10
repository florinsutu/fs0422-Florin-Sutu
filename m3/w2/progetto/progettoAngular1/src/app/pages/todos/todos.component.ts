import { Component, OnInit } from '@angular/core';
import { Todo } from 'src/app/models/todo';
import { TodosService } from 'src/app/services/todos.service';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.scss']
})
export class TodosComponent implements OnInit {

  ngOnInit(): void {
    this.todoSvc.getAllToDoes()
      .then(res => this.allToDoes = res)
  }

  constructor(private todoSvc: TodosService) { }

  allToDoes: Todo[] = [];
  currentToDo: Todo = new Todo('')


  addToEdit(todo: Todo): void {
    todo = Object.assign({}, todo) //per far si che il cambio di titolo nell'imput non cambi in tempo reale il titolo nella to do
    this.currentToDo = todo
  }

  addToDo(todo: Todo): void {
    this.todoSvc.addToDo(todo)
      .then(res => this.allToDoes.push(res))
  }

  addToCompleted(todo: Todo): void {
    todo = Object.assign({},todo)
    todo.completed = !todo.completed
    this.todoSvc.editToDo(todo)
    .then(res => {
      let index = this.allToDoes.findIndex((todo: Todo) => todo.id == res.id)
     this.allToDoes[index].completed = res.completed
     //non ho usato splice per rimanere nella logica del patch: cambiare soltanto parte dell'oggetto
     // object.assign serve perchè inspiegabilmente, limitarsi a cambiare il valore di todo.completed aggiorna allToDoes prima dei due secondi
    })
  }

  editToDo(todo: Todo): void {
    this.todoSvc.editToDo(todo)
      .then(res => {
        let index = this.allToDoes.findIndex((todo: Todo) => todo.id == res.id)
       this.allToDoes[index].title = res.title
       //non ho usato splice per rimanere nella logica del patch: cambiare soltanto parte dell'oggetto
      })
  }

  deleteToDo(todo: Todo): void {
    if (todo.id) {
      this.todoSvc.deleteToDo(todo)
      .then(res => {
        this.allToDoes = this.allToDoes.filter((todo: Todo) => todo.id != res.id)
      })
    }
  }

}
