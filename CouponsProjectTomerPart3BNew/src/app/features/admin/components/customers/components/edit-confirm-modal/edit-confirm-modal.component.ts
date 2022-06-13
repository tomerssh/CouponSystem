import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Customer } from 'src/app/shared/models/customer.model';
import { AdminService } from 'src/app/shared/services/admin/admin.service';

@Component({
  selector: 'app-edit-confirm-modal',
  templateUrl: './edit-confirm-modal.component.html',
  styleUrls: ['./edit-confirm-modal.component.scss'],
})
export class EditConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {}

  save() {
    let customer = new Customer(
      this.data.element.id,
      this.data.form.firstName,
      this.data.form.lastName,
      this.data.form.email,
      this.data.form.password
    );
    let name: string = `${this.data.element.firstName} ${this.data.element.lastName}`;
    this.adminService.updateCustomer(customer).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(name + ' updated');
      },
    });
  }
}
