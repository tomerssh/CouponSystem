import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './components/admin/admin.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { MaterialModule } from 'src/app/material.module';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { ModalComponent } from '../../shared/components/modal/modal.component';
import { CompaniesComponent } from './components/companies/companies.component';
import { CustomersComponent } from './components/customers/customers.component';

@NgModule({
  declarations: [
    AdminComponent,
    ContentComponent,
    DashboardComponent,
    DetailsComponent,
    ModalComponent,
    CompaniesComponent,
    CustomersComponent,
  ],
  imports: [CommonModule, AdminRoutingModule, SharedModule, MaterialModule],
})
export class AdminModule {}
