import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarProductoComponent } from './listar-producto.component';
import { CommonModule } from '@angular/common';
import { RouterTestingModule } from '@angular/router/testing';
import { SharedModule } from 'src/app/shared/shared.module';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';

describe('ListarProductoComponent', () => {
  let component: ListarProductoComponent;
  let fixture: ComponentFixture<ListarProductoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarProductoComponent ],
      imports: [
        CommonModule,
        SharedModule,
        RouterTestingModule,
        BrowserAnimationsModule,
        BrowserModule
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarProductoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
