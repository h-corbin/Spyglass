import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GoalsService {
  url : string;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      'Authorization': 'Basic ' + btoa('admin:password')
    }),
  };

  constructor(private http: HttpClient) { 
    this.url = 'http://localhost:8080/goals';
  }

  getAllGoals() :Observable<string> {
    return this.http.get<string>(this.url, this.httpOptions);
  }
}
