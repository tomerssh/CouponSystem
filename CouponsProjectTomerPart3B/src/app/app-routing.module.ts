import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { AddCompanyComponent } from './layouts/layout-admin/admin/services/add-company/add-company.component';
import { AddCustomerComponent } from './layouts/layout-admin/admin/services/add-customer/add-customer.component';
import { GetCompanyComponent } from './layouts/layout-admin/admin/services/get-company/get-company.component';
import { GetCustomerComponent } from './layouts/layout-admin/admin/services/get-customer/get-customer.component';
import { RemoveCompanyComponent } from './layouts/layout-admin/admin/services/remove-company/remove-company.component';
import { RemoveCustomerComponent } from './layouts/layout-admin/admin/services/remove-customer/remove-customer.component';
import { UpdateCompanyComponent } from './layouts/layout-admin/admin/services/update-company/update-company.component';
import { UpdateCustomerComponent } from './layouts/layout-admin/admin/services/update-customer/update-customer.component';
import { DashboardAdminComponent } from './layouts/layout-admin/dashboard-admin/dashboard-admin.component';
import { LayoutAdminComponent } from './layouts/layout-admin/layout-admin.component';
import { AddCouponComponent } from './layouts/layout-company/company/services/add-coupon/add-coupon.component';
import { GetCouponsCategoryComponent } from './layouts/layout-company/company/services/get-coupons-category/get-coupons-category.component';
import { GetCouponsPriceComponent } from './layouts/layout-company/company/services/get-coupons-price/get-coupons-price.component';
import { RemoveCouponComponent } from './layouts/layout-company/company/services/remove-coupon/remove-coupon.component';
import { UpdateCouponComponent } from './layouts/layout-company/company/services/update-coupon/update-coupon.component';
import { DashboardCompanyComponent } from './layouts/layout-company/dashboard-company/dashboard-company.component';
import { LayoutCompanyComponent } from './layouts/layout-company/layout-company.component';
import { GetCustomerCouponsCategoryComponent } from './layouts/layout-customer/customer/services/get-customer-coupons-category/get-customer-coupons-category.component';
import { GetCustomerCouponsPriceComponent } from './layouts/layout-customer/customer/services/get-customer-coupons-price/get-customer-coupons-price.component';
import { PurchaseCouponComponent } from './layouts/layout-customer/customer/services/purchase-coupon/purchase-coupon.component';
import { DashboardCustomerComponent } from './layouts/layout-customer/dashboard-customer/dashboard-customer.component';
import { LayoutCustomerComponent } from './layouts/layout-customer/layout-customer.component';
import { LayoutDashboardComponent } from './layouts/layout-dashboard/layout-dashboard.component';
import { LayoutLoginComponent } from './layouts/layout-login/layout-login.component';
import { LayoutPage404Component } from './layouts/layout-page404/layout-page404.component';

const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: LayoutDashboardComponent },
  { path: 'login', component: LayoutLoginComponent },
  {
    path: 'admin',
    component: LayoutAdminComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        children: [
          { path: '', component: DashboardAdminComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'add/company',
        children: [
          { path: '', component: AddCompanyComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'update/company',
        children: [
          { path: '', component: UpdateCompanyComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'remove/company',
        children: [
          { path: '', component: RemoveCompanyComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'get/company',
        children: [
          { path: '', component: GetCompanyComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'add/customer',
        children: [
          { path: '', component: AddCustomerComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'update/customer',
        children: [
          { path: '', component: UpdateCustomerComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'remove/customer',
        children: [
          { path: '', component: RemoveCustomerComponent, outlet: 'admin' },
        ],
      },
      {
        path: 'get/customer',
        children: [
          { path: '', component: GetCustomerComponent, outlet: 'admin' },
        ],
      },
    ],
  },
  {
    path: 'company',
    component: LayoutCompanyComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        children: [
          { path: '', component: DashboardCompanyComponent, outlet: 'company' },
        ],
      },
      {
        path: 'add/coupon',
        children: [
          { path: '', component: AddCouponComponent, outlet: 'company' },
        ],
      },
      {
        path: 'update/coupon',
        children: [
          { path: '', component: UpdateCouponComponent, outlet: 'company' },
        ],
      },
      {
        path: 'remove/coupon',
        children: [
          { path: '', component: RemoveCouponComponent, outlet: 'company' },
        ],
      },
      {
        path: 'get/coupon/category',
        children: [
          {
            path: '',
            component: GetCouponsCategoryComponent,
            outlet: 'company',
          },
        ],
      },
      {
        path: 'get/coupon/price',
        children: [
          { path: '', component: GetCouponsPriceComponent, outlet: 'company' },
        ],
      },
    ],
  },
  {
    path: 'customer',
    component: LayoutCustomerComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      {
        path: 'dashboard',
        children: [
          {
            path: '',
            component: DashboardCustomerComponent,
            outlet: 'customer',
          },
        ],
      },
      {
        path: 'purchase/coupon',
        children: [
          { path: '', component: PurchaseCouponComponent, outlet: 'customer' },
        ],
      },
      {
        path: 'get/coupon/category',
        children: [
          {
            path: '',
            component: GetCustomerCouponsCategoryComponent,
            outlet: 'customer',
          },
        ],
      },
      {
        path: 'get/coupon/price',
        children: [
          {
            path: '',
            component: GetCustomerCouponsPriceComponent,
            outlet: 'customer',
          },
        ],
      },
    ],
  },
  { path: '**', component: LayoutPage404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
