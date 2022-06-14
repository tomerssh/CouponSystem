import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CustomerCouponService {
  customerCouponPurchaseMap: any;

  constructor() {
    this.customerCouponPurchaseMap = {};
  }
}
