import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CustomerCouponService } from 'src/app/shared/services/customer/customer-coupon.service';
import { CustomerService } from 'src/app/shared/services/customer/customer.service';

@Component({
  selector: 'app-remove-confirm-modal',
  templateUrl: './remove-confirm-modal.component.html',
  styleUrls: ['./remove-confirm-modal.component.scss'],
})
export class RemoveConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private customerService: CustomerService,
    private customerCouponService: CustomerCouponService
  ) {}

  ngOnInit(): void {}

  remove() {
    this.customerService.removeCouponPurchase(this.data.element.id).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(this.data.element.title + ' removed successfully');
        this.customerCouponService.updateCouponPurchaseAmount(
          this.data.element.id
        );
      },
    });
  }
}
