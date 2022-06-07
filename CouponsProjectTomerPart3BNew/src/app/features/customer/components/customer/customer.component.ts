import { Component, OnInit } from '@angular/core';
import { ToggleMenuService } from 'src/app/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.scss'],
  providers: [ToggleMenuService],
})
export class CustomerComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
