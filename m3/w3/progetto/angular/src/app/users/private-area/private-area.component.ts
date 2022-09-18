import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-private-area',
  templateUrl: './private-area.component.html',
  styleUrls: ['./private-area.component.scss']
})
export class PrivateAreaComponent implements OnInit {

  currentUser!: User

  constructor(
    private userSvc: UserService,
    private auth: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.currentUser = this.auth.getLoggedUser()
  }

  editProfile() {
    this.userSvc.editUser(this.currentUser).subscribe(user => {
      this.currentUser = user
      this.router.navigate(['/'])
    })

  }

}
