import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-coupons-category',
  templateUrl: './get-coupons-category.component.html',
  styleUrls: ['./get-coupons-category.component.css'],
})
export class GetCouponsCategoryComponent implements OnInit {
  public coupons?: Coupon[];
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  handleGetCompanyCouponsByCategory(category: string) {
    let obs = this.companyService
      .getCompanyCouponsByCategory(category)
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
