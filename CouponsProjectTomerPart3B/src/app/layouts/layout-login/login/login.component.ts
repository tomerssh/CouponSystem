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
    console.log(email + ', ' + password + ', ' + clientType);
    let obs = this.loginService.login(email, password, clientType).subscribe({
      next: (token) => {
        console.log(token);
        sessionStorage.setItem('token', token.toString());
        this.router.navigate(['/admin', '/company', '/customer']);
      },
      error: (e) => {
        console.log(e.error);
        let errAsObject = JSON.parse(e.error);
        console.log(errAsObject);
        console.log(errAsObject.error);
        console.log(errAsObject.message);
        alert(errAsObject.error + ': ' + errAsObject.message);
      },
      complete: () => {
        obs.unsubscribe();
      },
    });
  }
}
