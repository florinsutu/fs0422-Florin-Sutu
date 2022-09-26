import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostsRoutingModule } from './posts-routing.module';
import { PostsComponent } from './posts.component';
import { PostFormComponent } from './post-form/post-form.component';
import { PostListComponent } from './post-list/post-list.component';
import { FormsModule } from '@angular/forms';
import { NgMaterialModule } from '../ng-material/ng-material.module';


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
