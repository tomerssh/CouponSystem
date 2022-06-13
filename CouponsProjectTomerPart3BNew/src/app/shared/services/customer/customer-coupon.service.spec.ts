import { TestBed } from '@angular/core/testing';

import { CustomerCouponService } from './customer-coupon.service';

describe('CustomerCouponService', () => {
  let service: CustomerCouponService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CustomerCouponService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
