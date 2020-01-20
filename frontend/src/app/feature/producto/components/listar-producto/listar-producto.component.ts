import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';
import { HttpParams } from '@angular/common/http';

import { ProductoService } from '../../shared/service/producto.service';
import { Producto } from '../../shared/model/producto';
import { Constantes } from 'src/app/shared/utilidades/constantes';
import { Validador } from 'src/app/shared/utilidades/validador';

@Component({
  selector: 'app-listar-producto',
  templateUrl: './listar-producto.component.html',
  styleUrls: ['./listar-producto.component.css']
})
export class ListarProductoComponent implements OnInit, OnDestroy {

  private subscriptionServices: Subscription[] = [];
  cedula: string;
  nombre: string;

  cargando: boolean;
  productos: Producto[];

  constructor(protected productoService: ProductoService,
              private snackBar: MatSnackBar, private router: Router) { }

  ngOnInit() {
    const redireccionar = this.verificarLocalStorage();
    if (redireccionar === true) {
      location.replace('/home');
    }
    this.cargando = true;
    this.productos = [];
    let parametros = new HttpParams();
    parametros = parametros.append('cedula', this.cedula);
    this.subscriptionServices.push(this.productoService.consultarConParametros(parametros).subscribe(
      (result) => {
        this.productos = result;
        this.cargando = false;
      },
      (error) => {
        console.log(error);
        this.abrirSnackBar(Constantes.HA_OCURRIDO_UN_ERROR);
        this.cargando = false;
      }
    ));
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

  irACrearProducto() {
    this.router.navigate(['producto/crear']);
  }

  private abrirSnackBar(message: string) {
    this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }

}
