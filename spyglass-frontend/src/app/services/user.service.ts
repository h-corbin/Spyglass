import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService{

  url = 'http://localhost:8080/users/';

  public username :string = '';
  public password :string = '';
  public loggedIn = false;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json'
    }), };

  constructor(private http: HttpClient) { }

  authenticate(username: string, password: string): Observable<any> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic ' + window.btoa(username + ":" + password)
      }),
    };
    return this.http.get(this.url+'authenticate', httpOptions);
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
    }); 
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(this.url, user, this.httpOptions);
  }

  getUser(): Observable<User> {
    return this.http.get<User>(this.url);
  }

  updateAccount(user: User): Observable<User> {
    return this.http.put<User>(this.url, user, this.httpOptions);
  }
  
  deleteAccount() {
    this.http.delete(this.url).subscribe();
  }

}
