import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent implements OnInit {
  public customer = new Customer();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleAddCustomer(firstName: string, lastName:string, email: string, password: string) {
    let customer = new Customer(undefined, firstName, lastName, email, password);
    let obs = this.adminService.addCustomer(customer).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = customer.firstName + ' ' + customer.lastName + ' added';
        obs.unsubscribe();
      },
    });
  }
}
