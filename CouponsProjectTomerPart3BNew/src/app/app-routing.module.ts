import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', loadChildren: () => import('./features/home/home.module').then(o => o.HomeModule) },
  { path: 'login', loadChildren: () => import('./features/login/login.module').then(o => o.LoginModule) },
  { path: 'admin', loadChildren: () => import('./features/admin/admin.module').then(o => o.AdminModule) },
  { path: 'company', loadChildren: () => import('./features/company/company.module').then(o => o.CompanyModule) },
  { path: 'customer', loadChildren: () => import('./features/customer/customer.module').then(o => o.CustomerModule) },
  { path: '**', loadChildren: () => import('./features/page404/page404.module').then(o => o.Page404Module) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
