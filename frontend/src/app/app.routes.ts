import { PayslipMonthComponent } from './components/payslip-month/payslip-month.component';
import { RegisterAdminComponent } from './components/register-admin/register-admin.component';
import { LoginComponent } from './components/login/login.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { MessageComponent } from './components/message/message.component';
import { PayslipComponent } from './components/payslip/payslip.component';
import { Routes } from '@angular/router';

import { AbsenceComponent } from './components/absence/absence.component';
import { ClockinComponent } from './components/clockin/clockin.component';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuard } from './guards/auth.guard';

export const ROUTES: Routes = [

    {path: 'home', component: HomeComponent, canActivate: [ AuthGuard ] },
    {path: 'login', component: LoginComponent },
    {path: 'register', component: RegisterAdminComponent },
    {path: 'clockin', component: ClockinComponent, canActivate: [ AuthGuard ] },
    {path: 'absences', component: AbsenceComponent, canActivate: [ AuthGuard ] },
    {path: 'payslips', component: PayslipComponent, canActivate: [ AuthGuard ] },
    {path: 'payslips/:idUser/:month/:year', component: PayslipMonthComponent, canActivate: [ AuthGuard ] },
    {path: 'messages', component: MessageComponent, canActivate: [ AuthGuard ] },
    {path: 'employees', component: EmployeeComponent, canActivate: [ AuthGuard ] },
    {path: 'employeeDetail/:id', component: EmployeeDetailsComponent, canActivate: [ AuthGuard ] },
    {path: 'search', component: SearchComponent, canActivate: [ AuthGuard ] },
    {path: '', pathMatch: 'full', redirectTo: 'home' },
    {path: '**', pathMatch: 'full', redirectTo: 'login' }

];

