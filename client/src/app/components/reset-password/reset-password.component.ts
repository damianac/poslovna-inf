import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatSnackBar } from '@angular/material';
import { ActivatedRoute } from '@angular/router';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  public form: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private route: ActivatedRoute,
              private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.fb.group({
      tokenId: ['', Validators.required],
      newPassword: ['',  Validators.required],
      confirmPassword: ['',  Validators.required]
    });

    const tokenId = this.route.snapshot.queryParamMap.get('token');
    this.form.patchValue({tokenId});
  }

  onSubmit() {
    console.log(this.form.value);

    if (this.form.valid) {
      this.authService.resetPassword(this.form.value).subscribe(
        (data) => this.snackBar.open('Password has been changed!', 'Close', { duration: 3000 }),
        () => this.snackBar.open('Error', 'Close', { duration: 3000})
      );
    }
  }

}
