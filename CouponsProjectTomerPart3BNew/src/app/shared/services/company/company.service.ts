import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Company } from '../../models/company.model';
import { Coupon } from '../../models/coupon.model';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  private mainUrl: string = `${environment.serverUrl}/rest/company/`;

  constructor(private httpClient: HttpClient) {}

  public addCoupon(coupon: Coupon) {
    let url = this.mainUrl + 'add/coupon';
    let couponAsJson = JSON.stringify(coupon);
    let body = couponAsJson;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, options);
  }

  public getCouponById(id: string) {
    let url = this.mainUrl + 'get/coupon/' + id;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon>(url, <Object>options);
  }

  public updateCoupon(coupon: Coupon) {
    let url = this.mainUrl + 'update/coupon';
    let couponAsJson = JSON.stringify(coupon);
    let body = couponAsJson;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.put(url, body, options);
  }

  public removeCoupon(id: number) {
    let url = this.mainUrl + 'remove/coupon/' + id;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.delete(url, options);
  }

  public getCompanyDetails() {
    let url = this.mainUrl + 'get/company';
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Company>(url, <Object>options);
  }

  public getCompanyCoupons() {
    let url = this.mainUrl + 'get/coupon';
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }

  public getCompanyCouponsByCategory(category: string) {
    let url = this.mainUrl + 'get/coupon/category/' + category;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }

  public getCompanyCouponsByMaxPrice(price: number) {
    let url = this.mainUrl + 'get/coupon/price/' + price;
    let token: any = JSON.parse(sessionStorage.getItem('cookie') || '').value;
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Coupon[]>(url, <Object>options);
  }
}
