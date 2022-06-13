import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdminService } from 'src/app/shared/services/admin/admin.service';

@Component({
  selector: 'app-remove-confirm-modal',
  templateUrl: './remove-confirm-modal.component.html',
  styleUrls: ['./remove-confirm-modal.component.scss'],
})
export class RemoveConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {}

  remove() {
    this.adminService.removeCompany(this.data.element.id).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(this.data.element.name + ' removed');
      },
    });
  }
}
