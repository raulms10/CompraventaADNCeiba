import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';
import { Router } from '@angular/router';

import { Validador } from 'src/app/shared/utilidades/validador';
import { ProductoService } from '../../shared/service/producto.service';
import { Constantes } from 'src/app/shared/utilidades/constantes';
import { Producto } from '../../shared/model/producto';

@Component({
  selector: 'app-crear-producto',
  templateUrl: './crear-producto.component.html',
  styleUrls: ['./crear-producto.component.css']
})
export class CrearProductoComponent implements OnInit, OnDestroy {

  private subscriptionServices: Subscription[] = [];
  cedula: string;
  nombre: string;

  form = new FormGroup({
    formCodigo: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(10)]),
    formNombre: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(30)]),
    formValor: new FormControl('', [Validators.required, Validador.validadorNumerosEnteros(true)]),
    formDescuento: new FormControl('', [Validators.required, Validador.validadorNumerosEnteros(true),
        Validador.validadorRango(true, 0, 75)]),
    formCedula: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(12)]),
    formNombreVendedor: new FormControl('', [Validators.required, Validador.validadorEspacios(true), Validators.maxLength(60)]),
  });

  cargando: boolean;
  snackBarRef: MatSnackBarRef<SimpleSnackBar>;

  constructor(protected productoService: ProductoService,
              private snackBar: MatSnackBar, public datepipe: DatePipe,
              private router: Router) { }

  ngOnInit() {
    const redireccionar = this.verificarLocalStorage();
    if (redireccionar === true) {
      location.replace('/home');
    }
    this.cargando = false;
    this.form.get('formCedula').setValue(this.cedula);
    this.form.get('formNombreVendedor').setValue(this.nombre);
  }

  ngOnDestroy() {
    this.subscriptionServices.forEach(subscription => subscription.unsubscribe());
  }

  private verificarLocalStorage(): boolean {
    this.cedula = decodeURIComponent(localStorage.getItem('cedula'));
    this.nombre = decodeURIComponent(localStorage.getItem('nombre'));
    if (Validador.esStringVacio(this.cedula) || Validador.esStringVacio(this.nombre)) {
      return true;
    }
    return false;
  }

  regresar() {
    this.router.navigate(['producto']);
  }

  enviar() {
    if (this.form.valid) {
      const esLunesAViernes = Validador.esDiaSemanaPermitido(Constantes.diasLunesAViernes);
      if (esLunesAViernes === true) {
        this.cargando = true;
        const producto: Producto = this.obtenerProducto();
        this.guardarProducto(producto);
      } else {
        this.abrirSnackBar(Constantes.SOLO_LUNES_A_VIERNES);
      }
    } else {
      this.abrirSnackBar(Constantes.CAMPOS_INVALIDOS);
    }
  }

  private guardarProducto(producto: Producto) {
    this.subscriptionServices.push(this.productoService.guardar(producto).subscribe(
      (result) => {
        this.snackBarRef = this.abrirSnackBar(Constantes.PRODUCTO_GUARDADO);
        this.snackBarRef.afterDismissed().subscribe(() => {
          this.router.navigate(['producto']);
        });
      },
      (error) => {
        console.log(error);
        this.abrirSnackBar(error.error.mensaje);
        this.cargando = false;
      }
    ));
  }

  obtenerValorConDescuento(valor: number, descuento: number) {
    return Validador.obtenerDescuento(valor, descuento);
  }

  private obtenerProducto(): Producto {
    const producto: Producto = new Producto();
    producto.codigo = this.form.get('formCodigo').value;
    producto.nombre = this.form.get('formNombre').value;
    producto.valor = this.form.get('formValor').value;
    producto.descuento = this.form.get('formDescuento').value;
    producto.fecha = this.datepipe.transform(new Date(), Constantes.FORMATO_FECHA);
    producto.cedulaVendedor = this.form.get('formCedula').value;
    producto.nombreVendedor = this.form.get('formNombreVendedor').value;
    return producto;
  }

  private abrirSnackBar(message: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }

}
