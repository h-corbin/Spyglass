export class Goal {
    goalId? : number;
    name : string = '';
    description : string = '';
    picture : string = '';
    targetDate : string = '';
    targetAmount? : number
    currentAmount? : number;
    progress? : number = 0.0;
    users : Array<String> = [];
    displayPartners = false;
}