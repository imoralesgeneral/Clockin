import { UserModel } from './../../models/user.model';
import { ClockinService } from './../../services/clockin.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styles: [
  ]
})


export class InfoUserComponent implements OnInit {

  user: UserModel;
  idUser: string = localStorage.getItem('idUser');

  loading: boolean;

  constructor( private service: ClockinService ) {

    this.loading = true;

  }

  ngOnInit(): void {
    this.service.getInfoUsuario( this.idUser )
    .subscribe( data => {
      this.user = data;
      this.loading = false;
    });
  }

}
