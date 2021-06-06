import { ClockinService } from 'src/app/services/clockin.service';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard  implements CanActivate {

  constructor( private service: ClockinService,
               private router: Router) {}

  canActivate(): boolean  {

  if ( this.service.isAuthenticated() ) {
    return true;
  } else {
    this.router.navigateByUrl('/login');
    return false;
  }

  }

}
