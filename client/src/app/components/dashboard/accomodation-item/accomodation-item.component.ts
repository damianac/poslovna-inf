import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-accomodation-item',
  templateUrl: './accomodation-item.component.html',
  styleUrls: ['./accomodation-item.component.css']
})
export class AccomodationItemComponent implements OnInit {

  @Input() public term: any;
  @Input() public isAuthenticated = false;
  @Output() public reserve = new EventEmitter<number>();

  constructor() { }

  ngOnInit() {
  }

}
