import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodosComponent } from './pages/todos/todos.component';
import { CompletedTodosComponent } from './pages/completed-todos/completed-todos.component';
import { HeaderComponent } from './sections/header/header.component';
import { FormsModule } from '@angular/forms';
import { TodoFormComponent } from './sections/todo-form/todo-form.component';
import { TodoListComponent } from './sections/todo-list/todo-list.component';



@NgModule({
  declarations: [
    AppComponent,
    TodosComponent,
    CompletedTodosComponent,
    HeaderComponent,
    TodoFormComponent,
    TodoListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
