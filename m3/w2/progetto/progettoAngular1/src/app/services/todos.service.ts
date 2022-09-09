import { Injectable } from '@angular/core';
import { Todo } from '../models/todo';

@Injectable({
  providedIn: 'root'
})
export class TodosService {

  constructor() { }

  allToDoes:Todo[] = [];

  toDoUrl:string =   'http://localhost:3000/todos'

  getAllToDoes():Promise<Todo[]> {

    return new Promise<Todo[]>((resolve, reject) => {
      setTimeout(() => {
        resolve(fetch(this.toDoUrl).then(res => res.json()))
      },2000)
  })
}

  getToDoById(id:number):Todo|boolean {
    return this.allToDoes.find((todo:Todo) => todo.id === id) || false;
  }

  addToDo(todo:Todo):void {
    todo = Object.assign({}, todo)
    this.allToDoes.push(todo)
  }

  deleteToDo(id:number):void{  //da sistemare in seguito
      this.allToDoes = this.allToDoes.filter((todo:Todo) => todo.id != id)
  }

  editToDo(todo:Todo):void {
    let index = this.allToDoes.findIndex((todo:Todo) => todo.id == todo.id)
    this.allToDoes.splice(index, 1, todo)
  }
}
