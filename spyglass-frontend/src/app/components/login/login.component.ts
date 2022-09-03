import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password : string = '';
  errorMessage = 'Invalid Credentials';
  successMessage: string = '';
  invalidLogin = false;
  loginSuccess = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.authenticate(this.username, this.password).subscribe(res => {
      this.userService.username = this.username;
      this.userService.password = this.password;
      this.userService.registerSuccessfulLogin(this.username, this.password);
      
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/home']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }

}
