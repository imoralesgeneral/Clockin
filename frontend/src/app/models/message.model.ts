import { AbsenceModel } from './absence.model';

export class MessageModel {

  validated: AbsenceModel[] = [];
  not_validated: AbsenceModel[] = [];
  declined: AbsenceModel[] = [];
  company: AbsenceModel[] = [];

  constructor( data: any ) {

    let index = 0;
    for(let elem in data['validated']) {
      let absence: AbsenceModel;
      absence = new AbsenceModel(data['validated'][index]);
      this.validated[index] = absence;
      index++;
    }
    index = 0;
    for(let elem in data['not_validated']) {
      let absence: AbsenceModel;
      absence = new AbsenceModel(data['not_validated'][index]);
      this.not_validated[index] = absence;
      index++;
    }
    index = 0;
    for(let elem in data['declined']) {
      let absence: AbsenceModel;
      absence = new AbsenceModel(data['declined'][index]);
      this.declined[index] = absence;
      index++;
    }
    index = 0;
    for(let elem in data['not_validated_company']) {
      let absence: AbsenceModel;
      absence = new AbsenceModel(data['not_validated_company'][index]);
      this.company[index] = absence;
      index++;
    }

    /*this.validated = data['validated'];
    this.not_validated = data['not_validated'];
    this.company = data['not_validated_company'];*/

  }

  getNumberValidated() {
    return this.validated.length;
  }

  getNumberNotValidated() {
    return this.not_validated.length;
  }

  getNumberDeclined() {
    return this.declined.length;
  }

  getNumberCompany() {
    return this.company.length;
  }

}
