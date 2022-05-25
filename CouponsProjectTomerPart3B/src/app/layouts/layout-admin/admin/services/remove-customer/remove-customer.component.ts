import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-remove-customer',
  templateUrl: './remove-customer.component.html',
  styleUrls: ['./remove-customer.component.css'],
})
export class RemoveCustomerComponent implements OnInit {
  public customer = new Customer();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleRemoveCustomer(id: string) {
    let customer = new Customer(id, undefined, undefined, undefined, undefined);
    let idAsNumber: number = parseInt(id);
    let obs = this.adminService.removeCustomer(idAsNumber).subscribe({
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
        this.msg = customer.firstName + ' ' + customer.lastName + ' removed';
        obs.unsubscribe();
      },
    });
  }
}
