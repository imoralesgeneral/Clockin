import { ClockinService } from 'src/app/services/clockin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {

  usern: string;
  pass: string;
  remember = false;

  constructor( private router: Router,
               private service: ClockinService ) { }

  ngOnInit(): void {
    if ( localStorage.getItem('username') ) {
      this.usern = localStorage.getItem('username');
      this.remember = true;
    }
  }

  login( form: NgForm ) {

    if (  form.invalid ) {
      return; }

    Swal.fire({
      allowOutsideClick: false,
      icon: 'info',
      text: 'Wait a moment...'
    });
    Swal.showLoading();


    this.service.login( this.usern, this.pass )
      .subscribe( resp => {

        Swal.close();

        if ( this.remember ) {
          localStorage.setItem('username', this.usern);
        }

        this.router.navigateByUrl('/home');

      }, (err) => {

        console.log(err);
        Swal.fire({
          icon: 'error',
          title: 'Authentication error',
          text: err.error.error_description
        });
      });

  }

}
