import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PrivateAreaComponent } from './private-area/private-area.component';
import { UsersComponent } from './users.component';

const routes: Routes = [
  {
    path: '',
     component: UsersComponent
  },
  {
    path: 'personal-area',
    component: PrivateAreaComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
