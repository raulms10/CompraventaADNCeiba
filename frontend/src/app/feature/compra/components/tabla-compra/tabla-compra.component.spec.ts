import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaCompraComponent } from './tabla-compra.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('TablaCompraComponent', () => {
  let component: TablaCompraComponent;
  let fixture: ComponentFixture<TablaCompraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaCompraComponent ],
      imports: [
        SharedModule,
        BrowserAnimationsModule,
        BrowserModule
      ]
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
