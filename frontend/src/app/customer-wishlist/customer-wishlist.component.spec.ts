import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerWishlistComponent } from './customer-wishlist.component';

describe('CustomerWishlistComponent', () => {
  let component: CustomerWishlistComponent;
  let fixture: ComponentFixture<CustomerWishlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomerWishlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerWishlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
