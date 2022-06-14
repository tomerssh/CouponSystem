import { NgModule } from '@angular/core';

import { MyCouponsRoutingModule } from './my-coupons-routing.module';
import { ModalComponent } from './components/modal/modal.component';
import { RemoveConfirmModalComponent } from './components/remove-confirm-modal/remove-confirm-modal.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [ModalComponent, RemoveConfirmModalComponent],
  imports: [SharedModule, MyCouponsRoutingModule],
})
export class MyCouponsModule {}
