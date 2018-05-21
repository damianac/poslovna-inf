import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Filter } from '../models/filter';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FilterService {

  constructor(private http: HttpClient) { }

  getSearchFilter(): Observable<Filter> {
    return this.http.get<Filter>(environment.hostUrl + '/api/filter');
  }

}
