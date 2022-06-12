import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticateSubject = new BehaviorSubject<boolean>(false);
  serverUrl = environment.serverUrl;

  get isAuth$(): Observable<boolean> {
    return this.isAuthenticateSubject.asObservable();
  }

  constructor(private httpClient: HttpClient, private router: Router) {}

  login(email: string, password: string, clientType: string) {
    let url = `${this.serverUrl}/login`;
    let body =
      'clientType=' + clientType + '&email=' + email + '&password=' + password;
    let httpHeaders = new HttpHeaders().set(
      'Content-Type',
      'application/x-www-form-urlencoded'
    );
    let options: any = { headers: httpHeaders, responseType: 'text' };
    let obs = this.httpClient.post(url, body, options);
    obs.subscribe({
      next: (cookie) => {
        sessionStorage.setItem('cookie', cookie.toString());
        this.isAuthenticateSubject.next(true);
        this.router.navigate([this.navigate(clientType)]);
      },
    });
    return obs;
  }

  logout() {
    if (sessionStorage.getItem('cookie')) {
      sessionStorage.setItem('cookie', '');
    }
    this.isAuthenticateSubject.next(false);
  }

  private navigate(clientType: string) {
    let route = '/admin';
    switch (clientType) {
      case 'COMPANY':
        route = '/company';
        break;
      case 'CUSTOMER':
        route = '/customer';
        break;
    }
    return route;
  }
}
