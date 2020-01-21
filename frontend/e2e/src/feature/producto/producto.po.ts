import { browser, by, element, ElementFinder } from 'protractor';

export class ProductoPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  darClickEnNavProducto() {
    return element(by.id('Producto')).click() as Promise<void>;
  }

  obtenerCompListarProducto() {
    return element(by.css('app-root app-producto app-listar-producto')) as ElementFinder;
  }

  obtenerCompCrearProducto() {
    return element(by.css('app-root app-producto app-crear-producto')) as ElementFinder;
  }

  obtenerTablaProductos() {
    return element(by.id('tabla-productos')) as ElementFinder;
  }

  obtenerFormCrearProductos() {
    return element(by.id('form-crear-producto')) as ElementFinder;
  }

  obtenerTituloTablaProducto() {
    return element(by.id('producto-titulo-tabla')).getText() as Promise<string>;
  }

  obtenerTituloCrearProducto() {
    return element(by.id('producto-titulo-crear')).getText() as Promise<string>;
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

  darClickEnBtnNuevoProducto() {
    return element(by.id('producto-btn-nuevo')).click() as Promise<void>;
  }
}
