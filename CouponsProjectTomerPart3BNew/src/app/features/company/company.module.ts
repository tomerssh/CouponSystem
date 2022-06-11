import { NgModule } from '@angular/core';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './components/company/company.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CouponsComponent } from './components/coupons/coupons.component';

@NgModule({
  declarations: [CompanyComponent, ContentComponent, DashboardComponent, DetailsComponent, CouponsComponent],
  imports: [CompanyRoutingModule, SharedModule],
})
export class CompanyModule {}
