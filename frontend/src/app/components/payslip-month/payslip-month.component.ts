import { Component, OnInit } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PayslipModel } from 'src/app/models/payslip.model';
import jsPDF from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-payslip-month',
  templateUrl: './payslip-month.component.html',
  styles: [
  ]
})
export class PayslipMonthComponent implements OnInit {

  payslip: PayslipModel;
  idUser: string;
  month: string;
  year: string;

  constructor( private router: ActivatedRoute,
               private service: ClockinService,
               private routerM: Router ) { }

  ngOnInit(): void {

    this.router.params.subscribe( params => {

      this.idUser =  params['idUser'];
      this.month =  params['month'];
      this.year =  params['year'];

    });

    this.service.getPayslip( this.idUser, this.month, this.year )
    .subscribe( data => {
      this.payslip = data;
    });

  }

  public downloadPDF(): void {
    const DATA = document.getElementById('htmlData');
    const doc = new jsPDF('p', 'mm',  [160, 90]);
    const options = {
      background: 'white',
      scale: 3
    };
    html2canvas(DATA, options).then((canvas) => {

      const img = canvas.toDataURL('image/PNG');

      const bufferX = 15;
      const bufferY = 15;
      const imgProps = (doc as any).getImageProperties(img);
      const pdfWidth = doc.internal.pageSize.getWidth() - 2 * bufferX;
      const pdfHeight = (imgProps.height * pdfWidth) / imgProps.width;
      doc.addImage(img, 'PNG', bufferX, bufferY, pdfWidth, pdfHeight, undefined, 'FAST');
      return doc;
    }).then((docResult) => {
      docResult.save(`payslip_${this.month}-${this.year}.pdf`);
    });
  }

}
