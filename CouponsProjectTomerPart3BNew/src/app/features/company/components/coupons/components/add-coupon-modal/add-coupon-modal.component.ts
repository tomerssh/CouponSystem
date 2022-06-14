import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmAddCouponModalComponent } from '../confirm-add-coupon-modal/confirm-add-coupon-modal.component';

@Component({
  selector: 'app-add-coupon-modal',
  templateUrl: './add-coupon-modal.component.html',
  styleUrls: ['./add-coupon-modal.component.scss'],
})
export class AddCouponModalComponent implements OnInit {
  elementForm: FormGroup;

  constructor(private dialog: MatDialog, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.elementForm = this.formBuilder.group({
      category: ['', [Validators.required]],
      title: ['', [Validators.required]],
      description: ['', [Validators.required]],
      startDate: ['', [Validators.required]],
      endDate: ['', [Validators.required]],
      amount: ['', [Validators.required]],
      price: ['', [Validators.required]],
      image: ['', [Validators.required]],
    });
  }

  save() {
    console.log(this.elementForm.value);
  }

  openConfirmAddDialog(form: any) {
    this.dialog.open(ConfirmAddCouponModalComponent, {
      data: {
        form: form.value,
      },
    });
  }
}
