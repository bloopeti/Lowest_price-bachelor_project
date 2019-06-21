import {TestBed} from '@angular/core/testing';

import {FavouriteProductService} from './favourite-product.service';

describe('FavouriteProductService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FavouriteProductService = TestBed.get(FavouriteProductService);
    expect(service).toBeTruthy();
  });
});
