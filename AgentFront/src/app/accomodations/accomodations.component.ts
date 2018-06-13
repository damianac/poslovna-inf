import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-accomodations',
  templateUrl: './accomodations.component.html',
  styleUrls: ['./accomodations.component.css']
})
export class AccomodationsComponent implements OnInit {
  
  myDisplayedColumns = ['id','name','country','city','type','category','capacity','manage','remove'];
  myDataSource: MatTableDataSource<AccomodationData>;
  public accomodations;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private http: HttpClient,private router: Router) {   
  }

  ngOnInit() {
    

    this.http.get('http://localhost:8081/accomodations').subscribe(data => {
      this.accomodations = data;
      console.log(this.accomodations);

      const accomodationsD: AccomodationData[] = [];
    for (let i = 0; i < this.accomodations.length; i++) { accomodationsD.push(createAccomodation(this.accomodations[i])); }

    this.myDataSource = new MatTableDataSource(accomodationsD);
    this.myDataSource.paginator = this.paginator;
    this.myDataSource.sort = this.sort;
    console.log(this.myDataSource);
    });

    
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // Datasource defaults to lowercase matches
    this.myDataSource.filter = filterValue;
    if (this.myDataSource.paginator) {
      this.myDataSource.paginator.firstPage();
    }
  }

  onRemove(id){
    this.http.delete('http://localhost:8081/accomodations/'+id,{ responseType: 'text' }).subscribe(data => {
      this.accomodations.splice(id-1,1);
      const accomodationsD: AccomodationData[] = [];
      for (let i = 0; i < this.accomodations.length; i++) { accomodationsD.push(createAccomodation(this.accomodations[i])); }
      this.myDataSource = new MatTableDataSource(accomodationsD);
    });
  }


}



function createAccomodation(acc): AccomodationData{
  return{
    id: acc.id,
    name: acc.name,
    country: acc.country,
    city: acc.city,
    type: acc.accomodationType.type,
    category: acc.category.category,
    capacity: acc.capacity
  }
}

export interface AccomodationData{
  id: string;
  name: string;
  country: string;
  city: string;
  type: string;
  category: string;
  capacity: number;
}