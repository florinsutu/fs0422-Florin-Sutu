import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    UsersComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    NgMaterialModule,
    FormsModule
  ]
})
export class UsersModule { }
