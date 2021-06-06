import { Component, OnInit } from '@angular/core';
import { MessageModel } from 'src/app/models/message.model';
import { ClockinService } from 'src/app/services/clockin.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-message-summary',
  templateUrl: './message-summary.component.html',
  styles: [
  ]
})
export class MessageSummaryComponent implements OnInit {

  message: MessageModel;
  idUser: string = localStorage.getItem('idUser');
  idCompany: string = localStorage.getItem('idCompany');
  loading: boolean;

  constructor( private service: ClockinService,
               private router: Router ) {

    this.loading = true;

  }

  ngOnInit(): void {

    this.service.getMessage( this.idUser, this.idCompany )
      .subscribe( data => {
        this.message = data;
        this.loading = false;
      });

  }

  toMessages() {

    this.router.navigateByUrl('/messages');

  }

  isAdmin() {

    let admin = localStorage.getItem('isAdmin');
    var boolValue = (admin =="true");
    return boolValue;

  }

}
