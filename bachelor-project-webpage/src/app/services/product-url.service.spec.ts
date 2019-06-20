import { TestBed } from '@angular/core/testing';

import { ProductUrlService } from './product-url.service';

describe('ProductUrlService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductUrlService = TestBed.get(ProductUrlService);
    expect(service).toBeTruthy();
  });
});
