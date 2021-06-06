import { DailyEntryModel } from './../../models/dailyEntry.model';
import { Component, OnInit } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-daily-entry',
  templateUrl: './daily-entry.component.html',
  styles: [
  ]
})
export class DailyEntryComponent implements OnInit {

  TODAY: Date = new Date();
  dailyEntry: DailyEntryModel[] = [];
  idUser: string = localStorage.getItem('idUser');
  loading: boolean;

  constructor( private service: ClockinService,
               private router: Router ) {

    this.loading = true;

  }

  ngOnInit(): void {

    this.service.getDailyEntry( this.idUser )
    .subscribe( data => {
      let index = 0;
      // tslint:disable-next-line: forin
      for(const elem in data) {
        this.dailyEntry[index] = new DailyEntryModel(data[elem]);
        index++;
      }
      this.loading = false;
    });

  }

  toClockin() {

    this.router.navigateByUrl('/clockin');

  }

}
