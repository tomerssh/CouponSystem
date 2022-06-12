import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AdminService } from 'src/app/shared/services/admin/admin.service';
import { Company } from 'src/app/shared/models/company.model';

@Component({
  selector: 'app-confirm-add-company-modal',
  templateUrl: './confirm-add-company-modal.component.html',
  styleUrls: ['./confirm-add-company-modal.component.scss'],
})
export class ConfirmAddCompanyModalComponent implements OnInit {
  msg: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminService
  ) {}

  ngOnInit(): void {}

  save() {
    let company = new Company(
      this.data.form.email,
      undefined,
      this.data.form.name,
      this.data.form.password
    );
    this.adminService.addCompany(company).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
      complete: () => {
        alert(company.name + ' added');
      },
    });
  }
}
