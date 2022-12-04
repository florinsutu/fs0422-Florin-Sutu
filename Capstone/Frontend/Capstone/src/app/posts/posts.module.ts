import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './posts.component';
import { PostFormComponent } from './post-form/post-form.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { FormsModule } from '@angular/forms';
import { PostListComponent } from './post-list/post-list.component';


@NgModule({
  declarations: [
    PostsComponent,
    PostFormComponent,
    PostListComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    FormsModule,
    NgMaterialModule
  ]
})
export class PostsModule { }
