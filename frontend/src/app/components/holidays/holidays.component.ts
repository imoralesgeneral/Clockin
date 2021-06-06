import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';
import {FormGroup, FormControl} from '@angular/forms';

@Component({
  selector: 'app-holidays',
  templateUrl: './holidays.component.html',
  styles: [
  ]
})
export class HolidaysComponent implements OnInit {

  @Output() reload: EventEmitter<any>;
  numberHolidays: number;
  typeH : string;
  startDate : string;
  endDate : string;
  msgError: string;
  idUser: string = localStorage.getItem('idUser');
  idCompany: string = localStorage.getItem('idCompany');
  range = new FormGroup({
    start: new FormControl(),
    end: new FormControl()
  });
  types: any[] = [
    {value: 'HOLIDAYS', viewValue: 'Holidays'},
    {value: 'MEDICAL_LEAVE', viewValue: 'Medical Leave'},
    {value: 'PERSONAL_MATTERS', viewValue: 'Personal Matters'}
  ];

  constructor( private service: ClockinService ) {

    this.reload = new EventEmitter();

  }

  ngOnInit(): void {

    this.service.getRestHolidays( this.idUser )
    .subscribe( data => {
      this.numberHolidays = data;
    });

  }

  getNumberHolidays() {
    return this.numberHolidays;
  }

  getInfo() {

    let dateS = new Date(this.range.value['start']).getTime();
    let dateE = new Date(this.range.value['end']).getTime();
    let obj = {
      codUser: this.idUser,
      idCompany: this.idCompany,
      type: this.typeH,
      startTime: dateS,
      endTime: dateE,
      validated: null
    }
      return obj;

  }

  requestAbsence() {

    let obj = this.getInfo();
    if(obj.type == undefined) {
      this.msgError = "Please, insert a type of absence"
     } else if(obj.startTime == 0) {
       this.msgError = "Please, insert start date"
     } else if(obj.endTime == 0) {
       this.msgError = "Please, insert end date"
     } else if(((obj.endTime-obj.startTime) > (86400000*this.numberHolidays)) && obj.type != 'MEDICAL_LEAVE') {
       this.msgError = "You don't have enough days of vacation"
     } else {
      this.msgError = undefined;
      this.service.requestAbsence( obj ).subscribe( resp => {
      });
      this.ngOnInit();
      this.range.reset();
      this.typeH = '';
      this.reload.emit('hol');
    }

  }

  isError() {

    if(this.msgError == undefined || this.msgError.length < 1) {
      return false;
    }
    return true;

  }

}
