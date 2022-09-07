import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Goal } from 'src/app/models/goal';
import { GoalsService } from 'src/app/services/goals.service';


@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {
  goalList: Array<Goal> = [];

  constructor(
    public router: Router,
    private goalService :GoalsService
  ) { }

  ngOnInit(): void {
    this.getAllGoals();
  }

  delete(goal: Goal) {
    this.goalService.deleteGoal(goal).subscribe( () => {
      this.getAllGoals();
    });
  }

  private getAllGoals() {
    this.goalService.getAllGoals().subscribe( res => {
      this.goalList = res;
      for (var goal of this.goalList) {
        goal.progress = 100 * (goal.currentAmount / goal.targetAmount);
      }
    })
  }

}
 