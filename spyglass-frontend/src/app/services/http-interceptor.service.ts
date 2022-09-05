import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class HttpInterceptorService implements HttpInterceptor {

  constructor(private userService: UserService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.userService.loggedIn == true) {
      request = request.clone({
        headers: new HttpHeaders({ 
            'Content-Type': 'application/json',
            'Authorization': `Basic ${window.btoa(this.userService.username + ":" + this.userService.password)}`
        })
      })
    }

    return next.handle(request);
  }
}