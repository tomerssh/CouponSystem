import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private isAuthenticateSubject = new BehaviorSubject<boolean>(false);

  get isAuth$() {
    return this.isAuthenticateSubject;
  }

  constructor(private httpClient: HttpClient) {}

  login(email: string, password: string, clientType: string) {
    let url = `${environment.serverUrl}/login`;
    let body =
      'clientType=' + clientType + '&email=' + email + '&password=' + password;
    let httpHeaders = new HttpHeaders().set(
      'Content-Type',
      'application/x-www-form-urlencoded'
    );
    let options: any = { headers: httpHeaders, responseType: 'text' };
    return this.httpClient.post(url, body, options);
  }

  logout() {
    if (sessionStorage.getItem('cookie')) {
      sessionStorage.removeItem('cookie');
      sessionStorage.setItem('isAuth', 'false');
      this.isAuthenticateSubject.next(false);
    }
  }
}
