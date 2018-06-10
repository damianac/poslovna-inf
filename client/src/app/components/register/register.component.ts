import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { AuthService } from '../../services/auth.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public form: FormGroup;
  public messages: string[] = [];

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.register(this.form.value).subscribe(
        () => {
          this.snackBar.open('Activation email sent! Check your mail.', 'close', {
            duration: 4000
          });
        },
        (errorResponse) => {
          this.messages = [];

          if (errorResponse.error.errors) {
            const errorsMesssages = errorResponse.error.errors;
            errorsMesssages.forEach(message => {
              this.messages.push(message.defaultMessage);
            });
          } else {
            this.messages.push(errorResponse.error.message);
          }
        }
      );
    }
  }

}
