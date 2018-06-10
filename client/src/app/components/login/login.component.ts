import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '../../services/auth.service';
import { EmailDialogComponent } from '../email-dialog/email-dialog.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public form: FormGroup;
  public errorMessage: string;
  public hide = true;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private dialog: MatDialog,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.authService.login(this.form.value).subscribe(
        data => {
          console.log(data.profileDto);
          this.router.navigate(['/home']);
          this.authService.loginEvent.next(true);
        },
        error => {
          this.errorMessage = error.error.message;
          this.authService.loginEvent.next(false);
        }
      );
    }
  }

  onOpenDialog() {
    const dialogRef = this.dialog.open(EmailDialogComponent, { width: '450px'});

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authService.forgotPassword(result).subscribe(
          () => this.snackBar.open('Email sent!', 'Close', { duration: 2000 }),
          () => this.snackBar.open('User doesn\'t exists with the given email!', 'Close', { duration: 2000 })
        );
      }
    });
  }

}
