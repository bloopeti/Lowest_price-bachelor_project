import {TestBed} from '@angular/core/testing';

import {SensitiveDataService} from './sensitive-data.service';

describe('SensitiveDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SensitiveDataService = TestBed.get(SensitiveDataService);
    expect(service).toBeTruthy();
  });
});
