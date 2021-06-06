import { ClockinService } from './services/clockin.service';

import { LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SearchComponent } from './components/search/search.component';
import { EmployeeComponent } from './components/employee/employee.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { ClockinComponent } from './components/clockin/clockin.component';
import { AbsenceComponent } from './components/absence/absence.component';
import { PayslipComponent } from './components/payslip/payslip.component';
import { MessageComponent } from './components/message/message.component';
import { InfoUserComponent } from './components/info-user/info-user.component';
import { InfoCompanyComponent } from './components/info-company/info-company.component';
import { DailyEntryComponent } from './components/daily-entry/daily-entry.component';

// Import routes
import { ROUTES } from './app.routes';

// Import locale
import { registerLocaleData } from '@angular/common';
import localEs from '@angular/common/locales/es';
import { GraphSevenDaysComponent } from './components/graph-seven-days/graph-seven-days.component';

// Import graphs
import { ChartsModule } from 'ng2-charts';
import { MessageSummaryComponent } from './components/message-summary/message-summary.component';
import { ClockinDayComponent } from './components/clockin-day/clockin-day.component';
import { AbsencesListComponent } from './components/absences-list/absences-list.component';
import { HolidaysComponent } from './components/holidays/holidays.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';

import { MatSliderModule } from '@angular/material/slider';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import {MatIconModule} from '@angular/material/icon'

import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { GraphThirtyDaysComponent } from './components/graph-thirty-days/graph-thirty-days.component';
import { RegisterEmployeeComponent } from './components/register-employee/register-employee.component';
import { EditEmployeeComponent } from './components/edit-employee/edit-employee.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterAdminComponent } from './components/register-admin/register-admin.component';
import { RegisterCompanyComponent } from './components/register-company/register-company.component';
import { PayslipMonthComponent } from './components/payslip-month/payslip-month.component';
import { LoadingComponent } from './components/shared/loading/loading.component';
import { FooterComponent } from './components/shared/footer/footer.component';

registerLocaleData(localEs);

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SearchComponent,
    EmployeeComponent,
    NavbarComponent,
    ClockinComponent,
    AbsenceComponent,
    PayslipComponent,
    MessageComponent,
    InfoUserComponent,
    InfoCompanyComponent,
    DailyEntryComponent,
    GraphSevenDaysComponent,
    MessageSummaryComponent,
    ClockinDayComponent,
    AbsencesListComponent,
    HolidaysComponent,
    EmployeeDetailsComponent,
    GraphThirtyDaysComponent,
    RegisterEmployeeComponent,
    EditEmployeeComponent,
    LoginComponent,
    RegisterAdminComponent,
    RegisterCompanyComponent,
    PayslipMonthComponent,
    LoadingComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ChartsModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    MatSliderModule,
    MatSelectModule,
    MatNativeDateModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    RouterModule.forRoot( ROUTES, { useHash: true } ),
    NoopAnimationsModule
  ],
  providers: [
    {
      provide: LOCALE_ID,
      useValue: 'es'
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
