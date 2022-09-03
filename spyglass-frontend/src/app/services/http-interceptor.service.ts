import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private userService: UserService) { }

  // intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
  //   if (this.userService.isUserLoggedIn() && req.url.indexOf('basicauth') === -1) {
  //       const authReq = req.clone({
  //           headers: new HttpHeaders({
  //               'Access-Control-Allow-Methods': '*',
  //               'Access-Control-Allow-Origin': '*',
  //               'Content-Type': 'application/json',
  //               // 'Authorization': `Basic ${window.btoa(this.userService.username + ":" + this.userService.password)}`
  //               'Authorization': 'Basic ' + window.btoa('admin:password')
  //           })
  //       });
  //       return next.handle(authReq);
  //   } else {
  //       return next.handle(req);
  //   }
  // }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + window.btoa('admin:password')
    }),
  };

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      headers: new HttpHeaders({
          'Content-Type': 'application/json',
          // 'Authorization': `Basic ${window.btoa(this.userService.username + ":" + this.userService.password)}`
          'Authorization': 'Basic ' + window.btoa('admin:password')
      })
    })

    return next.handle(request);
  }
}