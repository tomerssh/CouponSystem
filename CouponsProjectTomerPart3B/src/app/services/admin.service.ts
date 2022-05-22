import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company } from '../models/company.model';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private mainUrl: string = 'http://localhost:8080/admin/';

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
}
