import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SecondiComponent } from './secondi.component';
import { SecondiListComponent } from './secondi-list/secondi-list.component';

const routes: Routes = [
  {
    path: '',
    component: SecondiComponent,
    children: [
      {
        path: '',
        component: SecondiListComponent,
      }
    ]
  }
  ]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecondiRoutingModule { }
