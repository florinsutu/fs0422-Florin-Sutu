import { BreakpointObserver } from '@angular/cdk/layout';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { map } from 'rxjs/operators';
import { AuthResponse } from '../models/auth-response';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { ImageProcessingService } from '../services/image-processing.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-site',
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.scss']
})
export class SiteComponent {

  @ViewChild(MatSidenav)
  sidenav!: MatSidenav;
  currentUser: User = new User();

  constructor(
    private observer: BreakpointObserver,
    private authSvc: AuthService,
    private userSvc: UserService,
    private imgSvc: ImageProcessingService,
    ) { }


    loggedUser!: AuthResponse

  ngOnInit(): void {
    if (this.checkLog()){
      this.loggedUser = this.authSvc.getAccessData()
      this.userSvc.getUserById(this.loggedUser.id+"")
      .pipe(
        map((u: User, i)  => this.imgSvc.createImages(u))
        )
      .subscribe(
        res => this.currentUser = res as User,
      )
    }

  }

  ngAfterViewInit() {
    this.observer.observe(['(max-width: 992px)']).subscribe((res) => {
      if (res.matches) {
        this.sidenav.mode = 'over';
        this.sidenav.close();
      } else {
        this.sidenav.mode = 'side';
        this.sidenav.open();
      }
    });
  }

  checkLog(): boolean {
    return this.authSvc.isUserLogged()
  }


}
