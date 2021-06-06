import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserModel } from 'src/app/models/user.model';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styles: [
  ]
})
export class SearchComponent implements OnInit {

  employees: UserModel[] = [];
  idCompany: string = localStorage.getItem('idCompany');

  constructor( private router: Router,
               private service: ClockinService ) { }

  ngOnInit(): void {
  }

  find(term: string) {

    if(term.length > 0) {
  // this.loading = true;
     this.service.getEmployeesSearch( this.idCompany, term )
        .subscribe( data => {
          this.employees = data;
        });
    } else {
      this.employees = [];
    }

  }

  getEmployee( item: any ) {

    let id = item.id;
    this.router.navigate([ '/employeeDetail', id ]);

  }

}
