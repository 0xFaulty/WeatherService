import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {Error404Component, HistoryComponent, LoginComponent, MainComponent} from './core';
import {AuthGuard} from './shared/auth.guard';

const routes: Routes = [
  {path: '', component: MainComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'history', component: HistoryComponent, canActivate: [AuthGuard]},
  {path: 'error404', component: Error404Component},
  {path: '**', redirectTo: 'error404'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
