import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-get-companies',
  templateUrl: './get-companies.component.html',
  styleUrls: ['./get-companies.component.css'],
})
export class GetCompaniesComponent implements OnInit {
  public companies?: Company[];
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {
    this.handleGetCompanies();
  }

  handleGetCompanies() {
    let obs = this.adminService.getCompanies().subscribe({
      next: (arr) => {
        this.companies = arr;
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        obs.unsubscribe();
      },
    });
  }
}
