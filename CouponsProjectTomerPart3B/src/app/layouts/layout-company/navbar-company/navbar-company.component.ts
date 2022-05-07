import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar-company',
  templateUrl: './navbar-company.component.html',
  styleUrls: ['./navbar-company.component.css'],
})
export class NavbarCompanyComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  public logout() {
    sessionStorage.removeItem('token');
  }
}
