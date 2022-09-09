import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TodosComponent } from './pages/todos/todos.component';
import { CompletedTodosComponent } from './pages/completed-todos/completed-todos.component';
import { HeaderComponent } from './sections/header/header.component';


@NgModule({
  declarations: [
    AppComponent,
    TodosComponent,
    CompletedTodosComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
