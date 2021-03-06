import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CustomerCouponService } from 'src/app/shared/services/customer/customer-coupon.service';
import { CustomerService } from 'src/app/shared/services/customer/customer.service';

@Component({
  selector: 'app-purchase-confirm-modal',
  templateUrl: './purchase-confirm-modal.component.html',
  styleUrls: ['./purchase-confirm-modal.component.scss'],
})
export class PurchaseConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private customerService: CustomerService,
    private customerCouponService: CustomerCouponService
  ) {}

  ngOnInit(): void {}

  purchase() {
    this.customerService.purchaseCoupon(this.data.element.id).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(this.data.element.title + ' purchased successfully');
        this.customerCouponService.updateCouponPurchaseAmount(
          this.data.element.id
        );
      },
    });
  }
}
