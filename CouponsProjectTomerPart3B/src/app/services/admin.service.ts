import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../models/company.model';
import { Customer } from '../models/customer.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private mainUrl: string = 'http://localhost:8080/rest/admin/';

  constructor(private httpClient: HttpClient) {}

  public addCompany(company: Company) {
    let url = this.mainUrl + 'add/company';
    let companyAsJson = JSON.stringify(company);
    let body = companyAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, options);
  }

  public updateCompany(company: Company) {
    let url = this.mainUrl + 'update/company';
    let companyAsJson = JSON.stringify(company);
    let body = companyAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.put(url, body, options);
  }

  public removeCompany(id: number) {
    let url = this.mainUrl + 'remove/company/' + id;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.delete(url, options);
  }

  public getCompanies() {
    let url = this.mainUrl + 'get/company';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Company[]>(url, <Object>options);
  }

  public getCompany(id: number) {
    let url = this.mainUrl + 'get/company/' + id;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Company>(url, <Object>options);
  }

  public addCustomer(customer: Customer) {
    let url = this.mainUrl + 'add/customer';
    let customerAsJson = JSON.stringify(customer);
    let body = customerAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, options);
  }

  public updateCustomer(customer: Customer) {
    let url = this.mainUrl + 'update/customer';
    let customerAsJson = JSON.stringify(customer);
    let body = customerAsJson;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.put(url, body, options);
  }

  public removeCustomer(id: number) {
    let url = this.mainUrl + 'remove/customer/' + id;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.delete(url, options);
  }

  public getCustomers() {
    let url = this.mainUrl + 'get/customer';
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Customer[]>(url, <Object>options);
  }

  public getCustomer(id: number) {
    let url = this.mainUrl + 'get/customer/' + id;
    let token: any = sessionStorage.getItem('token');
    let httpHeaders = new HttpHeaders().set('token', token);
    let options: any = { headers: httpHeaders };
    return this.httpClient.get<Customer>(url, <Object>options);
  }
}
