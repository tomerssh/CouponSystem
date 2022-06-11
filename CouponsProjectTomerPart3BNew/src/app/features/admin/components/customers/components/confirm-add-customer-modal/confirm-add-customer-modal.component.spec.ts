import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmAddCustomerModalComponent } from './confirm-add-customer-modal.component';

describe('ConfirmAddCustomerModalComponent', () => {
  let component: ConfirmAddCustomerModalComponent;
  let fixture: ComponentFixture<ConfirmAddCustomerModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmAddCustomerModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmAddCustomerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
