import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/shared/models/customer.model';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
})
export class DetailsComponent implements OnInit {
  customer: Customer;
  name: string;

  constructor() {}

  ngOnInit(): void {
    this.customer = JSON.parse(sessionStorage.getItem('client') || '');
    this.name = `${this.customer.firstName} ${this.customer.lastName}`;
  }
}
