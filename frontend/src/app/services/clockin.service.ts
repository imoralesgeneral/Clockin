import { MessageModel } from './../models/message.model';
import { DailyEntryModel } from './../models/dailyEntry.model';
import { CompanyModel } from './../models/company.model';
import { UserModel } from './../models/user.model';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { map } from 'rxjs/operators';
import { EmployeeModel } from '../models/employee.model';
import { PayslipModel } from '../models/payslip.model';

@Injectable({
  providedIn: 'root'
})
export class ClockinService {

  userToken: string;

  constructor( private http: HttpClient ) {
    this.readToken();
  }

  getQuery( query: string ) {

    const url = `/api/${ query }`;

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.userToken}`
    });

    return this.http.get(url, { headers });
    //return this.http.get(url);
  }

  postQuery( query: string, obj: any ) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.userToken}`
    });
    const url = `/api/${ query }`;
    return this.http.post(url, obj, { headers });

  }

  putQuery( query: string, obj: any ) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.userToken}`
    });
    const url = `/api/${ query }`;
    return this.http.put(url, obj, { headers });

  }

  deleteQuery( query: string ) {

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${this.userToken}`
    });
    const url = `/api/${ query }`;
    return this.http.delete(url, { headers });
  }

  getInfoUsuario( id: string ) {

    return this.getQuery(`users/id/${id}`).pipe( map( data => new UserModel(data)) );

  }

  getCompanies() {

    let url = 'companies/';

    return this.http.get(url).pipe( map( data => {
      let companies: CompanyModel[] = [];
      for(let elem in data) {
        companies[elem] = new CompanyModel(data[elem]);
      }
      return companies;
    }) );

  }

  getInfoCompany( id: string ) {

    return this.getQuery(`companies/id/${id}`).pipe( map( data => new CompanyModel(data)) );

  }

  getDailyEntry( id: string ) {

    return this.getQuery(`entries/today/${id}`);

  }

  getLastSevenDays( id: string ) {

    return this.getQuery(`entries/week/${id}`);

  }

  getLastThirtyDays( id: string ) {

    return this.getQuery(`entries/month/${id}`);

  }

  getMessage( idUser: string, idCompany: string ) {

    return this.getQuery(`absences/message/${idUser}/${idCompany}`).pipe( map( data => new MessageModel(data)) );

  }

  getRestHolidays( idUser: string ) {

    return this.getQuery(`absences/rest/${idUser}`).pipe( map( (data: number) => data) );

  }

  getPayslip( idUser: string, month: string, year: string ) {

    return this.getQuery(`users/payslip/${idUser}/${month}/${year}`).pipe( map( data => new PayslipModel(data)) );

  }

  getEmployees( idCompany: string ) {

    return this.getQuery(`users/idcompany/${idCompany}`).pipe( map( data => {
      let employees: UserModel[] = [];
      for(let elem in data) {
        employees[elem] = new UserModel(data[elem]);
      }
      return employees;
    }) );

  }

  getEmployeesSearch( idCompany: string, term: string ) {

    return this.getQuery(`users/term/${idCompany}/${term}`).pipe( map( data => {
      let employees: UserModel[] = [];
      for(let elem in data) {
        employees[elem] = new UserModel(data[elem]);
      }
      return employees;
    }) );

  }

  doClockin( idUser: string ) {
    return this.postQuery(`entries/create/${idUser}`, null);
  }

  doClockout( idUser: string ) {
    return this.putQuery(`entries/update/${idUser}`, null);
  }

  validateAbsence( idAbsence: string ) {
    return this.putQuery(`absences/update/${idAbsence}/true`, null);
  }

  declineAbsence( idAbsence: string ) {
    return this.putQuery(`absences/update/${idAbsence}/false`, null);
  }

  requestAbsence( obj : any ) {
    return this.postQuery(`absences/create/`, obj);
  }

  registerEmployee ( obj: any ) {
    return this.postQuery(`users/create/`, obj);
  }

  registerAdmin( obj: any ) {

    const url = `users/create/`;
    return this.http.post(url, obj);

  }

  registerCompany ( obj: any ) {

    const url = `companies/create/`;
    return this.http.post(url, obj);

  }

  updateEmployee ( obj: any, idUser: string ) {
    return this.putQuery(`users/update/${idUser}`, obj);
  }

  deleteEmployee ( idUser: string ) {
    return this.deleteQuery(`users/delete/${idUser}`);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('tokenRefresh');
    localStorage.removeItem('idCompany');
    localStorage.removeItem('nameCompany');
    localStorage.removeItem('idUser');
    localStorage.removeItem('isAdmin');
    localStorage.removeItem('expire');
  }

  login ( usern: string, pass: string ) {

    const headers = {
      'Content-type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('frontendapp:12345')
    }

    const body = new HttpParams()
    .set('username', usern)
    .set('password', pass)
    .set('grant_type', 'password');

    return this.http.post(`/api/security/` + 'oauth/token', body, {headers}).pipe(
      map( resp => {
        this.saveToken( resp['access_token'] );
        this.saveRefreshToken( resp['refresh_token'] );
        this.saveIdCompany( resp['companyId'] );
        this.saveNameCompany( resp['companyName'] );
        this.saveIdUser( resp['userId'] );
        this.saveIsAdmin( resp['isAdmin'] );
        return resp;
      })
    );
  }

  refreshLogin (  ) {

    const headers = {
      'Content-type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + btoa('frontendapp:12345')
    }

    const body = new HttpParams()
    .set('refresh_token', localStorage.getItem('refresh_token'))
    .set('grant_type', 'refresh_token');

    return this.http.post(`/api/security/` + 'oauth/token', body, {headers}).pipe(
      map( resp => {
        this.saveToken( resp['access_token'] );
        this.saveRefreshToken( resp['refresh_token'] );
        this.saveIdCompany( resp['companyId'] );
        this.saveNameCompany( resp['companyName'] );
        this.saveIdUser( resp['userId'] );
        this.saveIsAdmin( resp['isAdmin'] );
        return resp;
      })
    );

  }

  private saveIdCompany( idCompany: string ) {
    localStorage.setItem('idCompany', idCompany);
  }

  private saveNameCompany( nameCompany: string ) {
    localStorage.setItem('nameCompany', nameCompany);
  }

  private saveIdUser( idUser: string ) {
    localStorage.setItem('idUser', idUser);
  }

  private saveIsAdmin( isAdmin: boolean ) {
    localStorage.setItem('isAdmin', String(isAdmin));
  }

  private saveToken( idToken: string ) {

    this.userToken = idToken;
    localStorage.setItem('token', idToken);

    let today = new Date();
    today.setSeconds( 3600 );

    localStorage.setItem('expire', today.getTime().toString() );

  }

  private saveRefreshToken( refreshToken: string ) {

    localStorage.setItem('tokenRefresh', refreshToken);

  }

  readToken() {

    if ( localStorage.getItem('token') ) {
      this.userToken = localStorage.getItem('token');
    } else {
      this.userToken = '';
    }

    return this.userToken;

  }

  isAuthenticated(): boolean {

    if ( this.userToken.length < 2 ) {
      return false;
    }

    const expire = Number(localStorage.getItem('expire'));
    const expireDate = new Date();
    expireDate.setTime(expire);

    if ( expireDate > new Date() ) {
      return true;
    } else {
      return false;
    }
  }

}
