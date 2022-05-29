import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-coupons-price',
  templateUrl: './get-coupons-price.component.html',
  styleUrls: ['./get-coupons-price.component.css'],
})
export class GetCouponsPriceComponent implements OnInit {
  public coupons?: Coupon[];
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  handleGetCompanyCouponsByMaxPrice(price: string) {
    let priceAsNumber = +price;
    let obs = this.companyService
      .getCompanyCouponsByMaxPrice(priceAsNumber)
      .subscribe({
        next: (coupons) => {
          this.coupons = coupons;
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
}
