import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseConfirmModalComponent } from './purchase-confirm-modal.component';

describe('PurchaseConfirmModalComponent', () => {
  let component: PurchaseConfirmModalComponent;
  let fixture: ComponentFixture<PurchaseConfirmModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseConfirmModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseConfirmModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
