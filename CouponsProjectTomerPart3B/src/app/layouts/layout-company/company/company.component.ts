import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css'],
})
export class CompanyComponent implements OnInit {
  @ViewChild('sidenav') sidenav?: ElementRef<HTMLInputElement>;
  private isSidenavOpen: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  navToggle() {
    if (this.isSidenavOpen) {
      this.closeNav();
    } else {
      this.openNav();
    }
    this.isSidenavOpen = !this.isSidenavOpen;
  }

  private openNav() {
    if (this.sidenav != undefined) {
      this.sidenav.nativeElement.style.display = 'block';
    }
  }

  private closeNav() {
    if (this.sidenav != undefined) {
      this.sidenav.nativeElement.style.display = 'none';
    }
  }
}
