import { Component, LOCALE_ID, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-coupons',
  templateUrl: './get-coupons.component.html',
  styleUrls: ['./get-coupons.component.css'],
})
export class GetCouponsComponent implements OnInit {
  public coupons?: Coupon[];
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.handleGetCompanyCoupons();
  }

  handleGetCompanyCoupons() {
    let obs = this.companyService.getCompanyCoupons().subscribe({
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
