import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/models/company.model';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-remove-company',
  templateUrl: './remove-company.component.html',
  styleUrls: ['./remove-company.component.css'],
})
export class RemoveCompanyComponent implements OnInit {
  public company = new Company();
  msg: string = '';

  constructor(private adminService: AdminService) {}

  ngOnInit(): void {}

  handleRemoveCompany(id: string) {
    let company = new Company(undefined, id, undefined, undefined);
    let idAsNumber: number = parseInt(id);
    let obs = this.adminService.removeCompany(idAsNumber).subscribe({
      next: (name) => {
        company.name = name.toString();
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.msg = errAsObject.message;
      },
      complete: () => {
        this.msg = company.name + ' removed';
        obs.unsubscribe();
      },
    });
  }
}
