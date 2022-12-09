import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SiteRoutingModule } from './site-routing.module';
import { SiteComponent } from './site.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';
import { PostFormComponent } from '../posts/post-form/post-form.component';


@NgModule({
  declarations: [
    SiteComponent,
    NavbarComponent
  ],
  imports: [
    CommonModule,
    SiteRoutingModule,
    NgMaterialModule
  ]
})
export class SiteModule { }
