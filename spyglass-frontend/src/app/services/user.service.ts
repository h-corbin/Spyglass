import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { map, Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  url = 'http://localhost:8080/users/';

  public username :string = '';
  public password :string = '';
  public loggedIn = false;

  constructor(private http: HttpClient, private router: Router) { }

  authenticate(username: string, password: string) :Observable<User> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + window.btoa('admin:password')
      }),
    };
    return this.http.get<User>(this.url+username, httpOptions);
  }

  registerSuccessfulLogin(username: string, password: string) {
    this.username = username;
    this.password = password;
    this.loggedIn = true;
  }

  logout() {
    this.http.post('http://localhost:8080/logout', {}).subscribe( () => {
      this.loggedIn = false;
      this.username = '';
      this.password = '';
      this.router.navigate(['/login']);
    });
    
  }

}
