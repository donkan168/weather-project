import { TestBed } from '@angular/core/testing';

import { WeaterServiceService } from './weater-service.service';

describe('WeaterServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WeaterServiceService = TestBed.get(WeaterServiceService);
    expect(service).toBeTruthy();
  });
});
