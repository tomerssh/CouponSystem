import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { RouterModule } from '@angular/router';
import { FooterComponent } from './components/footer/footer.component';
import { EditConfirmModalComponent } from './components/edit-confirm-modal/edit-confirm-modal.component';
import { RemoveConfirmModalComponent } from './components/remove-confirm-modal/remove-confirm-modal.component';
import { MaterialModule } from '../material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ModalComponent } from './components/modal/modal.component';

@NgModule({
  declarations: [
    HeaderComponent,
    FooterComponent,
    ModalComponent,
    EditConfirmModalComponent,
    RemoveConfirmModalComponent,
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MaterialModule,
    RouterModule,
  ],
  exports: [
    CommonModule,
    RouterModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HeaderComponent,
    FooterComponent,
    EditConfirmModalComponent,
    RemoveConfirmModalComponent,
  ],
  providers: [],
})
export class SharedModule {}
