import { browser, by, element, ElementFinder } from 'protractor';

export class CompraPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  obtenerCompCompra() {
    return element(by.css('app-root app-compra')) as ElementFinder;
  }

  obtenerTablaCompras() {
    return element(by.id('tabla-compras')) as ElementFinder;
  }

  obtenerTituloTablaCompras() {
    return element(by.id('compra-titulo-tabla')).getText() as Promise<string>;
  }

  modificarCedulaInput(cedula: string) {
    return element(by.id('home-input-cedula')).sendKeys(cedula) as Promise<void>;
  }

  modificarNombreInput(nombre: string) {
    return element(by.id('home-input-nombre')).sendKeys(nombre) as Promise<void>;
  }

  darClickEnBtnIngresar() {
    return element(by.id('home-btn-ingresar')).click() as Promise<void>;
  }

  darClickEnBtnLimpiar() {
    return element(by.id('home-btn-limpiar')).click() as Promise<void>;
  }

  darClickEnNavCompra() {
    return element(by.id('Compra')).click() as Promise<void>;
  }
}
