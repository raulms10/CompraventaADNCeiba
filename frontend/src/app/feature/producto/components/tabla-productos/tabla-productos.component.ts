import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';

import { Producto } from '../../shared/model/producto';


@Component({
  selector: 'app-tabla-productos',
  templateUrl: './tabla-productos.component.html',
  styleUrls: ['./tabla-productos.component.css']
})
export class TablaProductosComponent implements OnInit {

  columnasMostrar: string[];
  dataSource: MatTableDataSource<Producto>;
  @Input() productos: Producto[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor() { }

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

}
