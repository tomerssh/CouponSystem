import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmAddCouponModalComponent } from './confirm-add-coupon-modal.component';

describe('ConfirmAddCouponModalComponent', () => {
  let component: ConfirmAddCouponModalComponent;
  let fixture: ComponentFixture<ConfirmAddCouponModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmAddCouponModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmAddCouponModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
