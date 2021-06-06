
export class AbsenceModel {

  id: number;
  codUser: number;
  idCompany: number;
  type: string;
  startTime: Date;
  endTime: Date;
  validated?: Boolean;

  constructor( data: any ) {

    this.id = data['id'];
    this.codUser = data['codUser'];
    this.idCompany = data['idCompany'];
    this.type = data['type'];
    this.startTime = new Date(data['startTime']);
    this.endTime = new Date(data['endTime']);
    this.validated = data['validated'];

  }

}
