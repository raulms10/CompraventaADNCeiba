import { Component, OnInit, Input, ViewChild, OnDestroy } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSnackBarRef, SimpleSnackBar, MatSnackBar } from '@angular/material';
import { Subscription } from 'rxjs';

import { Producto } from 'src/app/feature/producto/shared/model/producto';
import { Compra } from '../../shared/model/compra.model';
import { CompraService } from '../../shared/service/compra.service';
import { Constantes } from 'src/app/shared/utilidades/constantes';
import { DatePipe } from '@angular/common';
import { Validador } from 'src/app/shared/utilidades/validador';

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
  @Input() cedula: string;
  @Input() nombre: string;
  cargando = false;
  snackBarRef: MatSnackBarRef<SimpleSnackBar>;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(protected compraService: CompraService, private snackBar: MatSnackBar, public datepipe: DatePipe) { }

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
    compra.cedulaComprador = this.cedula;
    compra.nombreComprador = this.nombre;
    compra.fechaCompra = this.datepipe.transform(new Date(), Constantes.FORMATO_FECHA);
    compra.valorPagado = this.calcularValorPagado(producto);
    return compra;
  }

  private guardarCompra(compra: Compra) {
    this.subscriptionServices.push(this.compraService.guardar(compra).subscribe(
      (result) => {
        this.snackBarRef = this.abrirSnackBar(Constantes.COMPRA_GUARDADA);
        this.snackBarRef.afterDismissed().subscribe(() => {
          // location.reload();
        });
        this.cargando = false;
      },
      (error) => {
        console.log(error);
        this.abrirSnackBar(error.error.mensaje);
        this.cargando = false;
      }
    ));
  }

  private calcularValorPagado(prod: Producto): number {
    const esViernes = Validador.esDiaSemanaPermitido(Constantes.diaViernes);
    let valorAPagar: number;
    if (esViernes === true) {
      valorAPagar = Validador.obtenerDescuento(prod.valor, prod.descuento);
    } else {
      valorAPagar = prod.valor;
    }
    return valorAPagar;
  }

  comprarProducto(producto: Producto) {
    this.cargando = true;
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
