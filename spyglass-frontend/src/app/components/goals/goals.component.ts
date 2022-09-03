import { Component, OnInit } from '@angular/core';
import { Goal } from 'src/app/models/goal';
import { GoalsService } from 'src/app/services/goals.service';


@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {
  goals :Goal = new Goal();

  constructor(private goalService :GoalsService) { }

  ngOnInit(): void {
    this.goalService.getAllGoals().subscribe( res => {
      this.goals = res;
    })
  }

}
 