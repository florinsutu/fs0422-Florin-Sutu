import { NgModule } from '@angular/core';
import { SecondiListComponent } from './secondi-list/secondi-list.component';
import { SecondiComponent } from './secondi.component';
import { SecondiRoutingModule } from './secondi-routing.module';
import { SharedModule } from '../shared/shared.module';



@NgModule({
  declarations: [
    SecondiListComponent,
    SecondiComponent
  ],
  imports: [
    SecondiRoutingModule,
    SharedModule
  ]
})
export class SecondiModule { }
