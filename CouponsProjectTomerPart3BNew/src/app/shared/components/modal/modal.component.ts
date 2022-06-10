import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PeriodicElement } from 'src/app/features/admin/components/companies/companies.component';
import { EditConfirmModalComponent } from '../edit-confirm-modal/edit-confirm-modal.component';
import { RemoveConfirmModalComponent } from '../remove-confirm-modal/remove-confirm-modal.component';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {
  elementToEdit: PeriodicElement = {
    position: this.data.position,
    name: this.data.name,
    weight: this.data.weight,
    symbol: this.data.symbol,
  };
  elementForm: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: PeriodicElement,
    private dialog: MatDialog,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.elementForm = this.formBuilder.group({
      position: [this.elementToEdit.position, [Validators.required]],
      name: [this.elementToEdit.name, [Validators.required]],
      weight: [this.elementToEdit.weight, [Validators.required]],
      symbol: [this.elementToEdit.symbol, [Validators.required]],
      // [Validators.email]
    });
  }

  // save() {
  // this.elementToEdit = this.elementForm.value;
  // this.data = this.elementToEdit;
  // }

  openEditDialog(form: any) {
    this.dialog.open(EditConfirmModalComponent, {
      data: {
        form: form.value,
      },
    });
  }

  openRemoveDialog(form: any) {
    this.dialog.open(RemoveConfirmModalComponent, {
      data: {
        form: form.value,
      },
    });
  }
}
