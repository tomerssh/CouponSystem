import { NgModule } from '@angular/core';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './components/company/company.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContentComponent } from './components/content/content.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DetailsComponent } from './components/details/details.component';
import { CouponsComponent } from './components/coupons/coupons.component';
import { AddCouponModalComponent } from './components/coupons/components/add-coupon-modal/add-coupon-modal.component';
import { ConfirmAddCouponModalComponent } from './components/coupons/components/confirm-add-coupon-modal/confirm-add-coupon-modal.component';
import { EditConfirmModalComponent } from './components/coupons/components/edit-confirm-modal/edit-confirm-modal.component';
import { ModalComponent } from './components/coupons/components/modal/modal.component';
import { RemoveConfirmModalComponent } from './components/coupons/components/remove-confirm-modal/remove-confirm-modal.component';

@NgModule({
  declarations: [CompanyComponent, ContentComponent, DashboardComponent, DetailsComponent, CouponsComponent, AddCouponModalComponent, ConfirmAddCouponModalComponent, EditConfirmModalComponent, ModalComponent, RemoveConfirmModalComponent],
  imports: [CompanyRoutingModule, SharedModule],
})
export class CompanyModule {}
