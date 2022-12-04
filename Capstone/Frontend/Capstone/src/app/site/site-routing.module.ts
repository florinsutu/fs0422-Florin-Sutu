import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SiteComponent } from './site.component';

const routes: Routes = [
  {
    path: '',
    component: SiteComponent,
    children: [
      {
        path: '',
        loadChildren: () => import('../posts/posts.module').then(m => m.PostsModule),
      },
      {
        path: 'users',
         loadChildren: () => import('../users/users.module').then(m => m.UsersModule),
      },
      {
         path: 'profile',
       loadChildren: () => import('../profile/profile.module').then(m => m.ProfileModule)
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SiteRoutingModule { }
