import { Component, OnInit } from '@angular/core';
import { ToggleMenuService } from 'src/app/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [ToggleMenuService],
})
export class AdminComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}
}
