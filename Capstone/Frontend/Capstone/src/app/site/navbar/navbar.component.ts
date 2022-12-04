import { outputAst } from '@angular/compiler';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { AuthResponse } from 'src/app/models/auth-response';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {
  constructor(private authSvc: AuthService) { }

  loggedUser!:AuthResponse

  @Input() sidenav!: MatSidenav;
  @Output() onToggleSidenav = new EventEmitter();

  toggleSidenav():void{
    this.onToggleSidenav.emit()
  };

  ngOnInit(): void {
    if(this.checkLog())
      this.loggedUser = this.authSvc.getAccessData()
  }

  checkLog():boolean{
    return this.authSvc.isUserLogged()
  }

  logOut(){
    this.authSvc.logOut();
  }



}
