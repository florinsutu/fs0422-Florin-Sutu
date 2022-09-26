import { NgModule } from '@angular/core';
import { PrimiComponent } from './primi/primi.component';
import { ListComponent } from './list/list.component';
import { PrimiRoutingModule } from './primi-routing.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    ListComponent,
    PrimiComponent
  ],
  imports: [
    PrimiRoutingModule,
    SharedModule
  ]
})
export class PrimiModule { }
