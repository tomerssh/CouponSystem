import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-remove-confirm-modal',
  templateUrl: './remove-confirm-modal.component.html',
  styleUrls: ['./remove-confirm-modal.component.scss'],
})
export class RemoveConfirmModalComponent implements OnInit {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}

  ngOnInit(): void {}

  remove() {
    console.log('removing');
    console.log(this.data.form);
  }
}
