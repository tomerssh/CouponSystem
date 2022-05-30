import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Coupon } from '../models/coupon.model';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private mainUrl: string = 'http://localhost:8080/rest/customer/';

  constructor(private httpClient: HttpClient) {}

  public purchaseCoupon(id: number) {
    let url = this.mainUrl + 'purchase/coupon';
    let idAsJson = JSON.stringify(id);
    let body = idAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, <Object>options);
  }

  public getCouponPurchaseAmount(couponId: number) {
    let url = this.mainUrl + 'get/coupon/amount/' + couponId;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.get(url, <Object>options);
  }

  public getCustomerDetails() {
    let url = this.mainUrl + 'get/customer';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Customer>(url, <Object>options);
  }

  public getCustomerCoupons() {
    let url = this.mainUrl + 'get/coupon';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }

  public getAllCoupons() {
    let url = this.mainUrl + 'get/coupon/all';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }

  public getCustomerCouponsByCategory(category: string) {
    let url = this.mainUrl + 'get/coupon/category/' + category;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }

  public getCustomerCouponsByMaxPrice(price: number) {
    let url = this.mainUrl + 'get/coupon/price/' + price;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }
}
