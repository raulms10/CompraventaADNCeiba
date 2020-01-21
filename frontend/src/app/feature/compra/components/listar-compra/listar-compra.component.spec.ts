import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarCompraComponent } from './listar-compra.component';
import { MatCardModule, MatButtonModule } from '@angular/material';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { SharedModule } from 'src/app/shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('ListarCompraComponent', () => {
  let component: ListarCompraComponent;
  let fixture: ComponentFixture<ListarCompraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarCompraComponent ],
      imports: [
        SharedModule,
        BrowserAnimationsModule,
        BrowserModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
