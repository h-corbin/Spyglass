import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Goal } from 'src/app/models/goal';

@Injectable({
  providedIn: 'root'
})
export class GoalsService {
  url : string = 'http://localhost:8080/goals/';

  constructor(private http: HttpClient) { }

  getAllGoals() :Observable<Array<Goal>> {
    return this.http.get<Array<Goal>>(this.url);
  }
}
