import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { AddCompanyComponent } from './layouts/layout-admin/admin/services/add-company/add-company.component';
import { GetCompanyComponent } from './layouts/layout-admin/admin/services/get-company/get-company.component';
import { RemoveCompanyComponent } from './layouts/layout-admin/admin/services/remove-company/remove-company.component';
import { UpdateCompanyComponent } from './layouts/layout-admin/admin/services/update-company/update-company.component';
import { DashboardAdminComponent } from './layouts/layout-admin/dashboard-admin/dashboard-admin.component';
import { LayoutAdminComponent } from './layouts/layout-admin/layout-admin.component';
import { LayoutCompanyComponent } from './layouts/layout-company/layout-company.component';
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
    ],
  },
  {
    path: 'company',
    component: LayoutCompanyComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'customer',
    component: LayoutCustomerComponent,
    canActivate: [AuthGuard],
  },
  { path: '**', component: LayoutPage404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
