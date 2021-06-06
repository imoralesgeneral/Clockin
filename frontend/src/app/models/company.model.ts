
export class CompanyModel {

  id: number;
  name: string;
  phoneNumber: string;
  street: string;
  city: string;
  postalCode: number;
  country: string;

  constructor( data?:any ) {

    this.id = data['id'];
    this.name = data['name'];
    this.phoneNumber = data['phoneNumber'];
    this.street = data['street'];
    this.city = data['city'];
    this.postalCode = data['postalCode'];
    this.country = data['country'];

  }

}
