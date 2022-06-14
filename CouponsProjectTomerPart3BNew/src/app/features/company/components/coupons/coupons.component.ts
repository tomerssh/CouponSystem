import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { CompanyService } from 'src/app/shared/services/company/company.service';
import { AddCouponModalComponent } from './components/add-coupon-modal/add-coupon-modal.component';
import { ModalComponent } from './components/modal/modal.component';

@Component({
  selector: 'app-coupons',
  templateUrl: './coupons.component.html',
  styleUrls: ['./coupons.component.scss'],
})
export class CouponsComponent implements OnInit, AfterViewInit {
  displayedColumns: string[] = [
    'id',
    'category',
    'title',
    'description',
    'startDate',
    'endDate',
    'amount',
    'price',
    'image',
  ];
  dataSource: MatTableDataSource<Coupon>;

  constructor(
    private dialog: MatDialog,
    private companyService: CompanyService
  ) {}

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<Coupon>();
    this.getCoupons();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  async getCoupons() {
    this.companyService.getCompanyCoupons().subscribe({
      next: (coupons) => {
        this.dataSource.data = coupons;
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        alert(errAsObject.message);
      },
    });
  }

  refresh() {
    this.getCoupons();
  }

  openAddCouponDialog() {
    this.dialog.open(AddCouponModalComponent);
  }

  openDialog(row: Coupon) {
    this.dialog.open(ModalComponent, {
      data: {
        id: row.id,
        category: row.category,
        title: row.title,
        description: row.description,
        startDate: row.startDate,
        endDate: row.endDate,
        amount: row.amount,
        price: row.price,
        image: row.image,
      },
    });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
}
