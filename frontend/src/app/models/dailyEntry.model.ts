
export class DailyEntryModel {

  date: string;
  entryTime: Date;
  departureTime?: Date;

  constructor( data:any ) {

    this.date = data['date'];
    this.entryTime = new Date(data['entryTime']);
    if(data['departureTime'] > 0) {
      this.departureTime = new Date(data['departureTime']);
    }

  }

}
