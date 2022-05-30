import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-all-coupons',
  templateUrl: './get-all-coupons.component.html',
  styleUrls: ['./get-all-coupons.component.css'],
})
export class GetAllCouponsComponent implements OnInit {
  public coupons: Coupon[] = [];
  msg: string = '';

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.handleGetAllCoupons();
  }

  handleGetAllCoupons() {
    let obs = this.customerService.getAllCoupons().subscribe({
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
