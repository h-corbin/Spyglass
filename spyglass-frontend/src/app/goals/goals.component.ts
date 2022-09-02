import { Component, OnInit } from '@angular/core';
import { GoalsService } from '../goals.service';


@Component({
  selector: 'app-goals',
  templateUrl: './goals.component.html',
  styleUrls: ['./goals.component.css']
})
export class GoalsComponent implements OnInit {
  goals :string = "";

  constructor(private goalService :GoalsService) { }

  ngOnInit(): void {
    this.goalService.getAllGoals().subscribe( res => {
      this.goals = res;
    })
  }

}
 