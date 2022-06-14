import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { RemoveConfirmModalComponent } from '../remove-confirm-modal/remove-confirm-modal.component';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.scss'],
})
export class ModalComponent implements OnInit {
  element: Coupon;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Coupon,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.element = {
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
  }

  openRemoveDialog() {
    this.dialog.open(RemoveConfirmModalComponent, {
      data: {
        element: this.element,
      },
    });
  }
}
