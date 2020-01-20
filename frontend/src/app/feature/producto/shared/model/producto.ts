import { Compra } from 'src/app/feature/compra/shared/model/compra.model';

export class Producto {
  codigo: string;
  nombre: string;
  valor: number;
  descuento: number;
  fecha: string;
  cedulaVendedor: string;
  nombreVendedor: string;
  compra: Compra;
}
