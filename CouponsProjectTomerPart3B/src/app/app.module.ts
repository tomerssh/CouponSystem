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
import { CompanyComponent } from './layouts/layout-company/company/company.component';
import { CustomerComponent } from './layouts/layout-customer/customer/customer.component';
import { DashboardAdminComponent } from './layouts/layout-admin/dashboard-admin/dashboard-admin.component';
import { AddCompanyComponent } from './layouts/layout-admin/admin/services/add-company/add-company.component';
import { UpdateCompanyComponent } from './layouts/layout-admin/admin/services/update-company/update-company.component';
import { RemoveCompanyComponent } from './layouts/layout-admin/admin/services/remove-company/remove-company.component';
import { GetCompaniesComponent } from './layouts/layout-admin/admin/services/get-companies/get-companies.component';
import { GetCompanyComponent } from './layouts/layout-admin/admin/services/get-company/get-company.component';
import { GetCustomersComponent } from './layouts/layout-admin/admin/services/get-customers/get-customers.component';
import { AddCustomerComponent } from './layouts/layout-admin/admin/services/add-customer/add-customer.component';
import { UpdateCustomerComponent } from './layouts/layout-admin/admin/services/update-customer/update-customer.component';
import { RemoveCustomerComponent } from './layouts/layout-admin/admin/services/remove-customer/remove-customer.component';
import { GetCustomerComponent } from './layouts/layout-admin/admin/services/get-customer/get-customer.component';
import { DashboardCompanyComponent } from './layouts/layout-company/dashboard-company/dashboard-company.component';
import { AddCouponComponent } from './layouts/layout-company/company/services/add-coupon/add-coupon.component';
import { UpdateCouponComponent } from './layouts/layout-company/company/services/update-coupon/update-coupon.component';
import { GetCouponsComponent } from './layouts/layout-company/company/services/get-coupons/get-coupons.component';
import { GetCouponsCategoryComponent } from './layouts/layout-company/company/services/get-coupons-category/get-coupons-category.component';
import { GetCouponsPriceComponent } from './layouts/layout-company/company/services/get-coupons-price/get-coupons-price.component';
import { RemoveCouponComponent } from './layouts/layout-company/company/services/remove-coupon/remove-coupon.component';
import { GetCompanyDetailsComponent } from './layouts/layout-company/company/services/get-company-details/get-company-details.component';

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
    CompanyComponent,
    CustomerComponent,
    DashboardAdminComponent,
    AddCompanyComponent,
    UpdateCompanyComponent,
    RemoveCompanyComponent,
    GetCompaniesComponent,
    GetCompanyComponent,
    GetCustomersComponent,
    AddCustomerComponent,
    UpdateCustomerComponent,
    RemoveCustomerComponent,
    GetCustomerComponent,
    DashboardCompanyComponent,
    AddCouponComponent,
    UpdateCouponComponent,
    GetCouponsComponent,
    GetCouponsCategoryComponent,
    GetCouponsPriceComponent,
    RemoveCouponComponent,
    GetCompanyDetailsComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
