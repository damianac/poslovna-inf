import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ReservationsComponent } from './components/reservations/reservations.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: DashboardComponent },
  { path: 'reservations', component: ReservationsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'reset-password', component: ResetPasswordComponent},
  { path: 'change-password', component: ChangePasswordComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {useHash: true})
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
