import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: FormGroup = new FormGroup({});
  flag: boolean = true;

  constructor(private fb: FormBuilder, private authSvc: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      username: [null, Validators.required],
      email: [null, [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]],
      password: [null, [Validators.required, Validators.minLength(4)]],
    });
  }
  
  submit(){
    this.authSvc.register(this.form.value)
    .subscribe(res => {
      Swal.fire({
        position: 'top-end',
        icon: 'success',
        title: `User ${res.username} registered successfully`,
        showConfirmButton: false,
        timer: 2000
      })
      this.form.reset();
      this.router.navigate(['/']);
    })
  }

}
