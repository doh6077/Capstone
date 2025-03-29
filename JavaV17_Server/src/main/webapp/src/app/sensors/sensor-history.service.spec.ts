import { TestBed } from '@angular/core/testing';

import { SensorHistoryService } from './sensor-history.service';

describe('SensorHistoryService', () => {
  let service: SensorHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SensorHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
