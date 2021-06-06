export class Descriptions {
  descript: string;
  integ: number;
}

export class PayslipModel {

  irpf: number;
  salaryYear: number;
  workedHours: number;
  salaryBefore: number;
  salaryAfter: number;
  hoursAbsences: number;
  discounts: Descriptions[] = [];
  pluses: Descriptions[] = [];

  constructor( data: any ) {

    this.irpf = data['irpf'];
    this.salaryYear = data['salaryYear'];
    this.workedHours = data['workedHours'];
    this.salaryBefore = data['salaryBefore'];
    this.salaryAfter = data['salaryAfter'];
    this.hoursAbsences = data['hoursAbsences'];
    let idx = 0;
    // tslint:disable-next-line: forin
    for( let elem in data['discounts']) {
      let dsc = new Descriptions();
      dsc.descript = elem;
      dsc.integ = data['discounts'][elem];
      this.discounts[idx] = dsc;
      idx++;
    }
    idx = 0;
    // tslint:disable-next-line: forin
    for( let elem in data['pluses']) {
      let dsc = new Descriptions();
      dsc.descript = elem;
      dsc.integ = data['pluses'][elem];
      this.pluses[idx] = dsc;
      idx++;
    }

  }

}
