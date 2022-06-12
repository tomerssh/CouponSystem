import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (this.isCookieValid()) {
      this.auth();
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }

  private auth() {
    if (Boolean(JSON.parse(sessionStorage.getItem('isAuth') || ''))) {
      this.authService.isAuth$.next(true);
    } else {
      this.authService.isAuth$.next(false);
    }
  }

  private isCookieValid() {
    let cookie = sessionStorage.getItem('cookie');
    return cookie != '' || cookie != null || cookie != undefined;
  }
}
