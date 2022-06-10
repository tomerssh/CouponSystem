import { NgModule } from '@angular/core';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './components/admin/admin.component';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { CustomersComponent } from './components/customers/customers.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { AddCompanyModalComponent } from './components/companies/components/add-company-modal/add-company-modal.component';
import { ConfirmAddCompanyModalComponent } from './components/companies/components/confirm-add-company-modal/confirm-add-company-modal.component';

@NgModule({
  declarations: [
    AdminComponent,
    ContentComponent,
    DashboardComponent,
    DetailsComponent,
    CompaniesComponent,
    CustomersComponent,
    AddCompanyModalComponent,
    ConfirmAddCompanyModalComponent,
  ],
  imports: [AdminRoutingModule, SharedModule],
})
export class AdminModule {}
