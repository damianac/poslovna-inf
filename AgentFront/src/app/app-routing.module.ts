import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccomodationsComponent } from 'src/app/accomodations/accomodations.component';
import { NewAccomodationComponent } from 'src/app/new-accomodation/new-accomodation.component';
import { AccomodationComponent } from 'src/app/accomodation/accomodation.component';
import { TermsComponent } from 'src/app/terms/terms.component';




const routes: Routes = [
  {path: '', redirectTo: 'accomodations', pathMatch: 'full'},
  {path: 'accomodations', component: AccomodationsComponent, pathMatch: 'full'},
  {path: 'newaccomodation', component: NewAccomodationComponent, pathMatch: 'full'},
  {path: 'accomodations/:id', component: AccomodationComponent, pathMatch: 'full'},
  {path: 'terms', component: TermsComponent, pathMatch: 'full'}
];


@NgModule({
    imports: [
      RouterModule.forRoot(routes, {useHash: true})
    ],
    exports: [
      RouterModule
    ]
  })
  export class AppRoutingModule { }