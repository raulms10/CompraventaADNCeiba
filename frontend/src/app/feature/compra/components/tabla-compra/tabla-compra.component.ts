import { Component, OnInit, Input, ViewChild, OnDestroy } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSnackBarRef, SimpleSnackBar, MatSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';

import { Producto } from 'src/app/feature/producto/shared/model/producto';
import { Compra } from '../../shared/model/compra.model';
import { CompraService } from '../../shared/service/compra.service';
import { Constantes } from 'src/app/shared/utilidades/constantes';

@Component({
  selector: 'app-tabla-compra',
  templateUrl: './tabla-compra.component.html',
  styleUrls: ['./tabla-compra.component.css']
})
export class TablaCompraComponent implements OnInit, OnDestroy {

  private subscriptionServices: Subscription[] = [];

  columnasMostrar: string[];
  dataSource: MatTableDataSource<Producto>;
  @Input() productos: Producto[];
  cargando = false;
  snackBarRef: MatSnackBarRef<SimpleSnackBar>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(protected compraService: CompraService, private snackBar: MatSnackBar) { }

  ngOnInit() {
    this.inizializarDatasource();
  }

  ngOnDestroy() {
    this.subscriptionServices.forEach(subscription => subscription.unsubscribe());
  }

  private inizializarDatasource() {
    this.dataSource = new MatTableDataSource(this.productos);
    this.dataSource.paginator ? this.dataSource.paginator.firstPage()
        : this.dataSource.paginator = this.paginator;
    this.actualizarColumnas();
  }

  private actualizarColumnas() {
    this.columnasMostrar = ['codigo', 'nombre', 'valor', 'descuento', 'nombre_vendedor', 'fecha_creacion', 'acciones'];
  }

  private obtenerCompra(prod: Producto) {
    const producto = Object.assign({}, prod);
    producto.compra = undefined;
    const compra = new Compra();
    compra.comandoProducto = producto;
    console.log(['Producto', producto, compra]);
    return compra;
  }

  private guardarCompra(compra: Compra) {
    this.subscriptionServices.push(this.compraService.guardar(compra).subscribe(
      (result) => {
        this.snackBarRef = this.abrirSnackBar(Constantes.COMPRA_GUARDADA);
        this.snackBarRef.afterDismissed().subscribe(() => {
          location.reload();
        });
      },
      (error) => {
        console.log(error);
        this.abrirSnackBar(error.error.mensaje);
        this.cargando = false;
      }
    ));
  }

  comprarProducto(producto: Producto) {
    console.log(['Origen', producto]);
    const compra: Compra = this.obtenerCompra(producto);
    this.guardarCompra(compra);
  }

  verDetalle(producto: Producto) {

  }

  private abrirSnackBar(message: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, 'OK', {
      duration: 5000,
    });
  }

}
