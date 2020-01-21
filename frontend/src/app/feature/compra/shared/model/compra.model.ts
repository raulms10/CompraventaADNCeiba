import { Producto } from 'src/app/feature/producto/shared/model/producto';

export class Compra {
  idCompra: number;
  cedulaComprador: string;
  nombreComprador: string;
  fechaCompra: string;
  valorPagado: number;
  comandoProducto: Producto;
}
