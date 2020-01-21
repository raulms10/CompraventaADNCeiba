import { TestBed } from '@angular/core/testing';

import { CompraService } from './compra.service';
import { HttpClientModule } from '@angular/common/http';

describe('CompraService', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [HttpClientModule]
  }));

  it('should be created', () => {
    const service: CompraService = TestBed.get(CompraService);
    expect(service).toBeTruthy();
  });
});
