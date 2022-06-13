import { NgModule } from '@angular/core';
import { CustomersRoutingModule } from './customers-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { AddCustomerModalComponent } from './components/add-customer-modal/add-customer-modal.component';
import { ConfirmAddCustomerModalComponent } from './components/confirm-add-customer-modal/confirm-add-customer-modal.component';
import { EditConfirmModalComponent } from './components/edit-confirm-modal/edit-confirm-modal.component';
import { ModalComponent } from './components/modal/modal.component';
import { RemoveConfirmModalComponent } from './components/remove-confirm-modal/remove-confirm-modal.component';

@NgModule({
  declarations: [
    AddCustomerModalComponent,
    ConfirmAddCustomerModalComponent,
    EditConfirmModalComponent,
    ModalComponent,
    RemoveConfirmModalComponent,
  ],
  imports: [SharedModule, CustomersRoutingModule],
})
export class CustomersModule {}
