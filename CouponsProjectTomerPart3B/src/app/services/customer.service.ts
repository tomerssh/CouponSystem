import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private mainUrl: string = 'http://localhost:8080/customer/';

  constructor(private httpClient: HttpClient) {}
}
