import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, FormControl, Validators, FormArray } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-accomodation',
  templateUrl: './accomodation.component.html',
  styleUrls: ['./accomodation.component.css']
})
export class AccomodationComponent implements OnInit {

  public form: FormGroup;
  public services = [];
  public types;
  public categories;
  public id;
  public accomodation;
  public terms;

  constructor(private fb: FormBuilder,
              private snackBar: MatSnackBar,
              private http: HttpClient,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = this.route.snapshot.params.id;

    this.form = new FormGroup({
      'name': new FormControl(null,Validators.required),
      'country': new FormControl(null,Validators.required),
      'city': new FormControl(null,Validators.required),
      'address': new FormControl(null,Validators.required),
      'accomodationType': new FormControl(null,Validators.required),
      'category': new FormControl(null,Validators.required),
      'description': new FormControl(null),
      'capacity': new FormControl(null,Validators.required),
      'terms': new FormArray([]),
      'additionalServices': new FormControl([])
    });
    


    


    this.http.get('http://localhost:8081/additionalServices').subscribe(data => {
      this.services = <any>data;
      
    });

    this.http.get('http://localhost:8081/accomodationTypes').subscribe(data => {
      this.types = data;
    });

    this.http.get('http://localhost:8081/categories').subscribe(data => {
      this.categories = data;
    });


    this.http.get('http://localhost:8081/accomodations/'+this.id +'/terms').subscribe(data => {
      this.terms = data;


      this.http.get('http://localhost:8081/accomodations/'+this.id).subscribe(data => {
        this.accomodation = data;

        

        let tempServices = [];
        
        this.form = new FormGroup({
          'name': new FormControl(this.accomodation.name,Validators.required),
          'country': new FormControl(this.accomodation.country,Validators.required),
          'city': new FormControl(this.accomodation.city,Validators.required),
          'address': new FormControl(this.accomodation.address,Validators.required),
          'accomodationType': new FormControl(this.accomodation.accomodationType.id,Validators.required),
          'category': new FormControl(this.accomodation.category.id,Validators.required),
          'description': new FormControl(this.accomodation.description),
          'capacity': new FormControl(this.accomodation.capacity,Validators.required),
          'terms': new FormArray([]),
          'additionalServices': new FormControl([])
        });


      let niz = <number[]>this.form.get('additionalServices').value;
      for(let i=0; i<this.services.length;i++){
        for(let j=0;j<this.accomodation.additionalServices.length;j++){
          let tempSelectedService = this.accomodation.additionalServices[j];
          let tempService =this.services[i];
          if(tempService.id === tempSelectedService.id){
            tempService.test = true;
            niz.push(tempSelectedService.id);
          }
        }  
      }

      for(let i=0; i<this.terms.length;i++){
        const group = new FormGroup({
          'startDate': new FormControl(this.terms[i].startDate,Validators.required),
          'endDate': new FormControl(this.terms[i].endDate,Validators.required),
          'price': new FormControl(this.terms[i].price,Validators.required),
          'reserved': new FormControl(this.terms[i].reserved),
          'id' : new FormControl(this.terms[i].id),
          'visited': new FormControl(this.terms[i].visited)
        });
        (<FormArray>this.form.get('terms')).push(group);
      }

      });
    });
    
  }


  onAddTerm(){
    //const control = new FormControl(null,Validators.required);
    //(<FormArray>this.form.get('terms')).push(control);

    const group = new FormGroup({
      'startDate': new FormControl(null,Validators.required),
      'endDate': new FormControl(null,Validators.required),
      'price': new FormControl(null,Validators.required),
      'reserved': new FormControl(false),
      'id' : new FormControl(null),
      'visited': new FormControl(false)
    });
    (<FormArray>this.form.get('terms')).push(group);
  }

  onTermReserve(term,event){
  
    (<FormArray>this.form.get('terms')).at((<FormArray>this.form.get('terms')).value.indexOf(term.value)).get('reserved').setValue(true);
    
    
  }

  onTermUnreserve(term,event){
  
    (<FormArray>this.form.get('terms')).at((<FormArray>this.form.get('terms')).value.indexOf(term.value)).get('reserved').setValue(false);
    
    
  }

  onServiceCheck(id: any,event: any){
    let niz = <number[]>this.form.get('additionalServices').value;
    if(event.checked){
      niz.push(id);
    }else{
      let index=niz.indexOf(id);
      niz.splice(index,1);
    }
  }
  
  onSubmit(){
   if(this.form.valid){
      this.http.put('http://localhost:8081/accomodations/'+this.id,this.form.value)
      .subscribe(
      );
    }

  }

}
