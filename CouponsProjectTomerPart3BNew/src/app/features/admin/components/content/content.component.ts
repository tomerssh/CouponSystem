import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ToggleMenuService } from 'src/app/shared/services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {
  menuState$: Observable<boolean>;
  url = this.router.url;
  links: { name: string; url: string }[] = [];

  constructor(
    private toggleMenuService: ToggleMenuService,
    private router: Router
  ) {
    this.menuState$ = this.toggleMenuService.menu$;
    this.links = [
      {
        name: 'Dashboard',
        url: this.url + '/dashboard',
      },
      {
        name: 'Add Company',
        url: this.url + '/add/company',
      },
    ];
  }

  ngOnInit(): void {}

  toggle() {
    this.toggleMenuService.toggle();
  }
}
