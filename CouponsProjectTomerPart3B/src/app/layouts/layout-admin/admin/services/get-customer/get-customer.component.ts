import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-get-customer',
  templateUrl: './get-customer.component.html',
  styleUrls: ['./get-customer.component.css'],
})
export class GetCustomerComponent implements OnInit {
  public customer = new Customer();
  public result = new Customer();
  msg?: string;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleGetCustomer(id: string) {
    let idAsNumber: number = parseInt(id);
    let obs = this.adminService.getCustomer(idAsNumber).subscribe({
      next: (customer) => {
        this.result = customer;
      },
      error: (e) => {
        this.msg = e.error.message;
      },
      complete: () => {
        this.msg = undefined;
        obs.unsubscribe();
      },
    });
  }
}
