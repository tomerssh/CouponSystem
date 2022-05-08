import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
import { AdminComponent } from './layouts/layout-admin/admin/admin.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSidenavModule } from '@angular/material/sidenav';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';

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
    AdminComponent,
    SidenavComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSidenavModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
