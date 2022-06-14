import { NgModule } from '@angular/core';

import { CouponsRoutingModule } from './coupons-routing.module';
import { ModalComponent } from './components/modal/modal.component';
import { PurchaseConfirmModalComponent } from './components/purchase-confirm-modal/purchase-confirm-modal.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [ModalComponent, PurchaseConfirmModalComponent],
  imports: [SharedModule, CouponsRoutingModule],
})
export class CouponsModule {}
