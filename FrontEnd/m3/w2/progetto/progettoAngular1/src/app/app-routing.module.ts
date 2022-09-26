import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompletedTodosComponent } from './pages/completed-todos/completed-todos.component';
import { TodosComponent } from './pages/todos/todos.component';

const routes: Routes = [
  {
    path: '',
    component: TodosComponent
  },
  {
    path: 'completedTodos',
    component: CompletedTodosComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
