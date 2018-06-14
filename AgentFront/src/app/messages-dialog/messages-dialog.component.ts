import { Component, OnInit, Inject } from '@angular/core';
import { FormControl, Validators, FormBuilder, FormGroup } from '@angular/forms';
import { validateVerticalPosition } from '@angular/cdk/overlay';
import { HttpClient } from '@angular/common/http';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-messages-dialog',
  templateUrl: './messages-dialog.component.html',
  styleUrls: ['./messages-dialog.component.css']
})
export class MessagesDialogComponent implements OnInit {

  public form: FormGroup;
  public messages: any;
  public user = "no user";

  constructor(private fb: FormBuilder, private http: HttpClient, @Inject(MAT_DIALOG_DATA) private data: number) { }

  ngOnInit() {
    this.form = this.fb.group({
      message: ['', [Validators.required]]
    });

    this.http.get('http://localhost:8081/accomodations/messages/'+this.data).subscribe(data => {
      this.messages = data;
      if(this.messages.length != 0){
        this.user = this.messages[0].user.email;
        this.form.addControl("termId",new FormControl(this.messages[0].term.id));
        this.form.addControl("userId",new FormControl(this.messages[0].user.id));
      }
      for(let i=0; i<this.messages.length; i++){
        if(this.messages[i].user != null){
          this.messages[i].color = 'primary';
        }else{
          this.messages[i].color = 'warn';
        }
      }
    });
  }
 
  onSendMessage(){
    this.http.post('http://localhost:8081/accomodations/messages',this.form.value).subscribe(
          (data) => {
            this.messages.push(data);
            for(let i=0; i<this.messages.length; i++){
              if(this.messages[i].user != null){
                this.messages[i].color = 'primary';
              }else{
                this.messages[i].color = 'warn';
              }
            }
          }
    );
  }

  

}
