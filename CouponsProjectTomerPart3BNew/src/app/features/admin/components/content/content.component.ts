import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ToggleMenuService } from 'src/app/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {
  menuState$: Observable<boolean>;

  constructor(private toggleMenuService: ToggleMenuService) {
    this.menuState$ = this.toggleMenuService.menu$;
  }

  ngOnInit(): void {}

  toggle() {
    this.toggleMenuService.toggle();
  }
}
