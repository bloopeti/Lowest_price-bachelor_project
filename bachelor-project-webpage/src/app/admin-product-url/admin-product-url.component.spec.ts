import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AdminProductUrlComponent} from './admin-product-url.component';

describe('AdminProductUrlComponent', () => {
  let component: AdminProductUrlComponent;
  let fixture: ComponentFixture<AdminProductUrlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [AdminProductUrlComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProductUrlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
