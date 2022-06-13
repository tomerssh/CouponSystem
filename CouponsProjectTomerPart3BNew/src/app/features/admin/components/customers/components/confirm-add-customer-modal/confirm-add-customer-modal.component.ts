import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-add-customer-modal',
  templateUrl: './confirm-add-customer-modal.component.html',
  styleUrls: ['./confirm-add-customer-modal.component.scss'],
})
export class ConfirmAddCustomerModalComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {}

  save() {
    console.log('adding');
    console.log(this.data.form);
  }
}
