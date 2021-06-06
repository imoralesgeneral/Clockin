import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payslip',
  templateUrl: './payslip.component.html',
  styles: [
  ]
})
export class PayslipComponent implements OnInit {

  years: any[] = [
    {value: '2021', viewValue: '2021'},
    {value: '2020', viewValue: '2020'},
    {value: '2019', viewValue: '2019'},
    {value: '2018', viewValue: '2018'},
    {value: '2017', viewValue: '2017'},
    {value: '2016', viewValue: '2016'},
    {value: '2015', viewValue: '2015'}
  ];

  months: any[] = [
    {value: '1', viewValue: 'January'},
    {value: '2', viewValue: 'February'},
    {value: '3', viewValue: 'March'},
    {value: '4', viewValue: 'April'},
    {value: '5', viewValue: 'May'},
    {value: '6', viewValue: 'June'},
    {value: '7', viewValue: 'July'},
    {value: '8', viewValue: 'August'},
    {value: '9', viewValue: 'September'},
    {value: '10', viewValue: 'October'},
    {value: '11', viewValue: 'November'},
    {value: '12', viewValue: 'December'}
  ];

  monthValue: string;
  yearValue: string;

  constructor( private router: Router ) { }

  getInfo() {
    let obj = {
      startDate : this.monthValue,
      endDate : this.yearValue
    }
    return obj;
  }

  ngOnInit(): void {
  }

  requestPayslip() {

    let idUser = localStorage.getItem('idUser');
    let month = this.getInfo().startDate;
    let year = this.getInfo().endDate;
    if(month != undefined && year != undefined) {
      this.router.navigateByUrl(`/payslips/${idUser}/${month}/${year}`);
    }
    return;

  }

}
