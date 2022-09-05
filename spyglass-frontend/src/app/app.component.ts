import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'spyglass';
  loggedIn = false;

  constructor(
    private router: Router, private userService: UserService
  ) {}
  
  ngOnInit() {
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
       this.loggedIn = this.userService.loggedIn;
      }
    })
  }

  logout() {
    this.userService.logout();
  }
}
