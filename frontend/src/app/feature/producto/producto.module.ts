import { NgModule } from '@angular/core';

import { ProductoRoutingModule } from './producto-routing.module';
import { ListarProductoComponent } from './components/listar-producto/listar-producto.component';
import { CrearProductoComponent } from './components/crear-producto/crear-producto.component';
import { ProductoComponent } from './components/producto/producto.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TablaProductosComponent } from './components/tabla-productos/tabla-productos.component';

@NgModule({
  declarations: [
    CrearProductoComponent,
    ListarProductoComponent,
    ProductoComponent,
    TablaProductosComponent
  ],
  imports: [
    ProductoRoutingModule,
    SharedModule
  ]
})
export class ProductoModule { }
