import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
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

  updateGoal(goal: Goal) :Observable<any>{
    goal.users = [];
    return this.http.put(this.url+goal.goalId, goal);
  }

  addPartner(goal: Goal, username: String) :Observable<any>{
    return this.http.put(this.url+goal.goalId+"/"+username, goal);
  }

  deleteGoal(goal: Goal) :Observable<any>{
    return this.http.delete(this.url+goal.goalId);
  }

  deleteAllGoals() :Observable<any>{
    return this.http.delete(this.url);
  }

  uploadImage(file: File) {
    const data: FormData = new FormData();
    data.append('file', file);

    const newRequest = new HttpRequest('POST', this.url+"image", data, {
    reportProgress: true,
    responseType: 'text'
    });

    this.http.request(newRequest).subscribe();
  } 
}
