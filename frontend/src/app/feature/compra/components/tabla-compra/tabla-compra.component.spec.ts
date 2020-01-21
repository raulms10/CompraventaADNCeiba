import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaCompraComponent } from './tabla-compra.component';

describe('TablaCompraComponent', () => {
  let component: TablaCompraComponent;
  let fixture: ComponentFixture<TablaCompraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaCompraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
