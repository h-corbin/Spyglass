import { Component, OnInit } from '@angular/core';
import { ChildActivationStart, Router } from '@angular/router';
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
    new GoalType("rainy day", "../../assets/photos/money.png"),
    new GoalType("retirement", "../../assets/photos/retirement.png"),
    new GoalType("travel", "../../assets/photos/travel.jpg"),
    new GoalType("other", "../../assets/photos/savings2.jpg")
  ]
  selected = false;
  selectedType: GoalType = new GoalType("","");
  goalImage: String = ''; 
  newGoalFailed = false;
  fileName = '';
  message? :String;

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

  onTypeSelect() {
    this.selected = true;
    this.goalImage = this.selectedType.image;
  }

  onFileSelect(event :any) {
    const file:File = event.target.files[0];
    if (file) {
        this.goalService.uploadImage(file);
        this.goalImage = "../../../assets/uploaded_photos/" + file.name;
    }
  }

}
