import { browser, by, element, ElementFinder } from 'protractor';

export class HomePage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root mat-toolbar span')).getText() as Promise<string>;
  }

  obtenerMensajePublicidad() {
    return element(by.id('titulo-publicidad')).getText() as Promise<string>;
  }

  obtenerCedulaDeInput() {
    return element(by.id('home-input-cedula')).getText() as Promise<string>;
  }

  obtenerNombreDeInput() {
    return element(by.id('home-input-nombre')).getText() as Promise<string>;
  }

  obtenerCompErrorCedula() {
    return element(by.id('home-error-cedula')) as ElementFinder;
  }

  obtenerCompErrorNombre() {
    return element(by.id('home-error-nombre')) as ElementFinder;
  }

  obtenerCompProducto() {
    return element(by.css('app-root app-producto')) as ElementFinder;
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


}
