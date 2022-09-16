import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzBreadCrumbModule } from 'ng-zorro-antd/breadcrumb';



@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    NzBreadCrumbModule,
    NzFormModule,
    NzInputModule,
    NzLayoutModule,
    NzGridModule,
    NzMenuModule
  ],
  exports: [
    CommonModule,
    NzBreadCrumbModule,
    NzFormModule,
    NzInputModule,
    NzLayoutModule,
    NzGridModule,
    NzMenuModule
  ]
})
export class NgzModule { }
