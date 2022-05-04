import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './layouts/layout-dashboard/dashboard/dashboard.component';
import { LoginComponent } from './layouts/layout-login/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { LayoutAdminComponent } from './layouts/layout-admin/layout-admin.component';
import { NavbarAdminComponent } from './layouts/layout-admin/navbar-admin/navbar-admin.component';
import { LayoutCompanyComponent } from './layouts/layout-company/layout-company.component';
import { NavbarCompanyComponent } from './layouts/layout-company/navbar-company/navbar-company.component';
import { LayoutCustomerComponent } from './layouts/layout-customer/layout-customer.component';
import { NavbarCustomerComponent } from './layouts/layout-customer/navbar-customer/navbar-customer.component';
import { LayoutLoginComponent } from './layouts/layout-login/layout-login.component';
import { LayoutDashboardComponent } from './layouts/layout-dashboard/layout-dashboard.component';
import { NavbarDashboardComponent } from './layouts/layout-dashboard/navbar-dashboard/navbar-dashboard.component';
import { LayoutPage404Component } from './layouts/layout-page404/layout-page404.component';
import { NavbarLoginComponent } from './layouts/layout-login/navbar-login/navbar-login.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    LayoutAdminComponent,
    NavbarAdminComponent,
    LayoutCompanyComponent,
    NavbarCompanyComponent,
    LayoutCustomerComponent,
    NavbarCustomerComponent,
    LayoutLoginComponent,
    LayoutDashboardComponent,
    NavbarDashboardComponent,
    LayoutPage404Component,
    NavbarLoginComponent,
  ],
  imports: [BrowserModule, AppRoutingModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}