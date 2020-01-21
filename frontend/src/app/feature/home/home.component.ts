import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Validador } from 'src/app/shared/utilidades/validador';
import { Router } from '@angular/router';
import { MatSnackBarRef, SimpleSnackBar, MatSnackBar } from '@angular/material';
import { Constantes } from 'src/app/shared/utilidades/constantes';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  form = new FormGroup({
    formCedula: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(12)]),
    formNombreVendedor: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(60)]),
  });

  cedula: string;
  nombre: string;

  constructor(private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.verificarLocalStorage();
  }

  enviar() {
    if (this.form.valid) {
      this.cedula = this.form.get('formCedula').value;
      this.nombre = this.form.get('formNombreVendedor').value;
      if (this.guardarEnLocalStorage()) {
        location.replace('/producto');
      } else {
        this.abrirSnackBar('No se soporta LocalStorage.');
      }
    } else {
      this.abrirSnackBar(Constantes.CAMPOS_INVALIDOS);
    }
  }

  private verificarLocalStorage() {
    this.cedula = decodeURIComponent(localStorage.getItem('cedula'));
    this.nombre = decodeURIComponent(localStorage.getItem('nombre'));
    if (Validador.esStringVacio(this.cedula) || Validador.esStringVacio(this.nombre)) {
      this.limpiar();
      this.abrirSnackBar(Constantes.DEBE_INGRESAR_DATOS);
      return;
    }
    this.form.get('formCedula').setValue(this.cedula);
    this.form.get('formNombreVendedor').setValue(this.nombre);
  }

  limpiar() {
    this.cedula = undefined;
    this.nombre = undefined;
    localStorage.clear();
    this.form.get('formCedula').setValue('');
    this.form.get('formNombreVendedor').setValue('');
  }

  private guardarEnLocalStorage(): boolean {
    if (typeof(Storage) !== 'undefined') {
      localStorage.clear();
      localStorage.setItem('cedula', encodeURIComponent(this.cedula));
      localStorage.setItem('nombre', encodeURIComponent(this.nombre));
      return true;
    } else {
        // LocalStorage no soportado en este navegador
        console.log('No se soporta LocalStorage');
        return false;
    }
  }

  private abrirSnackBar(message: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }
}
