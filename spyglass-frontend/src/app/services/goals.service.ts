import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Goal } from 'src/app/models/goal';

@Injectable({
  providedIn: 'root'
})
export class GoalsService {
  url : string = 'http://localhost:8080/goals';

  constructor(private http: HttpClient) { }

  getAllGoals() :Observable<Goal> {
    return this.http.get<Goal>(this.url);
  }
}
