import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerCouponService } from 'src/app/services/customer-coupon.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-purchase-coupon',
  templateUrl: './purchase-coupon.component.html',
  styleUrls: ['./purchase-coupon.component.css'],
})
export class PurchaseCouponComponent implements OnInit {
  public coupon = new Coupon();
  msg: string = '';

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {}

  handlePurchaseCoupon(id: string) {
    let idAsNumber = +id;
    let obs = this.customerService.purchaseCoupon(idAsNumber).subscribe({
      next: (title) => {
        this.coupon.title = title.toString();
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = this.coupon.title + ' purchased';
        obs.unsubscribe();
      },
    });
  }
}
