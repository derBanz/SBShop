import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseUpdateComponent } from './purchase-update.component';

describe('PurchaseUpdateComponent', () => {
  let component: PurchaseUpdateComponent;
  let fixture: ComponentFixture<PurchaseUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PurchaseUpdateComponent]
    });
    fixture = TestBed.createComponent(PurchaseUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
