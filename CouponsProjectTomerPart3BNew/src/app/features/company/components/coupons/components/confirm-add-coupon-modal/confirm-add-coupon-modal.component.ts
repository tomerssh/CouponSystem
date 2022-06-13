import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-add-coupon-modal',
  templateUrl: './confirm-add-coupon-modal.component.html',
  styleUrls: ['./confirm-add-coupon-modal.component.scss'],
})
export class ConfirmAddCouponModalComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {}

  save() {
    console.log('adding');
    console.log(this.data.form);
  }
}
