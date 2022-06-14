import { Injectable } from '@angular/core';
import { CustomerService } from './customer.service';

@Injectable({
  providedIn: 'root',
})
export class CustomerCouponService {
  customerCouponPurchaseMap: any;

  constructor(private customerService: CustomerService) {
    this.customerCouponPurchaseMap = {};
  }

  updateCouponPurchaseAmount(couponId: string) {
    let idAsNumber: number = +couponId;
    this.customerService.getCouponPurchaseAmount(idAsNumber).subscribe({
      next: (amount) => {
        this.customerCouponPurchaseMap[idAsNumber] = amount;
      },
    });
  }
}
