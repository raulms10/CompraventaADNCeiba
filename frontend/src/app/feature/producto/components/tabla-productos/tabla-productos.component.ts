import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';

import { Producto } from '../../shared/model/producto';
import { ProductoService } from 'src/app/feature/producto/shared/service/producto.service';
import { Constantes } from 'src/app/shared/utilidades/constantes';
import { Validador } from 'src/app/shared/utilidades/validador';
import { HttpParams } from '@angular/common/http';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-tabla-productos',
  templateUrl: './tabla-productos.component.html',
  styleUrls: ['./tabla-productos.component.css']
})
export class TablaProductosComponent implements OnInit {

  private subscriptionServices: Subscription[] = [];
  columnasMostrar: string[];
  dataSource: MatTableDataSource<Producto>;
  snackBarRef: MatSnackBarRef<SimpleSnackBar>;
  cargando = false;
  @Input() productos: Producto[];
  productoEliminar: Producto;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(protected productoService: ProductoService, private snackBar: MatSnackBar,
              public datepipe: DatePipe) { }

  ngOnInit() {
    this.inizializarDatasource();
  }

  private inizializarDatasource() {
    this.dataSource = new MatTableDataSource(this.productos);
    this.dataSource.paginator ? this.dataSource.paginator.firstPage()
        : this.dataSource.paginator = this.paginator;
    this.actualizarColumnas();
  }

  private actualizarColumnas() {
    this.columnasMostrar = ['codigo', 'nombre', 'valor', 'descuento', 'fecha_creacion', 'acciones'];
  }

  eliminar(producto: Producto) {
    const esLunesAViernes = Validador.esDiaSemanaPermitido(Constantes.diasLunesAViernes);
    if (esLunesAViernes === true) {
      this.cargando = true;
      // this.productoEliminar = producto;
      this.eliminarProducto(producto);
    } else {
      this.abrirSnackBar(Constantes.NO_PERMITIDO_SABADO_DOMINGO);
    }
  }

  private eliminarProducto(producto: Producto) {
    let parametros = new HttpParams();
    parametros = parametros.append('codigo', producto.codigo);
    parametros = parametros.append('fecha', this.datepipe.transform(new Date(), Constantes.FORMATO_FECHA));
    this.subscriptionServices.push(this.productoService.eliminar(parametros).subscribe(
      (result) => {
        this.cargando = false;
        const index = this.productos.indexOf(producto);
        if (index !== -1) {
          this.productos.splice(index, 1);
        }
        this.resetDataSource();
        this.abrirSnackBar(Constantes.PRODUCTO_ELIMINADO);
      },
      (error) => {
        console.log(error);
        this.abrirSnackBar(error.error.mensaje);
        this.cargando = false;
      }
    ));
  }

  private resetDataSource() {
    setTimeout(() => {
      this.inizializarDatasource();
    }, 100);
    this.inizializarDatasource();
  }

  private abrirSnackBar(message: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }

}
