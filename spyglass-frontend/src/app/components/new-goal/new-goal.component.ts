import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Form, FormControl, FormGroup, Validators } from '@angular/forms';
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

  goalForm: FormGroup = new FormGroup({});
  goal: Goal = new Goal();
  typeList: Array<String> = ["car", "education", "gifts", "house", "rainy day", "retirement", "travel", "other"];
  selected = false;
  selectedType: GoalType = new GoalType("Select a goal type","");
  goalImage: string = ''; 
  newGoalFailed = false;
  fileName = '';
  @Output() submitted = new EventEmitter();

  constructor(
    public router: Router,
    private goalService: GoalsService
  ) { }

  ngOnInit(): void {
    this.goalForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      description: new FormControl(''),
      targetAmount: new FormControl(0.00, Validators.required),
      currentAmount: new FormControl(0.00)
    });
  }

  onSubmit() {
    this.goal.name = this.goalForm.value.name;
    this.goal.description = this.goalForm.value.description;
    this.goal.targetAmount = this.goalForm.value.targetAmount;
    this.goal.currentAmount = this.goalForm.value.currentAmount;
    this.goal.picture = this.goalImage;
    this.goalService.newGoal(this.goal).subscribe(res => {
      this.newGoalFailed = false;
      this.submitted.emit();
      this.router.navigate(['/home']);
    }, () => {
      this.newGoalFailed = true;
    });
  }

  onTypeSelect(event: String) {
    this.selected = true;
    switch(event) {
      case "car": this.goalImage = "../../assets/photos/car.png"; break;
      case "education": this.goalImage = "../../assets/photos/education.png"; break;
      case "gifts": this.goalImage = "../../assets/photos/gifts.png"; break;
      case "house": this.goalImage = "../../assets/photos/house.png"; break;
      case "rainy day": this.goalImage = "../../assets/photos/money.png"; break;
      case "retirement": this.goalImage = "../../assets/photos/retirement.png"; break;
      case "travel": this.goalImage ="../../assets/photos/travel.jpg"; break;
      case "other": this.goalImage = "../../assets/photos/savings2.jpg"; break;
    }
  }

  onFileSelect(event :any) {
    const file:File = event.target.files[0];
    if (file) {
        this.goalService.uploadImage(file).subscribe(() => {
          this.goalImage = "../../../assets/uploaded_photos/" + file.name;
        });
    }
  }
}
