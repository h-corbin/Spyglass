import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap' ;
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string = '';
  password : string = '';
  successMessage: string = '';
  invalidLogin = false;
  registerSuccess = false;
  invalidUsername = false;
  user: User = new User();
  showPassword = false;

  constructor(
    public router: Router,
    private userService: UserService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
  }

  login() {
    this.userService.authenticate(this.username, this.password).subscribe(res => {
      this.userService.registerSuccessfulLogin(this.username, this.password);
      this.invalidLogin = false;
      this.router.navigate(['/home']);
    }, () => {
      this.registerSuccess = false;
      this.invalidLogin = true;
    });
  }

  open(modal: any) {
    this.modalService.open(modal);
  }

  close() {
    this.invalidLogin = false;
    this.invalidUsername = false;
    this.user = new User();
    this.modalService.dismissAll();
  }

  save() {
    this.userService.register(this.user).subscribe(res => {
      this.modalService.dismissAll();
      this.registerSuccess = true;
      this.invalidUsername = false;
      this.invalidLogin = false;
      this.user = new User();
    }, () => {
      this.registerSuccess = false;
      this.invalidUsername = true;
    });
  }

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }

}
