import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditConfirmModalComponent } from './edit-confirm-modal.component';

describe('EditConfirmModalComponent', () => {
  let component: EditConfirmModalComponent;
  let fixture: ComponentFixture<EditConfirmModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditConfirmModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditConfirmModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
