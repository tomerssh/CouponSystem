import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-update-company',
  templateUrl: './update-company.component.html',
  styleUrls: ['./update-company.component.css'],
})
export class UpdateCompanyComponent implements OnInit {
  public company = new Company();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleUpdateCompany(email: string, id: string, password: string) {
    let company = new Company(email, id, undefined, password);
    let obs = this.adminService.updateCompany(company).subscribe({
      next: (name) => {
        company.name = name.toString();
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = company.name + ' updated';
        obs.unsubscribe();
      },
    });
  }
}
