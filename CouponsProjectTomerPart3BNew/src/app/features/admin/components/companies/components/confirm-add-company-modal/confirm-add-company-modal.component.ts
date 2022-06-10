import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-confirm-add-company-modal',
  templateUrl: './confirm-add-company-modal.component.html',
  styleUrls: ['./confirm-add-company-modal.component.scss'],
})
export class ConfirmAddCompanyModalComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {}

  save() {
    console.log('adding');
    console.log(this.data.form);
  }
}
