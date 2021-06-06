import { CompanyModel } from './../../models/company.model';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';

@Component({
  selector: 'app-info-company',
  templateUrl: './info-company.component.html',
  styles: [
  ]
})
export class InfoCompanyComponent implements OnInit {

  company: CompanyModel;
  idCompany: string = localStorage.getItem('idCompany');
  loading: boolean;

  constructor( private service: ClockinService ) {

    this.loading = true;

  }

  ngOnInit(): void {

    this.service.getInfoCompany( this.idCompany )
      .subscribe( data => {
        this.company = data;
        this.loading = false;
      });

  }

}
