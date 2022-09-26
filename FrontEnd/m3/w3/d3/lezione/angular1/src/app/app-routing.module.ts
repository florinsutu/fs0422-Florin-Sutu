import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './Pages/home/home.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path:'primi',
    loadChildren:() => import('./primi/primi.module').then(m => m.PrimiModule)
  },
  {
    path:'secondi',
    loadChildren:() => import('./secondi/secondi.module').then(m => m.SecondiModule)
  },
  { path: 'chiSiamo',
   loadChildren: () => import('./chi-siamo/chi-siamo.module').then(m => m.ChiSiamoModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
