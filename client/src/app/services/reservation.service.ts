import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  constructor(private http: HttpClient) { }

  reserve(id): Observable<any> {
    return this.http.put(environment.hostUrl + '/api/reservations/' + id, null);
  }

  getReservations(): Observable<any> {
    return this.http.get(environment.hostUrl + '/api/reservations');
  }

  cancelReservation(id): Observable<any> {
    return this.http.delete(environment.hostUrl + '/api/reservations/' + id);
  }

}
