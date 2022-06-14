import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Coupon } from 'src/app/shared/models/coupon.model';
import { CustomerCouponService } from 'src/app/shared/services/customer/customer-coupon.service';
import { CustomerService } from 'src/app/shared/services/customer/customer.service';
import { ModalComponent } from './components/modal/modal.component';

@Component({
  selector: 'app-my-coupons',
  templateUrl: './my-coupons.component.html',
  styleUrls: ['./my-coupons.component.scss'],
})
export class MyCouponsComponent implements OnInit {
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
  customerCouponPurchased: any = {};

  constructor(
    private dialog: MatDialog,
    private customerService: CustomerService,
    private customerCouponService: CustomerCouponService
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
    this.customerService.getCustomerCoupons().subscribe({
      next: (coupons) => {
        this.dataSource.data = coupons;
        for (let c of coupons) {
          this.customerCouponService.updateCouponPurchaseAmount(c.id!);
        }
        this.customerCouponPurchased =
          this.customerCouponService.customerCouponPurchaseMap;
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
