import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { BaseService } from 'src/app/core/services/base.service';
import { environment } from 'src/environments/environment';
import { Compra } from '../model/compra.model';

@Injectable({
  providedIn: 'root'
})
export class CompraService extends BaseService {

  constructor(protected http: HttpClient) { super(http); }

  public guardar(compra: Compra) {
    return this.doPost<Compra, boolean>(`${environment.endpoint}compras`, compra, this.optsName('crear compras'));
  }
}
