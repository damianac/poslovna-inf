import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AccomodationService {

  constructor(private http: HttpClient) { }

  getAccomodations() {
    return this.http.get<any>(environment.hostUrl + '/api/accomodations');
  }

  searchAccomodations(form, services, types, categories): Observable<any> {
    return this.http.get<any>(environment.hostUrl + '/api/accomodations/search', {
      params: {
        city: form.city,
        country: form.country,
        capacity: form.capacity,
        startDate: new Date(form.startDate).toLocaleDateString(),
        endDate: new Date(form.endDate).toLocaleDateString(),
        types: types,
        services: services,
        categories: categories,
        sortBy: form.sortBy,
        orderBy: form.orderBy,
      }
    });
  }

}
