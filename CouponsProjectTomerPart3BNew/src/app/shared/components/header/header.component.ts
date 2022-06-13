import { Component, Input, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../../services/auth/auth.service';
import { ToggleMenuService } from '../../services/toggleMenu/toggle-menu.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  isAuth$: BehaviorSubject<boolean>;

  @Input()
  content: string = '';
  @Input()
  btn: string = '';
  @Input()
  btnUrl: string = '';

  constructor(
    private toggleMenuService: ToggleMenuService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.isAuth$ = this.authService.isAuth$;
  }

  toggle() {
    this.toggleMenuService.toggle();
  }

  logout() {
    if (this.btn === 'logout') {
      this.authService.logout();
    }
  }
}
