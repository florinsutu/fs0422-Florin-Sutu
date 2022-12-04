import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: FormGroup = new FormGroup({});
  flag: boolean = true;

  constructor(private fb: FormBuilder, private authSvc: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      username: [null],
      password: [null, [Validators.required, Validators.minLength(4)]],
    });
  }

  login() {

    this.authSvc.login(this.form.value)
      .subscribe({
        next: res => {
          this.authSvc.saveAccessData(res)
          this.router.navigate(['/site'])
        },
        error: error => {
          Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: 'This user does not exist',
            showConfirmButton: false,
            timer: 2000
          })
        }
      })
  }

}
