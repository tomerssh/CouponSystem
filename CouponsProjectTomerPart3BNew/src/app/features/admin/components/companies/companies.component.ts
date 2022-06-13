import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Company } from 'src/app/shared/models/company.model';
import { AdminService } from 'src/app/shared/services/admin/admin.service';
import { AddCompanyModalComponent } from './components/add-company-modal/add-company-modal.component';
import { ModalComponent } from './components/modal/modal.component';

@Component({
  selector: 'app-companies',
  templateUrl: './companies.component.html',
  styleUrls: ['./companies.component.scss'],
})
export class CompaniesComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = ['id', 'name', 'email', 'password'];
  dataSource: MatTableDataSource<Company>;

  constructor(private dialog: MatDialog, private adminService: AdminService) {}

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<Company>();
    this.getCompanies();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  async getCompanies() {
    this.adminService.getCompanies().subscribe({
      next: (companies) => {
        this.dataSource.data = companies;
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
    });
  }

  refresh() {
    this.getCompanies();
  }

  openAddCompanyDialog() {
    this.dialog.open(AddCompanyModalComponent);
  }

  openDialog(row: Company) {
    this.dialog.open(ModalComponent, {
      data: {
        id: row.id,
        name: row.name,
        email: row.email,
        password: row.password,
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
