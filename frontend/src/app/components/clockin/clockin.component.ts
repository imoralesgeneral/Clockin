import { GraphSevenDaysComponent } from './../graph-seven-days/graph-seven-days.component';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-clockin',
  templateUrl: './clockin.component.html',
  styles: [
  ]
})
export class ClockinComponent implements OnInit {

  @ViewChild(GraphSevenDaysComponent) child;

  constructor() { }

  ngOnInit(): void {
  }

  update( t: any) {
    setTimeout(() =>
    {
      this.child.ngOnInit();
    },
    1000);
  }

}
