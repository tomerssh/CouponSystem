import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-edit-confirm-modal',
  templateUrl: './edit-confirm-modal.component.html',
  styleUrls: ['./edit-confirm-modal.component.scss'],
})
export class EditConfirmModalComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {}

  save() {
    console.log(this.data.form);
  }
}
