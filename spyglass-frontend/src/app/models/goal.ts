export class Goal {
    goalId! : number;
    name : string = '';
    description : string = '';
    picture : string = '';
    targetDate : string = '';
    targetAmount : number = 0.0;
    currentAmount : number = 0.0;
    progress : number = 0;
}