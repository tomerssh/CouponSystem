import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css'],
})
export class UpdateCustomerComponent implements OnInit {
  public customer = new Customer();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleUpdateCustomer(id: string, email: string, password: string) {
    let customer = new Customer(id, undefined, undefined, email, password);
    let obs = this.adminService.updateCustomer(customer).subscribe({
      next: (name) => {
        let fullNameArray = name.toString().split(' ');
        customer.firstName = fullNameArray[0];
        customer.lastName = fullNameArray[1];
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = customer.firstName + ' ' + customer.lastName + ' updated';
        obs.unsubscribe();
      },
    });
  }
}
