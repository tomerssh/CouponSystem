import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-get-company',
  templateUrl: './get-company.component.html',
  styleUrls: ['./get-company.component.css'],
})
export class GetCompanyComponent implements OnInit {
  public company = new Company();
  public result = new Company();
  msg?: string;

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleGetCompany(id: string) {
    let idAsNumber: number = parseInt(id);
    let obs = this.adminService.getCompany(idAsNumber).subscribe({
      next: (company) => {
        this.result = company;
      },
      error: (e) => {
        this.msg = e.error.message;
      },
      complete: () => {
        this.msg = undefined;
        obs.unsubscribe();
      },
    });
  }
}
