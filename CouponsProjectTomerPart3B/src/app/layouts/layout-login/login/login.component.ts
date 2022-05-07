import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService, private router: Router) {}

  ngOnInit(): void {}

  public login(email: string, password: string, clientType: string) {
    let obs = this.loginService.login(email, password, clientType).subscribe({
      next: (token) => {
        sessionStorage.setItem('token', token.toString());
        this.router.navigate([this.navigate(clientType)]);
      },
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        console.log(errAsObject.error);
        console.log(errAsObject.message);
      },
      complete: () => {
        obs.unsubscribe();
      },
    });
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
