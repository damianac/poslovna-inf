import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

import { environment } from '../../environments/environment';
import { TokenResponse } from '../models/token-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public loginEvent: BehaviorSubject<boolean>;

  constructor(private http: HttpClient) {
    this.loginEvent = new BehaviorSubject(this.isLoggedIn());
  }

  logout() {
    sessionStorage.removeItem('token');
    this.loginEvent.next(false);
  }

  isLoggedIn(): boolean {
    return sessionStorage.getItem('token') != null;
  }

  getToken(): string {
    return sessionStorage.getItem('token');
  }

  login(credentials: any): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(environment.hostUrl + '/api/account/signin', credentials)
      .pipe(
        tap(data => sessionStorage.setItem('token', data.accessToken))
      );
  }

  register(credentials: any): Observable<any> {
    return this.http.post(environment.hostUrl + '/api/account/register', credentials);
  }

  forgotPassword(email: any): Observable<any> {
    return this.http.post(environment.hostUrl + '/api/account/forgot-password', email.email, {
      headers: {
        'Content-type' : 'text/plain'
      }
    });
  }

  resetPassword(credentials: any): Observable<any> {
    return this.http.post<any>(environment.hostUrl + '/api/account/password-reset', credentials);
  }

  changePassword(credentials: any): Observable<any> {
    return this.http.post<any>(environment.hostUrl + '/api/account/password-change', credentials);
  }

}
