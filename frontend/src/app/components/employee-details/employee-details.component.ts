import { AbsencesListComponent } from './../absences-list/absences-list.component';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserModel } from 'src/app/models/user.model';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styles: [
  ]
})
export class EmployeeDetailsComponent implements OnInit {

  employee: UserModel;
  isAdministrator: boolean = false;
  isUser: boolean = true;

  @ViewChild(AbsencesListComponent) absences: AbsencesListComponent;

  constructor( private router: ActivatedRoute,
               private service: ClockinService,
               private routerM: Router ) {

  }

  toMessages() {

    this.routerM.navigateByUrl('/messages');

  }

  getEmployee( id: string ) {

    this.service.getInfoUsuario( id )
      .subscribe( data => {
        this.employee = data;
        this.isAdmin();
        this.isEmployee();
      });

  }

  ngOnInit(): void {

    this.router.params.subscribe( params => {

      this.getEmployee( params['id'] );
      //this.setIdAbsences( params['id'] );

    });

  }

  setIdAbsences( id: string ) {

    this.router.params.subscribe( params => {

      this.absences.setIdUser(id);

    });
  }

  isEmployee() {
    let admin = false;
    for(let rol in this.employee.roles) {
      if(this.employee.roles[rol]['id'] == 1) {
        this.isUser = true;
      }
    }
  }

  isAdmin() {
    let admin = false;
    for(let rol in this.employee.roles) {
      if(this.employee.roles[rol]['id'] == 2) {
        this.isAdministrator = true;
      }
    }
  }

}
