import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { AuthService } from 'src/app/shared/services/auth/auth.service';
import { CompanyService } from 'src/app/shared/services/company/company.service';
import { CustomerService } from 'src/app/shared/services/customer/customer.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {
  isAuth$: BehaviorSubject<boolean>;
  elementForm: FormGroup;
  error: string | null;

  clients: clientType[] = [
    { value: 'ADMINISTRATOR', viewValue: 'Admin' },
    { value: 'COMPANY', viewValue: 'Company' },
    { value: 'CUSTOMER', viewValue: 'Customer' },
  ];

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private router: Router,
    private companyService: CompanyService,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.isAuth$ = this.authService.isAuth$;
    this.elementForm = this.formBuilder.group({
      clientType: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  login(email: string, password: string, clientType: string) {
    this.authService.login(email, password, clientType).subscribe({
      next: (cookie) => {
        sessionStorage.setItem('cookie', cookie.toString());
        this.router.navigate([this.navigate(clientType)]);
        sessionStorage.setItem('isAuth', 'true');
        this.isAuth$.next(true);
        this.initClient(clientType);
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.error = errAsObject.message;
      },
      complete: () => {
        this.error = null;
      },
    });
  }

  private navigate(clientType: string) {
    let route = '/admin';
    switch (clientType) {
      case 'COMPANY':
        route = '/company';
        break;
      case 'CUSTOMER':
        route = '/customer';
        break;
    }
    return route;
  }

  private initClient(clientType: string) {
    if (clientType === 'ADMINISTRATOR') {
      sessionStorage.setItem('client', 'admin');
    } else if (clientType === 'COMPANY') {
      this.companyService.getCompanyDetails().subscribe({
        next: (company) => {
          sessionStorage.setItem('client', JSON.stringify(company));
        },
      });
    } else if (clientType === 'CUSTOMER') {
      this.customerService.getCustomerDetails().subscribe({
        next: (customer) => {
          sessionStorage.setItem('client', JSON.stringify(customer));
        },
      });
    }
  }
}
interface clientType {
  value: string;
  viewValue: string;
}
