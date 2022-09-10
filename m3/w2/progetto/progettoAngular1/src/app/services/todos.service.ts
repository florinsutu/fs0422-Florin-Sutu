import { Injectable } from '@angular/core';
import { Todo } from '../models/todo';

@Injectable({
  providedIn: 'root'
})
export class TodosService {

  constructor() { }

  toDoUrl: string = 'http://localhost:3000/todos'

  getAllToDoes(): Promise<Todo[]> {

    return new Promise<Todo[]>((resolve) => {
      setTimeout(() => {
        resolve(fetch(this.toDoUrl)
          .then(res => res.json()))
      }, 2000)
    })
  }

  getToDoById(id: number) {

  }

  addToDo(todo: Todo): Promise<Todo> {

    let options = {
      method: 'POST',
      body: JSON.stringify(todo),
      headers: { "content-type": "application/json" }
    }

    return new Promise<Todo>((resolve) => {
      setTimeout(() => {
        resolve(fetch(this.toDoUrl, options)
          .then(res => res.json()))
      }, 2000)
    })

  }

  deleteToDo(todo: Todo): Promise<Todo> {  //da sistemare in seguito

    let options = {
      method: 'DELETE',
      headers: { "content-type": "application/json" }
    }

    return new Promise<Todo>((resolve) => {
      setTimeout(() => {
        resolve(fetch(this.toDoUrl + '/' + todo.id, options)
          .then(() => todo)) //per rispettare la traccia che chiedeva la Promise per ogni metodo della CRUD
      }, 2000)
    })

  }

  editToDo(todo: Todo): Promise<Todo> {

    let options = {
      method: 'PATCH',
      body: JSON.stringify({
        title: todo.title,
        completed: todo.completed
      }),
      headers: { "content-type": "application/json" }
    }

    return new Promise<Todo>((resolve) => {
      setTimeout(() => {
        resolve(fetch(this.toDoUrl + '/' + todo.id, options)
          .then(res => res.json()))
      }, 2000)
    })

  }
}
