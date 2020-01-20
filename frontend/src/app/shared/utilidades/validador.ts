import { ValidatorFn, AbstractControl } from '@angular/forms';
import { isNumeric } from 'rxjs/util/isNumeric';
export class Validador {

  /* Se envia true si es requerido que ingrese información, y se mostrará el respectivo error
     al dejar el campo en blaco, si se envia false es porque el campo es opcional y si no ingresa nada
     cuenta como número, pero si ingresa algo, ese algo debe ser un valor numérico*/
  static validadorNumerosEnteros(tieneRequerido: boolean): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      const valor: string = String(control.value);
      const hayValor = valor !== undefined && valor !== null && valor !== '';
      if ((!hayValor && !tieneRequerido) || (hayValor && (valor.includes('.') || valor.includes(' ') || !isNumeric(valor)))) {
        return {integer: true};
      }
      return null;
    };
  }

  // Aplica lo mismo que validadorNumerosEnteros
  static validadorEspacios(tieneRequerido: boolean): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      const valor: string = String(control.value);
      const hayValor = valor !== undefined && valor !== null && valor !== '';
      if ((!hayValor && !tieneRequerido) || (hayValor && valor.trim() === '')) {
        return {espacios: true};
      }
      return null;
    };
  }

   // Aplica lo mismo que validadorNumerosEnteros
   static validadorRango(tieneRequerido: boolean, valorMinimo: number, valorMaximo: number): ValidatorFn {
    return (control: AbstractControl): { [key: string]: boolean } | null => {
      const valor: string = String(control.value);
      const hayValor = valor !== undefined && valor !== null && valor !== '';
      const numero: number = +valor;
      if ((!hayValor && !tieneRequerido) || (hayValor && (numero < valorMinimo || valorMaximo < numero))) {
        return {rango: true};
      }
      return null;
    };
  }

  static esDiaSemanaPermitido(diasPermitos: string): boolean {
    const fecha = new Date().getDay();
    return diasPermitos.includes(fecha + '');
  }

  static esStringVacio(str: string) {
    return str === undefined || str === '' || str == null || str === 'null';
  }

  static getFormatoFecha(fecha: Date) {
    if (fecha === undefined || fecha === null) {
      return '';
    }
    const locaDate = fecha.toLocaleString().split('-');
    const dia = Number(locaDate[0]) <= 9 ? '0' + locaDate[0] : locaDate[0];
    const mes = Number(locaDate[1]) <= 9 ? '0' + locaDate[1] : locaDate[1];
    const anno = fecha.getFullYear();
    return  anno + '-' + mes + '-' + dia;
  }
}
