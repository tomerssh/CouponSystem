import { NgModule } from '@angular/core';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './components/admin/admin.component';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { CustomersComponent } from './components/customers/customers.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CompaniesModule } from './components/companies/companies.module';
import { CustomersModule } from './components/customers/customers.module';

@NgModule({
  declarations: [
    AdminComponent,
    ContentComponent,
    DashboardComponent,
    DetailsComponent,
    CompaniesComponent,
    CustomersComponent,
  ],
  imports: [AdminRoutingModule, SharedModule, CompaniesModule, CustomersModule],
})
export class AdminModule {}
