import { NgModule } from '@angular/core';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './components/customer/customer.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CouponsComponent } from './components/coupons/coupons.component';
import { MyCouponsComponent } from './components/my-coupons/my-coupons.component';
import { CouponsModule } from './components/coupons/coupons.module';
import { MyCouponsModule } from './components/my-coupons/my-coupons.module';

@NgModule({
  declarations: [
    CustomerComponent,
    ContentComponent,
    DashboardComponent,
    DetailsComponent,
    CouponsComponent,
    MyCouponsComponent,
  ],
  imports: [
    CustomerRoutingModule,
    SharedModule,
    CouponsModule,
    MyCouponsModule,
  ],
})
export class CustomerModule {}
