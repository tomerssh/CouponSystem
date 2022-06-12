import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'src/app/shared/services/auth/auth.service';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.scss'],
})
export class ContentComponent implements OnInit {
  elementForm: FormGroup;
  error?: string;

  clients: clientType[] = [
    { value: 'ADMINISTRATOR', viewValue: 'Admin' },
    { value: 'COMPANY', viewValue: 'Company' },
    { value: 'CUSTOMER', viewValue: 'Customer' },
  ];

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.elementForm = this.formBuilder.group({
      clientType: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  login(email: string, password: string, clientType: string) {
    console.log(this.elementForm.value);
    this.authService.login(email, password, clientType).subscribe({
      error: (e) => {
        let errAsObject = JSON.parse(e.error);
        this.error = errAsObject.message;
      },
      complete: () => {
        this.error = undefined;
      },
    });
  }
}
interface clientType {
  value: string;
  viewValue: string;
}
