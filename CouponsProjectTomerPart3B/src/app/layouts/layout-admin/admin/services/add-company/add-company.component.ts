import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-add-company',
  templateUrl: './add-company.component.html',
  styleUrls: ['./add-company.component.css'],
})
export class AddCompanyComponent implements OnInit {
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleAddCompany(email: string, name: string, password: string) {
    let company = new Company(email, 0, name, password);
    let obs = this.adminService.addCompany(company).subscribe({
      next: (id) => {
        company.id = parseInt(id.toString());
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = 'Company ' + company.name + ' added';
        obs.unsubscribe();
      },
    });
  }
}
