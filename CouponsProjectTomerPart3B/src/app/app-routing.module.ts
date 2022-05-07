import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
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
  { path: 'admin', component: LayoutAdminComponent, canActivate: [AuthGuard] },
  { path: 'company', component: LayoutCompanyComponent, canActivate: [AuthGuard] },
  { path: 'customer', component: LayoutCustomerComponent, canActivate: [AuthGuard] },
  { path: '**', component: LayoutPage404Component },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
