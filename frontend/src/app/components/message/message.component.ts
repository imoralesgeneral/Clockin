import { MessageModel } from './../../models/message.model';
import { Component, OnInit } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styles: [
  ]
})

export class MessageComponent implements OnInit {

  message: MessageModel;
  idUser: string = localStorage.getItem('idUser');
  idCompany: string = localStorage.getItem('idCompany');
  loading: boolean;

  constructor( private service: ClockinService ) {

    this.loading = true;

  }

  ngOnInit(): void {

    this.service.getMessage( this.idUser, this.idCompany )
      .subscribe( data => {
        this.message = data;
        this.loading = false;
      });

  }

  isAdmin() {

    let admin = localStorage.getItem('isAdmin');
    var boolValue = (admin =="true");
    return boolValue;

  }

  validateAbsence( idAbsence: string ) {

    this.service.validateAbsence( idAbsence ).subscribe( resp => {
    });
    this.ngOnInit();

  }

  declineAbsence( idAbsence: string ) {

    this.service.declineAbsence( idAbsence ).subscribe( resp => {
    });
    this.ngOnInit();

  }

}
