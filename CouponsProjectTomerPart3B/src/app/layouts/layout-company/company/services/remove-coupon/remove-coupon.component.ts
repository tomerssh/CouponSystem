import { Component, OnInit } from '@angular/core';
import { Coupon } from 'src/app/models/coupon.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-remove-coupon',
  templateUrl: './remove-coupon.component.html',
  styleUrls: ['./remove-coupon.component.css'],
})
export class RemoveCouponComponent implements OnInit {
  public coupon = new Coupon();
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {}

  handleRemoveCoupon(id: string) {
    let idAsNumber: number = parseInt(id);
    let obs = this.companyService.removeCoupon(idAsNumber).subscribe({
      next: (title) => {
        this.coupon.title = title.toString();
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = this.coupon.title + ' removed';
        obs.unsubscribe();
      },
    });
  }
}
