import { TestBed } from '@angular/core/testing';

import { ProductPriceForUrlService } from './product-price-for-url.service';

describe('ProductPriceForUrlService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ProductPriceForUrlService = TestBed.get(ProductPriceForUrlService);
    expect(service).toBeTruthy();
  });
});
