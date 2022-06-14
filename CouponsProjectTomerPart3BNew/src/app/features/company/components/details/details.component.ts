import { Component, OnInit } from '@angular/core';
import { Company } from 'src/app/shared/models/company.model';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.scss'],
})
export class DetailsComponent implements OnInit {
  company: Company;

  constructor() {}

  ngOnInit(): void {
    this.company = JSON.parse(sessionStorage.getItem('client') || '');
  }
}
