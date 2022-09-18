import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SiteRoutingModule } from './site-routing.module';
import { SiteComponent } from './site.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { NgMaterialModule } from '../ng-material/ng-material.module';


@NgModule({
  declarations: [
    SiteComponent,
    HeaderComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    SiteRoutingModule,
    NgMaterialModule
  ]
})
export class SiteModule { }
