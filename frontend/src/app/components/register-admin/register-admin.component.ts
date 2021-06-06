import { CompanyModel } from './../../models/company.model';
import { ClockinService } from './../../services/clockin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register-admin',
  templateUrl: './register-admin.component.html',
  styles: [
  ]
})
export class RegisterAdminComponent implements OnInit {

  remember = false;
  password2: string;
  companyName: string;
  registerCompany = false;
  admin: any = {
    name: '',
    username: '',
    email: '',
    idCompany: '',
    password: '',
    phoneNumber: '',
    salaryYear: undefined,
    holidays: undefined,
    payments: undefined,
    weeklyHours: undefined,
    irpf: undefined,
    roles: [
      {
          id: 1,
          type: "ROLE_USER"
      },
      {
        id: 2,
        type: "ROLE_ADMIN"
    }
    ]
  }
  company: any = {
    name: '',
    street: '',
    city: '',
    postalCode: '',
    country: '',
    phoneNumber: ''
  }

  companies: CompanyModel[];


  constructor( private router: Router,
               private service: ClockinService ) { }

  ngOnInit(): void {

    this.service.getCompanies()
    .subscribe( data => {
      this.companies = data;
    });

  }

  createCompany() {
    this.registerCompany = true;
  }

  isNumber( value: string ) {
    if (/^[0-9]*[,]*[.]*[0-9]*$/.test(value)) {
      return true;
    }
    return false;
  }

  onSubmit( form: NgForm ) {
    console.log(form);
    console.log(form.form);
    console.log(form.form.controls['username'].errors);

    if ( form.invalid ) { Swal.fire({
      icon: 'error',
      title: 'Form error',
      text: 'Error to fill the form'
    });
    return; }

    Swal.fire({
      allowOutsideClick: false,
      icon: 'info',
      text: 'Wait a moment...'
    });
    Swal.showLoading();

    if(this.registerCompany) {
    this.service.registerCompany(this.company).subscribe( resp => {

      setTimeout(() =>
      {
        this.companyName = resp['id'];
        Swal.close();
      },
      1000);


      }, (err) => {

        console.log(err);
        Swal.fire({
          icon: 'error',
          title: 'Registerer Company Error',
        });

      });
    }
    setTimeout(() =>
      {
    this.admin.idCompany = Number(this.companyName);

    this.service.registerAdmin( this.admin )
      .subscribe( resp => {

        Swal.close();

        if ( this.remember ) {
          localStorage.setItem('username', this.admin.username);
        }

        this.router.navigateByUrl('/login');

      }, (err) => {

        Swal.fire({
          icon: 'error',
          title: 'Registerer Admin Error',
        });

      });

    },
    2000);

  }

}
