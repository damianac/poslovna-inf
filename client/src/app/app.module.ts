import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './modules/material.module';
import { SlideshowModule } from 'ng-simple-slideshow';

import { AppComponent } from './app.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ResetPasswordComponent } from './components/reset-password/reset-password.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { TokenInterceptor } from './services/token.interceptor';
import { EmailDialogComponent } from './components/email-dialog/email-dialog.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { AccomodationItemComponent } from './components/dashboard/accomodation-item/accomodation-item.component';
import { ReservationsComponent } from './components/reservations/reservations.component';
import { MessagesDialogComponent } from './components/messages-dialog/messages-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    RegisterComponent,
    ResetPasswordComponent,
    NavbarComponent,
    EmailDialogComponent,
    ChangePasswordComponent,
    AccomodationItemComponent,
    ReservationsComponent,
    MessagesDialogComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    AppRoutingModule,
    MaterialModule,
    SlideshowModule
  ],
  entryComponents: [
    EmailDialogComponent,
    MessagesDialogComponent
  ],
  providers: [
    [{
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }]
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
