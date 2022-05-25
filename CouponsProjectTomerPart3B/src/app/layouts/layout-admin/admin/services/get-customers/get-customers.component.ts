import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/models/customer.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-get-customers',
  templateUrl: './get-customers.component.html',
  styleUrls: ['./get-customers.component.css'],
})
export class GetCustomersComponent implements OnInit {
  public customers?: Customer[];
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.handleGetCustomers();
  }

  handleGetCustomers() {
    let obs = this.adminService.getCustomers().subscribe({
      next: (arr) => {
        this.customers = arr;
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
