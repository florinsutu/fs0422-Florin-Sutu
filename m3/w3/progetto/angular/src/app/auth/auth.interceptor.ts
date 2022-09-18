import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authSvc: AuthService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    if(this.authSvc.isUserLogged() ){

      let token = this.authSvc.getAccessToken();
      if(token){
        let requestClone = request.clone({
          headers: request.headers.append('Authorization', 'Bearer ' + token)
        });
        return next.handle(requestClone);
      }
    }


    console.log('Chiamata con utente non loggato');

    return next.handle(request);
  }
}
