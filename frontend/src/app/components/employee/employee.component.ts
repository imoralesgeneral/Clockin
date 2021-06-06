import { UserModel } from './../../models/user.model';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ClockinService } from 'src/app/services/clockin.service';
import { delay } from 'rxjs/operators';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styles: [
  ]
})

export class EmployeeComponent implements OnInit {

  employees: UserModel[] = [];
  idCompany: string = localStorage.getItem('idCompany');
  loading: boolean;

  constructor( private router: Router,
               private service: ClockinService ) {

    this.loading = true;

  }

  ngOnInit(): void {
    this.service.getEmployees( this.idCompany )
      .subscribe( data => {
        this.employees = data;
        this.loading = false;
      });
  }

  update( t: any) {

    setTimeout(() =>
    {
        this.ngOnInit();
    },
    2000);

  }

  getEmployee( item: any ) {

    let id = item.id;
    this.router.navigate([ '/employeeDetail', id ]);

  }

}
