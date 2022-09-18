import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsersRoutingModule } from './users-routing.module';
import { UsersComponent } from './users.component';
import { PrivateAreaComponent } from './private-area/private-area.component';
import { FormsModule } from '@angular/forms';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';


@NgModule({
  declarations: [
    UsersComponent,
    PrivateAreaComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    FormsModule,
    NgMaterialModule,
    SweetAlert2Module
  ]
})
export class UsersModule { }
