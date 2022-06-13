import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Customer } from 'src/app/shared/models/customer.model';
import { AdminService } from 'src/app/shared/services/admin/admin.service';

@Component({
  selector: 'app-confirm-add-customer-modal',
  templateUrl: './confirm-add-customer-modal.component.html',
  styleUrls: ['./confirm-add-customer-modal.component.scss'],
})
export class ConfirmAddCustomerModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {}

  save() {
    let customer = new Customer(
      undefined,
      this.data.form.firstName,
      this.data.form.lastName,
      this.data.form.email,
      this.data.form.password
    );
    let name: string = `${customer.firstName} ${customer.lastName}`;
    this.adminService.addCustomer(customer).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(name + ' added');
      },
    });
  }
}
