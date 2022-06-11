import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ConfirmAddCompanyModalComponent } from '../../../companies/components/confirm-add-company-modal/confirm-add-company-modal.component';

@Component({
  selector: 'app-add-customer-modal',
  templateUrl: './add-customer-modal.component.html',
  styleUrls: ['./add-customer-modal.component.scss'],
})
export class AddCustomerModalComponent implements OnInit {
  elementForm: FormGroup;

  constructor(private dialog: MatDialog, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.elementForm = this.formBuilder.group({
      name: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  save() {
    console.log(this.elementForm.value);
  }

  openConfirmAddDialog(form: any) {
    this.dialog.open(ConfirmAddCompanyModalComponent, {
      data: {
        form: form.value,
      },
    });
  }
}
