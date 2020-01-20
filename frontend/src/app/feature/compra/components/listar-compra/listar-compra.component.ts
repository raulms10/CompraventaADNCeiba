import { Component, OnInit, OnDestroy } from '@angular/core';
import { MatSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';

import { ProductoService } from 'src/app/feature/producto/shared/service/producto.service';
import { Producto } from 'src/app/feature/producto/shared/model/producto';
import { Constantes } from 'src/app/shared/utilidades/constantes';
import { Validador } from 'src/app/shared/utilidades/validador';

@Component({
  selector: 'app-listar-compra',
  templateUrl: './listar-compra.component.html',
  styleUrls: ['./listar-compra.component.css']
})
export class ListarCompraComponent implements OnInit, OnDestroy {

  private subscriptionServices: Subscription[] = [];
  cedula: string;
  nombre: string;

  cargando: boolean;
  productos: Producto[];

  constructor(protected productoService: ProductoService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    const redireccionar = this.verificarLocalStorage();
    if (redireccionar === true) {
      location.replace('/home');
    }
    this.cargando = true;
    this.productos = [];
    this.subscriptionServices.push(this.productoService.consultar().subscribe(
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

  private abrirSnackBar(message: string) {
    this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }

}
