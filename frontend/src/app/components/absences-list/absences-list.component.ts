import { SimpleChanges } from '@angular/core';
import { Component, Input, OnInit } from '@angular/core';
import { MessageModel } from 'src/app/models/message.model';
import { ClockinService } from 'src/app/services/clockin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-absences-list',
  templateUrl: './absences-list.component.html',
  styles: [
  ]
})
export class AbsencesListComponent implements OnInit {

  idUser: string = localStorage.getItem('idUser');
  idCompany: string = localStorage.getItem('idCompany');
  message: MessageModel;
  loading: boolean;

  constructor( private service: ClockinService,
               private router: ActivatedRoute ) {

    this.loading = true;
    if(this.router.snapshot.params.id != undefined) {
      this.idUser = this.router.snapshot.params.id;
    }

  }

  setIdUser( idUser: string ) {

    this.idUser = idUser;

  }

  setIdCompany( idCompany: string ) {

    this.idCompany = idCompany;

  }

  ngOnInit(): void {
    this.service.getMessage( this.idUser, this.idCompany )
    .subscribe( data => {
      this.message = data;
      this.loading = false;
    });
  }

}
