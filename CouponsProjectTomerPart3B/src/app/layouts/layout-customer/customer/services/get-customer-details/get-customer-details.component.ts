import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-get-customer-details',
  templateUrl: './get-customer-details.component.html',
  styleUrls: ['./get-customer-details.component.css'],
})
export class GetCustomerDetailsComponent implements OnInit {
  public customer?: Customer;
  msg: string = '';

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.handleGetCustomerDetails();
  }

  handleGetCustomerDetails() {
    let obs = this.customerService.getCustomerDetails().subscribe({
      next: (customer) => {
        this.customer = customer;
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
