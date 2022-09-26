import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LiComponent } from './li/li.component';
import { ItComponent } from './it/it.component';



@NgModule({
  declarations: [
    LiComponent,
    ItComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    ItComponent,
    LiComponent,
    CommonModule
  ]
})
export class SharedModule { }
