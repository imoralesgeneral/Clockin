import { Injectable } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';

interface ErrorValidate {
  [s:string]: boolean
}

@Injectable({
  providedIn: 'root'
})

export class ValidatorsService {

  public errorMessage: string;

  constructor() { }

  samePasswords( pass1: string, pass2: string ) {

    return ( formGroup: FormGroup ) => {

      const pass1Control = formGroup.controls[pass1];
      const pass2Control = formGroup.controls[pass2];

      if ( pass1Control.value === pass2Control.value ) {
        pass2Control.setErrors(null);
      } else {
        pass2Control.setErrors({ notSame: true,  msg:  "Both passwords are not equals"});
      }

    }

  }

  passwordInvalidate( pass: string ) {

    return ( formGroup: FormGroup ) => {

      const passControl = formGroup.controls[pass];

      if (passControl.value.length < 5) {
        passControl.setErrors({ lengthInvalid: true,  msg:  "Minimum 5 characters"});
      } else if (passControl.value === passControl.value.toLowerCase()){
        passControl.setErrors({ lowerCaseInvalid: true,  msg:  "Your password must contains at least one uppercase"});
      } else if (passControl.value === passControl.value.toUpperCase()){
        passControl.setErrors({ upperCaseInvalid: true,  msg:  "Your password must contains at least one lowercase"});
      } else if (!/\d/.test(passControl.value)){
        passControl.setErrors({ numberInvalid: true,  msg:  "Your password must contains at least one number"});
      } else {
        passControl.setErrors(null);
      }

    }

  }

  phoneNumberInvalid( number: string ) {

    return ( formGroup: FormGroup ) => {

      const numberControl = formGroup.controls[number];

      if (numberControl.value.length != 9) {
        numberControl.setErrors({ lengthInvalid: true,  msg:  "Phone number must contains 9 digits"});
      } else if (/\[a-z]\[A-Z]\s/.test(numberControl.value)){
        numberControl.setErrors({ numberInvalid: true,  msg:  "Phone number must contains only numbers"});
      } else {
        numberControl.setErrors(null);
      }

    }

  }

  numberInvalid( number: string, type: string ) {

    return ( formGroup: FormGroup ) => {

      const numberControl = formGroup.controls[number];

      if (numberControl.value.length < 1) {
        numberControl.setErrors({ lengthInvalid: true,  msg: type + " cannot be in blank"});
      } else if (/\[a-z]\[A-Z]\s/.test(numberControl.value)){
        numberControl.setErrors({ numberInvalid: true,  msg: type + " must contains only numbers"});
      } else {
        numberControl.setErrors(null);
      }

    }

  }



}
