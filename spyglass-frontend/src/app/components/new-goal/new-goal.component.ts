import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Goal } from 'src/app/models/goal';
import { GoalType } from 'src/app/models/goalType';
import { GoalsService } from 'src/app/services/goals.service';

@Component({
  selector: 'app-new-goal',
  templateUrl: './new-goal.component.html',
  styleUrls: ['./new-goal.component.css']
})
export class NewGoalComponent implements OnInit {

  goal: Goal = new Goal();
  typeList: Array<GoalType> = [
    new GoalType("car", "../../assets/photos/car.png"),
    new GoalType("education", "../../assets/photos/education.png"),
    new GoalType("gifts", "../../assets/photos/gifts.png"),
    new GoalType("house", "../../assets/photos/house.png"),
    new GoalType("rainy day", "../../assets/photos/savings.png"),
    new GoalType("retirement", "../../assets/photos/retirement.png"),
    new GoalType("travel", "../../assets/photos/travel.jpg"),
    new GoalType("other", "../../assets/photos/savings2.jpg")
  ]
  selectedType :GoalType = new GoalType("","");
  newGoalFailed = false;

  constructor(
    public router: Router,
    private goalService: GoalsService,
  ) { }

  ngOnInit(): void {
  }

  onSubmit() {
    this.goal.picture = this.selectedType.image;
    this.goalService.newGoal(this.goal).subscribe(res => {
      this.newGoalFailed = false;
      this.router.navigate(['/home']);
    }, () => {
      this.newGoalFailed = true;
    });
  }

}
