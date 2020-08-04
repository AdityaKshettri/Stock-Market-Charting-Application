import { HttpInterceptor, HttpRequest, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { AuthService } from '../services/auth.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(public authService: AuthService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {

    /*const authToken = this.authService.getToken();
    if(authToken) {
      const headers = new HttpHeaders({
        'Authorization': "Bearer " + authToken,
        'Content-Type': 'application/json'
      });
      const authRequest = req.clone({headers});
      console.log(authRequest);
      return next.handle(authRequest);
    }*/

    return next.handle(req);
  }
}

