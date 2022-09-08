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

  constructor(
    private router: Router, private userService: UserService
  ) {}
  
  ngOnInit() {}

  isLoggedIn() {
    return this.userService.loggedIn
  }

  logoClicked() {
    if (this.isLoggedIn()) {
      this.router.navigate(['/home']);
    } else {
      this.router.navigate(['/login']);
    }
  }
}
