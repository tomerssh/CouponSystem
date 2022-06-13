import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Customer } from 'src/app/shared/models/customer.model';
import { EditConfirmModalComponent } from '../edit-confirm-modal/edit-confirm-modal.component';
import { RemoveConfirmModalComponent } from '../remove-confirm-modal/remove-confirm-modal.component';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {
  elementToEdit: Customer;
  elementForm: FormGroup;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Customer,
    private dialog: MatDialog,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.elementToEdit = {
      id: this.data.id,
      firstName: this.data.firstName,
      lastName: this.data.lastName,
      email: this.data.email,
      password: this.data.password,
    };
    this.elementForm = this.formBuilder.group({
      email: [
        this.elementToEdit.email,
        [Validators.required, Validators.email],
      ],
      password: [this.elementToEdit.password, [Validators.required]],
    });
  }

  openEditDialog(form: any) {
    this.dialog.open(EditConfirmModalComponent, {
      data: {
        element: this.elementToEdit,
        form: form.value,
      },
    });
  }

  openRemoveDialog(form: any) {
    this.dialog.open(RemoveConfirmModalComponent, {
      data: {
        element: this.elementToEdit,
        form: form.value,
      },
    });
  }
}
