import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css'],
})
export class AddCompanyComponent implements OnInit {
  public company = new Company();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleAddCompany(email: string, name: string, password: string) {
    let company = new Company(email, undefined, name, password);
    let obs = this.adminService.addCompany(company).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = company.name + ' added';
        obs.unsubscribe();
      },
    });
  }
}
