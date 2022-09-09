import { formatCurrency, formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Goal } from 'src/app/models/goal';
import { GoalsService } from 'src/app/services/goals.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {
  goalList: Array<Goal> = [];
  editGoal = new Goal();
  updateGoalFailed = false;
  newPartner: String = "";
  noGoals = true;
  format = 'MM/dd/yyyy';
  locale = 'en-US';

  constructor(
    public router: Router,
    private goalService :GoalsService,
    private userService: UserService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.getAllGoals();
  }

  ngOnChanges(): void {
    this.getAllGoals();
  }

  open(modal: any, goal: Goal) {
    this.editGoal = goal;
    this.modalService.open(modal);
  }

  delete(goal: Goal) {
    this.goalService.deleteGoal(goal).subscribe( () => {
      this.getAllGoals();
    });
  }

  update(goal: Goal) {
    this.goalService.updateGoal(goal).subscribe(res => {
      this.updateGoalFailed = false;
      this.getAllGoals();
      this.modalService.dismissAll();
    }, () => {
      this.updateGoalFailed = true;
    });
  }

  invite(goal: Goal, username :String) {
    this.goalService.addPartner(goal, username).subscribe(res => {
      this.updateGoalFailed = false;
      this.getAllGoals();
      this.modalService.dismissAll();
    }, () => {
      this.updateGoalFailed = true;
    });
  }

  getAllGoals() {
    this.goalService.getAllGoals().subscribe( res => {
      this.goalList = res;
      if (this.goalList.length == 0) {
        this.noGoals = true;
        this.router.navigate(['/new-goal']);
      } else {
        this.noGoals = false;
        for (var goal of this.goalList) {
          if (typeof goal.currentAmount !== 'undefined' && typeof goal.targetAmount !== 'undefined') {
            if (goal.targetAmount != 0) {
              goal.progress = 100 * (goal.currentAmount / goal.targetAmount);
            } else {
              goal.progress = 0;
            }
          }
          if (typeof goal.targetDate !== 'undefined') {
            goal.targetDate = formatDate(goal.targetDate, this.format, this.locale);
          }
          if (goal.users.length == 1) {
            goal.displayPartners = false;
          } else {
            goal.displayPartners = true;
            const index = goal.users.indexOf(this.userService.username, 0);
            if (index > -1) {
              goal.users.splice(index, 1);
            }
          }
        }
      }
    });
  }
}
 