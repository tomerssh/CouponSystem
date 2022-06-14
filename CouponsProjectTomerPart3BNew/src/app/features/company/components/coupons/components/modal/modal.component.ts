import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { EditConfirmModalComponent } from '../edit-confirm-modal/edit-confirm-modal.component';
import { RemoveConfirmModalComponent } from '../remove-confirm-modal/remove-confirm-modal.component';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {
  elementToEdit: Coupon;
  elementForm: FormGroup;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Coupon,
    private dialog: MatDialog,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.elementToEdit = {
      id: this.data.id,
      category: this.data.category,
      title: this.data.title,
      description: this.data.description,
      startDate: this.data.startDate,
      endDate: this.data.endDate,
      amount: this.data.amount,
      price: this.data.price,
      image: this.data.image,
    };
    this.elementForm = this.formBuilder.group({
      category: [this.elementToEdit.category, [Validators.required]],
      title: [this.elementToEdit.title, [Validators.required]],
      description: [this.elementToEdit.description, [Validators.required]],
      startDate: [this.elementToEdit.startDate, [Validators.required]],
      endDate: [this.elementToEdit.endDate, [Validators.required]],
      amount: [this.elementToEdit.amount, [Validators.required]],
      price: [this.elementToEdit.price, [Validators.required]],
      image: [this.elementToEdit.image, [Validators.required]],
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
