import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerCouponService } from 'src/app/services/customer-coupon.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-customer-coupons-price',
  templateUrl: './get-customer-coupons-price.component.html',
  styleUrls: ['./get-customer-coupons-price.component.css'],
})
export class GetCustomerCouponsPriceComponent implements OnInit {
  public coupons?: Coupon[];
  customerCouponPurchased: any = {};
  msg: string = '';

  constructor(
    private customerService: CustomerService,
    private customerCouponService: CustomerCouponService
  ) {}

  ngOnInit(): void {}

  handleGetCustomerCouponsByMaxPrice(price: string) {
    let priceAsNumber = +price;
    let obs = this.customerService
      .getCustomerCouponsByMaxPrice(priceAsNumber)
      .subscribe({
        next: (coupons) => {
          this.coupons = coupons;
          for (let c of this.coupons) {
            this.handleGetCouponPurchaseAmount(c.id!);
          }
          this.customerCouponPurchased =
            this.customerCouponService.customerCouponPurchaseMap;
        },
        error: (e) => {
          let errAsObject = JSON.parse(e.error);
          this.msg = errAsObject.message;
        },
        complete: () => {
          obs.unsubscribe();
        },
      });
  }

  handleGetCouponPurchaseAmount(couponId: string) {
    let idAsNumber = +couponId;
    let obs = this.customerService
      .getCouponPurchaseAmount(idAsNumber)
      .subscribe({
        next: (amount) => {
          this.customerCouponService.customerCouponPurchaseMap[idAsNumber] =
            +amount.toString();
        },
        error: (e) => {
          // let errAsObject = JSON.parse(e.error);
          // this.msg = errAsObject.message;
          this.msg = e.error.message;
        },
        complete: () => {
          obs.unsubscribe();
        },
      });
  }
}
