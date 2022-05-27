import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-add-coupon',
  templateUrl: './add-coupon.component.html',
  styleUrls: ['./add-coupon.component.css'],
})
export class AddCouponComponent implements OnInit {
  public coupon = new Coupon();
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  handleAddCoupon(
    category: string,
    title: string,
    description: string,
    startDate: string,
    endDate: string,
    amount: string,
    price: string,
    image: string
  ) {
    let coupon = new Coupon(
      undefined,
      undefined,
      category,
      title,
      description,
      startDate,
      endDate,
      amount,
      price,
      image
    );
    let obs = this.companyService.addCoupon(coupon).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = coupon.title + ' added';
        obs.unsubscribe();
      },
    });
  }
}
