import { NgModule } from '@angular/core';

import { CustomerRoutingModule } from './customer-routing.module';
import { CustomerComponent } from './components/customer/customer.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CouponsComponent } from './components/coupons/coupons.component';
import { ModalComponent } from './components/coupons/components/modal/modal.component';
import { PurchaseConfirmModalComponent } from './components/coupons/components/purchase-confirm-modal/purchase-confirm-modal.component';
import { RemoveConfirmModalComponent } from './components/coupons/components/remove-confirm-modal/remove-confirm-modal.component';

@NgModule({
  declarations: [CustomerComponent, ContentComponent, DashboardComponent, DetailsComponent, CouponsComponent, ModalComponent, PurchaseConfirmModalComponent, RemoveConfirmModalComponent],
  imports: [CustomerRoutingModule, SharedModule],
})
export class CustomerModule {}
