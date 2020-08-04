import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FlashMessagesModule } from 'angular2-flash-messages';

import { HomeComponent } from './components/home/home.component';
import { IposComponent } from './components/ipos/ipos.component';
import { CreateIpoComponent } from './components/ipos/create-ipo/create-ipo.component';
import { CreateStockExchangeComponent } from './components/stock-exchanges/create-stock-exchange/create-stock-exchange.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { CreateCompanyComponent } from './components/companies/create-company/create-company.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { StockExchangesComponent } from './components/stock-exchanges/stock-exchanges.component';
import { ImportExcelComponent } from './components/import-excel/import-excel.component';
import { ComparisonChartsComponent } from './components/comparison-charts/comparison-charts.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SectorsComponent } from './components/sectors/sectors.component';
import { CreateSectorComponent } from './components/sectors/create-sector/create-sector.component';

import { AppRoutingModule } from './app-routing.module';

import { AuthService } from './services/auth.service';

import { AuthInterceptor } from './interceptors/auth-interceptor';

import {Chart} from 'chart.js';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    CreateCompanyComponent,
    CompaniesComponent,
    StockExchangesComponent,
    ImportExcelComponent,
    ComparisonChartsComponent,
    NavbarComponent,
    NotFoundComponent,
    HomeComponent,
    IposComponent,
    CreateIpoComponent,
    CreateStockExchangeComponent,
    SectorsComponent,
    CreateSectorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FlashMessagesModule.forRoot()
  ],
  providers: [
    AuthService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    //{provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
