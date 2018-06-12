import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public form: FormGroup;

  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private route: ActivatedRoute,
    private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.form = this.fb.group({
      //tokenId: ['', Validators.required],
      newPassword: ['',  Validators.required],
      confirmPassword: ['',  Validators.required]
    });

    const tokenId = this.route.snapshot.queryParamMap.get('token');
    this.form.patchValue({tokenId});
  }


  onSubmit() {
    console.log(this.form.value);

    if (this.form.valid) {
      this.authService.changePassword(this.form.value).subscribe(
        (data) => this.snackBar.open('Password has been changed!', 'Close', { duration: 3000 }),
        () => this.snackBar.open('Error', 'Close', { duration: 3000})
      );
    }
  }

}
