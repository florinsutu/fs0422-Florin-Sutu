import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/class/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private authSvc: AuthService) { }

  user!:User

  ngOnInit(): void {
    if(this.checkLog())
      this.user = this.authSvc.getLoggedUser()
  }

  checkLog():boolean{
    return this.authSvc.isUserLogged()
  }

  logOut(){
    this.authSvc.logOut();
  }

}
