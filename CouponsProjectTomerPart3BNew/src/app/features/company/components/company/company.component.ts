import { Component, OnInit } from '@angular/core';
import { ToggleMenuService } from 'src/app/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss'],
  providers: [ToggleMenuService],
})
export class CompanyComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
