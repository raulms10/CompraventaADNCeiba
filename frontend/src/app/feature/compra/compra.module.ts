import { NgModule } from '@angular/core';

import { CompraRoutingModule } from './compra-routing.module';
import { CompraComponent } from './components/compra/compra.component';
import { ListarCompraComponent } from './components/listar-compra/listar-compra.component';
import { TablaCompraComponent } from './components/tabla-compra/tabla-compra.component';
import { SharedModule } from 'src/app/shared/shared.module';


@NgModule({
  declarations: [CompraComponent, ListarCompraComponent, TablaCompraComponent],
  imports: [
    SharedModule,
    CompraRoutingModule
  ]
})
export class CompraModule { }
