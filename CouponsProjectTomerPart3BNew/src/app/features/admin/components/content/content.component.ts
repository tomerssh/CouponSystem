import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ToggleMenuService } from 'src/app/shared/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {
  menuState$: Observable<boolean>;
  apiUrl: string;
  links: { name: string; apiUrl: string }[];

  constructor(private toggleMenuService: ToggleMenuService) {
    this.menuState$ = this.toggleMenuService.menu$;
    this.apiUrl = '/admin';
    this.links = [
      {
        name: 'Dashboard',
        apiUrl: this.apiUrl,
      },
      {
        name: 'Details',
        apiUrl: this.apiUrl + '/details',
      },
    ];
  }

  ngOnInit(): void {}

  toggle() {
    this.toggleMenuService.toggle();
  }
}
