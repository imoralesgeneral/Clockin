
export class EmployeeModel {

  id: number;
  idCompany: number;
  username: string;
  password: string;
  name: string;
  email: string;
  phoneNumber: string;
  holidays: number;
  salaryYear: number;
  irpf: number;
  payments: number;
  weeklyHours: number;
  roles: string[]; // Solo se guardan los ids de los roles

  constructor( data:any ) {
    this.id = data['id'];
    this.idCompany = data['idCompany'];
    this.username = data['username'];
    this.password = data['password'];
    this.name = data['name'];
    this.email = data['email'];
    this.phoneNumber = data['phoneNumber'];
    this.holidays = data['holidays'];
    this.salaryYear = data['salaryYear'];
    this.irpf = data['irpf'];
    this.payments = data['payments'];
    this.weeklyHours = data['weeklyHours'];
    this.roles = data['roles'];
  }

}
