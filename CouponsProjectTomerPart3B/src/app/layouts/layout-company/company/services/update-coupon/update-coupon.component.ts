import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-update-coupon',
  templateUrl: './update-coupon.component.html',
  styleUrls: ['./update-coupon.component.css'],
})
export class UpdateCouponComponent implements OnInit {
  public coupon = new Coupon();
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  getCouponById(id: string) {
    let obs = this.companyService.getCouponById(id).subscribe({
      next: (coupon) => {
        this.coupon = coupon;
      },
      error: (e) => {
        this.coupon = new Coupon();
        this.msg = e.error.message;
      },
      complete: () => {
        this.msg = '';
        obs.unsubscribe();
      },
    });
  }

  handleUpdateCoupon(
    id: string,
    category: string,
    title: string,
    description: string,
    startDate: string,
    endDate: string,
    amount: string,
    price: string,
    image: string
  ) {
    let startDateDate: Date = new Date(startDate);
    let endDateDate: Date = new Date(endDate);
    let coupon = new Coupon(
      id,
      undefined,
      category,
      title,
      description,
      startDateDate,
      endDateDate,
      amount,
      price,
      image
    );
    let obs = this.companyService.updateCoupon(coupon).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = coupon.title + ' updated';
        obs.unsubscribe();
      },
    });
  }
}
