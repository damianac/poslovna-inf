import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSelectionList, MatSnackBar } from '@angular/material';

import { FilterService } from '../../services/filter.service';
import { AuthService } from '../../services/auth.service';
import { AccomodationService } from '../../services/accomodation.service';
import { ReservationService } from '../../services/reservation.service';
import { Filter } from '../../models/filter';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  public isAuthenticated = false;
  public terms = [];
  public filter: Filter;
  public searchForm: FormGroup;

  public sortBy = [
    { name: 'Price', value: 'PRICE' },
    { name: 'Category', value: 'CATEGORY' },
    { name: 'Rating', value: 'RATING' }
  ];

  public order = [
    { name: 'Ascending', value: 'ASC' },
    { name: 'Descending', value: 'DESC' },
  ];

  constructor(private filterService: FilterService,
              private fb: FormBuilder,
              private authService: AuthService,
              private accomodationService: AccomodationService,
              private reservationService: ReservationService,
              private snackBar: MatSnackBar) { }

  @ViewChild('services') services: MatSelectionList;
  @ViewChild('types') types: MatSelectionList;
  @ViewChild('categories') categories: MatSelectionList;

  ngOnInit() {
    this.isAuthenticated = this.authService.isLoggedIn();

    this.filterService.getSearchFilter().subscribe(
      filter => this.filter = filter
    );

    this.accomodationService.getAccomodations()
      .subscribe(terms => this.terms = terms);

    this.searchForm = this.fb.group({
      city: [null, Validators.required],
      country: [null, Validators.required],
      capacity: [null, Validators.required],
      startDate: [(new Date()).toISOString(), Validators.required],
      endDate: [(new Date()).toISOString(), Validators.required],
      sortBy: 'PRICE',
      orderBy: 'ASC'
    });
  }

  onSumbit() {
    const services = this.services.selectedOptions.selected.map(t => t.value).join();
    const types = this.types.selectedOptions.selected.map(t => t.value).join();
    const categories = this.categories.selectedOptions.selected.map(t => t.value).join();

    if (this.searchForm.valid) {
      this.accomodationService.searchAccomodations(this.searchForm.value, services, types, categories)
        .subscribe(terms => this.terms = terms);
    }
  }

  onReserve(id, index) {
    this.reservationService.reserve(id).subscribe(
      () => {
        this.terms.splice(index, 1);
        this.snackBar.open(`Reservation has been made`, 'Close', {
          duration: 2000
        });
      },
      () => {
        this.snackBar.open(`Error while reserving. Please refresh the page`, 'Close', {
          duration: 2000
        });
      }
    );
  }

}
