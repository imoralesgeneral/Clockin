import { ValidatorsService } from './../../services/validators.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { UserModel } from 'src/app/models/user.model';
import { ActivatedRoute, Router } from '@angular/router';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-edit-employee',
  templateUrl: './edit-employee.component.html',
  styles: [
  ]
})
export class EditEmployeeComponent implements OnInit {

  forma: FormGroup;
  isAdministrator: boolean = false;
  isUser: boolean = true;
  employee: UserModel;
  idUser: string = localStorage.getItem('idUser');
  idCompany: string = localStorage.getItem('idCompany');
  nameCompany: string = localStorage.getItem('nameCompany');
  loading: boolean;
  roleUser = [
    {
        id: 1,
        type: "ROLE_USER"
    }
  ];
  roleAdmin = [
    {
      id: 1,
      type: "ROLE_USER"
    },
    {
        id: 2,
        type: "ROLE_ADMIN"
    }
  ];

  constructor( private router: ActivatedRoute,
               private service: ClockinService,
               private fb: FormBuilder,
               private routerR: Router,
               public validators: ValidatorsService)
              {

     this.loading = true;
     if(this.router.snapshot.params.id != undefined) {
        this.idUser = this.router.snapshot.params.id;
      }

  }

  ngOnInit(): void {

    this.router.params.subscribe( params => {

      this.getEmployee( this.idUser );
      //this.setIdAbsences( params['id'] );
      this.loading = false;
    });

    this.createForm();
  }

  ngAfterViewInit(): void {
    this.loadForm();
  }

  setIdUser( idUser: string ) {

    this.idUser = idUser;

  }

  setIdCompany( idCompany: string ) {

    this.idCompany = idCompany;

  }

  getEmployee( id: string ) {
    this.service.getInfoUsuario( id )
      .subscribe( data => {
        this.employee = data;
        this.isAdmin();
        this.isEmployee();
      });
  }

  get notValidName() {
    return this.forma.get('name').invalid && this.forma.get('name').touched
  }

  get notValidUsername() {
    return this.forma.get('username').invalid && this.forma.get('username').touched
  }

  get notValidEmail() {
    return this.forma.get('email').invalid && this.forma.get('email').touched
  }


  get notValidPhone() {
    return this.forma.get('phoneNumber').invalid && this.forma.get('phoneNumber').touched;
  }

  get notValidSalary() {
    return this.forma.get('salaryYear').invalid && this.forma.get('salaryYear').touched;
  }

  get notValidHolidays() {
    return this.forma.get('holidays').invalid && this.forma.get('holidays').touched;
  }

  get notValidPayments() {
    return this.forma.get('payments').invalid && this.forma.get('payments').touched;
  }

  get notValidHours() {
    return this.forma.get('weeklyHours').invalid && this.forma.get('weeklyHours').touched;
  }

  get notValidIRPF() {
    return this.forma.get('irpf').invalid && this.forma.get('irpf').touched;
  }

  createForm() {

    this.forma = this.fb.group({
      name  : ['', [ Validators.required, Validators.minLength(5) ] ],
      username: ['', [ Validators.required, Validators.minLength(5) ] ],
      email: ['', [Validators.required, Validators.pattern('[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$') ] ],
      company: ['', ],
      phoneNumber: ['', [Validators.required ] ],
      salaryYear: ['', [Validators.required ] ],
      holidays: ['', [Validators.required ] ],
      payments: ['', [Validators.required ] ],
      weeklyHours: ['', [Validators.required ] ],
      irpf: ['', [Validators.required ] ]
    },{
      validators: [this.validators.phoneNumberInvalid('phoneNumber'),
                   this.validators.numberInvalid('salaryYear', 'Salary'), this.validators.numberInvalid('holidays', 'Holidays'), this.validators.numberInvalid('payments', 'Payment'),
                   this.validators.numberInvalid('weeklyHours', 'Weekly Hours'), this.validators.numberInvalid('irpf', 'IRPF')]
    });
  }

  save() {


    if ( this.forma.invalid ) {
      return Object.values( this.forma.controls ).forEach( control => {

        if ( control instanceof FormGroup ) {
          Object.values( control.controls ).forEach( control => control.markAsTouched() );
        } else {
          control.markAsTouched();
        }
      });

    } else {
      this.updateUser();
    }

    this.forma.reset({
      name: 'Sin nombre'
    });

  }

  deleteEmployee() {
    console.log("delete: "+this.idUser);
    this.service.deleteEmployee( this.idUser  ).subscribe( resp => {
      });
      this.routerR.navigateByUrl("/employees");
  }

  updateUser() {
    this.service.updateEmployee( this.getUser(), this.idUser  ).subscribe( resp => {
      });
      this.routerR.navigateByUrl("/employees");
  }

  getUser() {
    let rolesU = this.isAdministrator ? this.roleAdmin : this.roleUser;
    let user = {
      id  : Number(this.idUser),
      name  : this.forma.value.name,
      username: this.forma.value.username,
      email: this.forma.value.email,
      idCompany: Number(this.idCompany),
      phoneNumber: this.forma.value.phoneNumber,
      salaryYear: Number(this.forma.value.salaryYear),
      holidays: Number(this.forma.value.holidays),
      payments: Number(this.forma.value.payments),
      weeklyHours: Number(this.forma.value.weeklyHours),
      irpf: Number(this.forma.value.irpf),
      roles: rolesU
    }
    return user;
  }

  loadForm() {

    this.forma.reset({
      name  : this.employee.name,
      username: this.employee.username,
      email: this.employee.email,
      company: this.nameCompany,
      phoneNumber: this.employee.phoneNumber,
      salaryYear: this.employee.salaryYear,
      holidays: this.employee.holidays,
      payments: this.employee.payments,
      weeklyHours: this.employee.weeklyHours,
      irpf: this.employee.irpf
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
