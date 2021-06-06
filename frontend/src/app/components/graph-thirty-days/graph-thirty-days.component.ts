import { SevenDaysModel } from './../../models/sevenDays.model';
import { Component, OnInit } from '@angular/core';
import { ClockinService } from 'src/app/services/clockin.service';
import { ActivatedRoute } from '@angular/router';

import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label } from 'ng2-charts';

@Component({
  selector: 'app-graph-thirty-days',
  templateUrl: './graph-thirty-days.component.html',
  styles: [
  ]
})
export class GraphThirtyDaysComponent implements OnInit {

  days: SevenDaysModel[] = [];
  idUser: string = localStorage.getItem('idUser');

  barChartLabelsIni: any[] = [];
  barChartDataIni: number[] = [];
  barChartOptions: ChartOptions = {
    responsive: true,
    scales: {
      xAxes: [{
          gridLines: {
              display:false
          }
      }],
      yAxes: [{
          gridLines: {
              //display:false
          },
          ticks: {
            beginAtZero: true
          }
      }]
    }
  };
  barChartLabels: Label[] = [];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  barChartData: ChartDataSets[] = [];
  public doughnutChartColors: Color[] = [
    {backgroundColor:["#d6d3d2"]},
  ];

  constructor( private service: ClockinService,
               private router: ActivatedRoute ) {

    if(this.router.snapshot.params.id != undefined) {
      this.idUser = this.router.snapshot.params.id;
    }

  }

  ngOnInit(  ): void {

    this.service.getLastThirtyDays( this.idUser )
    .subscribe( data => {
      let index = 0;
      for(const elem in data) {
        this.days[index] = new SevenDaysModel(data[elem]);
        index++;
      }
      this.days = this.days.sort((a,b) => {return new Date(a.date).getTime() - new Date(b.date).getTime()});
      index = 0;
      for(const elem in this.days) {
        this.barChartDataIni[index] = Number(this.days[index].hours.toFixed(2));
        this.barChartLabelsIni[index] = this.days[index].date.toString().substring(4,10);
        index++;
      }
      this.barChartLabels = this.barChartLabelsIni;
      this.barChartData = [{label: 'Hours worked',
       data: this.barChartDataIni }];
    });

  }

}
