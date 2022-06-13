import { NgModule } from '@angular/core';
import { CompaniesRoutingModule } from './companies-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { AddCompanyModalComponent } from './components/add-company-modal/add-company-modal.component';
import { ConfirmAddCompanyModalComponent } from './components/confirm-add-company-modal/confirm-add-company-modal.component';
import { EditConfirmModalComponent } from './components/edit-confirm-modal/edit-confirm-modal.component';
import { ModalComponent } from './components/modal/modal.component';
import { RemoveConfirmModalComponent } from './components/remove-confirm-modal/remove-confirm-modal.component';

@NgModule({
  declarations: [
    AddCompanyModalComponent,
    ConfirmAddCompanyModalComponent,
    EditConfirmModalComponent,
    ModalComponent,
    RemoveConfirmModalComponent,
  ],
  imports: [SharedModule, CompaniesRoutingModule],
})
export class CompaniesModule {}
