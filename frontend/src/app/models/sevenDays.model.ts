
export class SevenDaysModel {

  hours: number;
  date: Date;

  constructor( data:any ) {
    this.hours = data['amount'];
    this.date = new Date(parseInt(data['description']));

  }

}
