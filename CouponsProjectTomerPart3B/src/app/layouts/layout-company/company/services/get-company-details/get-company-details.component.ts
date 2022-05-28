import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-get-company-details',
  templateUrl: './get-company-details.component.html',
  styleUrls: ['./get-company-details.component.css'],
})
export class GetCompanyDetailsComponent implements OnInit {
  public company?: Company;
  msg: string = '';

  constructor(private companyService: CompanyService) {}

  ngOnInit(): void {
    this.handleGetCompanyDetails();
  }

  handleGetCompanyDetails() {
    let obs = this.companyService.getCompanyDetails().subscribe({
      next: (company) => {
        this.company = company;
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
