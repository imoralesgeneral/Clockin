import { ValidatorsService } from './../../services/validators.service';
import { Component,  EventEmitter,  OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-register-employee',
  templateUrl: './register-employee.component.html',
  styles: [
  ]
})
export class RegisterEmployeeComponent implements OnInit {

  @Output() reload: EventEmitter<any>;
  forma: FormGroup;
  idCompany: string = localStorage.getItem('idCompany');
  nameCompany: string = localStorage.getItem('nameCompany');

  constructor( private fb: FormBuilder,
               public validators: ValidatorsService,
               private service: ClockinService) {
      this.createForm();
      this.reload = new EventEmitter();
    }

  ngOnInit(): void {
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

  get notValidPass1() {
    return this.forma.get('pass1').invalid && this.forma.get('pass1').touched;
  }

  get notValidPass2() {
    const pass1 = this.forma.get('pass1').value;
    const pass2 = this.forma.get('pass2').value;

    return ( pass1 === pass2 ) ? false : true;
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
      company: [this.nameCompany, ],
      pass1: ['', [Validators.required ] ],
      pass2: ['', [Validators.required ] ],
      phoneNumber: ['', [Validators.required ] ],
      salaryYear: ['', [Validators.required ] ],
      holidays: ['', [Validators.required ] ],
      payments: ['', [Validators.required ] ],
      weeklyHours: ['', [Validators.required ] ],
      irpf: ['', [Validators.required ] ]
    },{
      validators: [this.validators.samePasswords('pass1','pass2'), this.validators.passwordInvalidate('pass1'), this.validators.phoneNumberInvalid('phoneNumber'),
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
      this.createUser();
      this.reload.emit('hol');
    }



    // Posteo de información
    this.forma.reset({
      name: 'Sin nombre'
    });

  }

  createUser() {
    this.service.registerEmployee( this.getUser() ).subscribe( resp => {
      });
    this.reset();
  }

  getUser() {
    let user = {
      name  : this.forma.value.name,
      username: this.forma.value.username,
      email: this.forma.value.email,
      idCompany: this.idCompany,
      password: this.forma.value.pass1,
      phoneNumber: this.forma.value.phoneNumber,
      salaryYear: Number(this.forma.value.salaryYear),
      holidays: Number(this.forma.value.holidays),
      payments: Number(this.forma.value.payments),
      weeklyHours: Number(this.forma.value.weeklyHours),
      irpf: Number(this.forma.value.irpf),
      roles: [
        {
            id: 1,
            type: "ROLE_USER"
        }
      ]
    }
    return user;
  }

  reset() {

    this.forma = this.fb.group({
      name  : '',
      username: '',
      email: '',
      company: this.nameCompany,
      pass1: '',
      pass2: '',
      phoneNumber: '',
      salaryYear: '',
      holidays: '',
      payments: '',
      weeklyHours: '',
      irpf: ''
    });

  }

}
