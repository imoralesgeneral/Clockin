import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { DailyEntryModel } from 'src/app/models/dailyEntry.model';
import { ClockinService } from 'src/app/services/clockin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-clockin-day',
  templateUrl: './clockin-day.component.html',
  styles: [
  ]
})
export class ClockinDayComponent implements OnInit {

  @Output() reload: EventEmitter<any>;
  TODAY: Date = new Date();
  dailyEntry: DailyEntryModel[] = [];
  idUser: string = localStorage.getItem('idUser');
  loading: boolean;

  constructor( private service: ClockinService,
               private router: Router ) {

    this.reload = new EventEmitter();
    this.loading = true;

  }

  ngOnInit(): void {
    this.service.getDailyEntry(this.idUser)
    .subscribe( data => {
      let index = 0;
      for(const elem in data) {
        this.dailyEntry[index] = new DailyEntryModel(data[elem]);
        index++;
      }
      this.loading = false;
    });

  }

  toClockin() {
    this.service.doClockin(this.idUser).subscribe( resp => {
    });
    this.ngOnInit();
    this.reload.emit('hol');
  }

  toClockout() {

    this.service.doClockout(this.idUser).subscribe( resp => {
    });
    this.ngOnInit();
    this.reload.emit('hol');
  }

  isDeparture() {

    let index = this.dailyEntry.length-1;
    if(this.dailyEntry.length == 0) {
      return true;
    } else if(this.dailyEntry[index].departureTime == undefined) {
      return false;
    } else {
      return true;
    }

  }

}
