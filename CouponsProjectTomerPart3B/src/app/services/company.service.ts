import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../models/company.model';
import { Coupon } from '../models/coupon.model';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  private mainUrl: string = 'http://localhost:8080/company/';

  constructor(private httpClient: HttpClient) {}

  public addCoupon(coupon: Coupon) {
    let url = this.mainUrl + 'add/coupon';
    let couponAsJson = JSON.stringify(coupon);
    let body = couponAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, options);
  }

  public getCompanyDetails() {
    let url = this.mainUrl + 'get/company';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Company>(url, <Object>options);
  }

  public getCompanyCoupons() {
    let url = this.mainUrl + 'get/coupon';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }
}
