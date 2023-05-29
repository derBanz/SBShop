import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseReadComponent } from './purchase-read.component';

describe('PurchaseReadComponent', () => {
  let component: PurchaseReadComponent;
  let fixture: ComponentFixture<PurchaseReadComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PurchaseReadComponent]
    });
    fixture = TestBed.createComponent(PurchaseReadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
