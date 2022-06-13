import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Customer } from 'src/app/shared/models/customer.model';
import { AdminService } from 'src/app/shared/services/admin/admin.service';
import { AddCustomerModalComponent } from './components/add-customer-modal/add-customer-modal.component';
import { ModalComponent } from './components/modal/modal.component';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.scss'],
})
export class CustomersComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'id',
    'firstName',
    'lastName',
    'email',
    'password',
  ];
  dataSource: MatTableDataSource<Customer>;

  constructor(private dialog: MatDialog, private adminService: AdminService) {}

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<Customer>();
    this.getCustomers();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  async getCustomers() {
    this.adminService.getCustomers().subscribe({
      next: (customers) => {
        this.dataSource.data = customers;
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
    });
  }

  refresh() {
    this.getCustomers();
  }

  openAddCustomerDialog() {
    this.dialog.open(AddCustomerModalComponent);
  }

  openDialog(row: Customer) {
    this.dialog.open(ModalComponent, {
      data: {
        id: row.id,
        firstName: row.firstName,
        lastName: row.lastName,
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
