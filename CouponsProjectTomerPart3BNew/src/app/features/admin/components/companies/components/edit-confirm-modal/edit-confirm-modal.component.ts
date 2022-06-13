import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Company } from 'src/app/shared/models/company.model';
import { AdminService } from 'src/app/shared/services/admin/admin.service';

@Component({
  selector: 'app-edit-confirm-modal',
  templateUrl: './edit-confirm-modal.component.html',
  styleUrls: ['./edit-confirm-modal.component.scss'],
})
export class EditConfirmModalComponent implements OnInit {
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {}

  save() {
    let company = new Company(
      this.data.form.email,
      this.data.element.id,
      undefined,
      this.data.form.password
    );
    this.adminService.updateCompany(company).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(this.data.element.name + ' updated');
      },
    });
  }
}
