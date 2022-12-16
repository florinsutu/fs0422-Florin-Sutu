import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './posts.component';
import { PostFormComponent } from './post-form/post-form.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PostListComponent } from './post-list/post-list.component';
import { PostCommentsComponent } from './post-comments/post-comments.component';
import { MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';



@NgModule({
  declarations: [
    PostsComponent,
    PostFormComponent,
    PostListComponent,
    PostCommentsComponent,
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    FormsModule,
    NgMaterialModule,
    ReactiveFormsModule,
    MatDialogModule
  ],
  providers: [
    { provide: MAT_DIALOG_DATA, useValue: {} }
  ]
})
export class PostsModule { }
