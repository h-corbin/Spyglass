import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  user: User = new User();
  enteredPassword: String = "";
  badPassword = false;

  constructor(
    public router: Router,
    private userService: UserService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    this.userService.getUser().subscribe(res =>{
      this.user = res;
    })
  }

  open(modal: any) {
    this.modalService.open(modal);
  }

  updateAccount() {
    if (this.enteredPassword == this.userService.password) {
      this.badPassword = false;
      this.userService.updateAccount(this.user).subscribe( () => {
        this.modalService.dismissAll();
      });
    } else {
      this.badPassword = true;
    }
    
  }

  deleteAccount() {
    if(confirm("Are you sure you want to delete your account?")) {
      this.userService.deleteAccount();
      this.router.navigate(['/login']);
    }
  }

}
