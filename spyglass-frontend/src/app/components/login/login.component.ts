import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap' ;
import { User } from 'src/app/models/user';

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
  user: User = new User();

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
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.router.navigate(['/home']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }

  open(modal: any) {
    this.modalService.open(modal);
  }

  save() {
    console.log("saving")
    this.userService.register(this.user).subscribe(res => {
      console.log("registered")
    })
    this.modalService.dismissAll;
  }

}
