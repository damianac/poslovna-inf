import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MesssagesService {

  constructor(private http: HttpClient) { }

  getMessages(id: number): Observable<any> {
    return this.http.get<any>(environment.hostUrl + '/api/reservations/' + id + '/messages');
  }

  sendMessage(id: number, message: string): Observable<any> {
    return this.http.post<any>(environment.hostUrl + '/api/reservations/' + id + '/messages', message, {
      headers: { 'Content-type' : 'text/pain' }
    });
  }

}
