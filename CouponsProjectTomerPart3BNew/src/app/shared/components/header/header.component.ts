import { Component, Input, OnInit } from '@angular/core';
import { ToggleMenuService } from 'src/app/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  @Input()
  content: string = '';
  @Input()
  btn: string = '';
  @Input()
  btnUrl: string = '';

  constructor(private toggleMenuService: ToggleMenuService) {}

  ngOnInit(): void {}

  toggle() {
    this.toggleMenuService.toggle();
  }
}
