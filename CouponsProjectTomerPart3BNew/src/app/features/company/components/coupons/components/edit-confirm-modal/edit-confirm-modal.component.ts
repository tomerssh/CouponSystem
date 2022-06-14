import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Company } from 'src/app/shared/models/company.model';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { CompanyService } from 'src/app/shared/services/company/company.service';

@Component({
  selector: 'app-edit-confirm-modal',
  templateUrl: './edit-confirm-modal.component.html',
  styleUrls: ['./edit-confirm-modal.component.scss'],
})
export class EditConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private companyService: CompanyService
  ) {}

  ngOnInit(): void {}

  save() {
    let company: Company = JSON.parse(sessionStorage.getItem('client') || '');
    let startDateDate: Date = new Date(this.data.form.startDate);
    let endDateDate: Date = new Date(this.data.form.endDate);
    let coupon = new Coupon(
      this.data.element.id,
      company,
      this.data.form.category,
      this.data.form.title,
      this.data.form.description,
      startDateDate,
      endDateDate,
      this.data.form.amount,
      this.data.form.price,
      this.data.form.image
    );
    this.companyService.updateCoupon(coupon).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(this.data.element.title + ' updated');
      },
    });
  }
}
