import { ClockinService } from 'src/app/services/clockin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styles: [
  ]
})
export class NavbarComponent implements OnInit {

  constructor( private service: ClockinService,
               private router: Router ) { }

  ngOnInit(): void {
  }

  isAdmin() {

    let admin = localStorage.getItem('isAdmin');
    var boolValue = (admin =="true");
    return boolValue;

  }

  isLogged() {

    if ( this.service.isAuthenticated() ) {
      return true;
    } else {
      return false;
    }

  }

  logout() {
    this.service.logout();
    this.router.navigateByUrl('/login');
  }

}
