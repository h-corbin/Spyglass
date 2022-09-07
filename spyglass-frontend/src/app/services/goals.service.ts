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

  newGoal(goal: Goal) :Observable<Goal> {
    return this.http.post<Goal>(this.url, goal);
  }

  deleteGoal(goal: Goal) :Observable<any>{
    return this.http.delete(this.url+goal.goalId);
  }
}
