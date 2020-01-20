import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

import { BaseService } from 'src/app/core/services/base.service';
import { environment } from 'src/environments/environment';
import { Producto } from '../model/producto';

@Injectable({
  providedIn: 'root'
})
export class ProductoService extends BaseService {

  constructor(protected http: HttpClient) { super(http); }

  public consultarCache() {
    return this.doGet<Producto[]>(`${environment.endpoint}productos/productos`, this.optsNameCache('consultar productos con cache'));
  }

  public consultar() {
    return this.doGet<Producto[]>(`${environment.endpoint}productos`, this.optsName('consultar productos'));
  }

  public consultarConParametros(parametros: HttpParams) {
    return this.doGetParameters<Producto[]>(`${environment.endpoint}productos`, parametros, this.optsName('consultar productos'));
  }

  public guardar(producto: Producto) {
    return this.doPost<Producto, boolean>(`${environment.endpoint}productos`, producto, this.optsName('crear/actualizar productos'));
  }

  public eliminar(producto: Producto) {
    return this.doPost<Producto, boolean>(`${environment.endpoint}productos`, producto, this.optsName('eliminar productos'));
  }
}
