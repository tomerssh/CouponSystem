import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmAddCompanyModalComponent } from './confirm-add-company-modal.component';

describe('ConfirmAddCompanyModalComponent', () => {
  let component: ConfirmAddCompanyModalComponent;
  let fixture: ComponentFixture<ConfirmAddCompanyModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfirmAddCompanyModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmAddCompanyModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
