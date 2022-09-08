import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccountComponent } from './components/account/account.component';
import { GoalsComponent } from './components/goals/goals.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { NewGoalComponent } from './components/new-goal/new-goal.component';

const routes: Routes = [
  { path: 'new-goal', component: NewGoalComponent},
  { path: '', component: LoginComponent},
  { path: 'home', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'logout', component: LoginComponent},
  { path: 'goals', component: GoalsComponent},
  { path: 'account', component: AccountComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
