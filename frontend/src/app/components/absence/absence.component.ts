import { AbsencesListComponent } from './../absences-list/absences-list.component';
import { Component, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-absence',
  templateUrl: './absence.component.html',
  styles: [
  ]
})
export class AbsenceComponent implements OnInit {

  @ViewChild(AbsencesListComponent) child;

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
