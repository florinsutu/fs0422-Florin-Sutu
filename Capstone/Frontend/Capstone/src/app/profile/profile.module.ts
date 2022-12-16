import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProfileRoutingModule } from './profile-routing.module';
import { ProfileComponent } from './profile.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { FormsModule } from '@angular/forms';
import { PostsComponent } from '../posts/posts.component';
import { MatDialogModule } from '@angular/material/dialog';


@NgModule({
  declarations: [
    ProfileComponent
  ],
  entryComponents: [PostsComponent],
  imports: [
    CommonModule,
    ProfileRoutingModule,
    NgMaterialModule,
    MatDialogModule,
    FormsModule,

  ]
})
export class ProfileModule { }
