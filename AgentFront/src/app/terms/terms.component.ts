import {Component, OnInit, ViewChild, AfterViewInit} from '@angular/core';
import {MatPaginator, MatSort, MatTableDataSource, MatDialog} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MessagesDialogComponent } from 'src/app/messages-dialog/messages-dialog.component';

@Component({
  selector: 'app-terms',
  templateUrl: './terms.component.html',
  styleUrls: ['./terms.component.css']
})
export class TermsComponent implements OnInit {

  myDisplayedColumns = ['id', 'accomodation_name', 'start_date','end_date','price','visited', 'manage_visit', 'inbox'];
  myDataSource: MatTableDataSource<TermData>;
  public terms;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private http: HttpClient,private dialog: MatDialog) { }

  ngOnInit() {

    this.http.get('http://localhost:8081/accomodations/visited').subscribe(data => {
      this.terms = data;

      const termsD: TermData[] = [];
    for (let i = 0; i < this.terms.length; i++) { termsD.push(createTerm(this.terms[i])); }

    this.myDataSource = new MatTableDataSource(termsD);
    this.myDataSource.paginator = this.paginator;
    this.myDataSource.sort = this.sort;
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

  onConfirmVisit(id){
    this.http.get('http://localhost:8081/accomodations/'+id+'/true',{ responseType: 'text' }).subscribe(data => {

    });
    this.terms[id-1].visited = true;
    const termsD: TermData[] = [];
    for (let i = 0; i < this.terms.length; i++) { termsD.push(createTerm(this.terms[i])); }

    this.myDataSource = new MatTableDataSource(termsD);
    this.myDataSource.paginator = this.paginator;
    this.myDataSource.sort = this.sort;

  }

  onOpenDialog(id: number){
    
    const dialogRef = this.dialog.open(MessagesDialogComponent, 
      {
         width: '450px',
         data: id
    
      });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        
        
        
      }
    });
  }

}


function createTerm(term): TermData{
  return{
    id: term.id,
    accomodation_name: term.accomodation.name,
    start_date: term.startDate,
    end_date: term.endDate,
    price: term.price,
    visited: term.visited
  }
}


export interface TermData{
  id: number;
  accomodation_name: string;
  start_date: string;
  end_date: string;
  price: number;
  visited: boolean;
}
