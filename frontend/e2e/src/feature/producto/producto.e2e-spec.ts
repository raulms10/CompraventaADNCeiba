import { ProductoPage } from './producto.po';
import { browser, logging } from 'protractor';

describe('workspace-project Producto', () => {
  const CEDULA = '123456789';
  const NOMBRE = 'Juanita Gomez';

  let page: ProductoPage;

  beforeEach(() => {
    page = new ProductoPage();
  });

  it('debe ingresar a producto', () => {
    page.navigateTo();
    page.darClickEnBtnLimpiar();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnIngresar();
    page.darClickEnNavProducto();
    expect(page.obtenerCompListarProducto().isDisplayed()).toBeTruthy();
    expect(page.obtenerTituloTablaProducto()).toEqual('Mis productos');
    expect(page.obtenerTablaProductos().isDisplayed()).toBeTruthy();
  });

  it('debe ingresar a crear producto', () => {
    page.navigateTo();
    page.darClickEnBtnLimpiar();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnIngresar();
    page.darClickEnNavProducto();
    page.darClickEnBtnNuevoProducto();
    expect(page.obtenerCompCrearProducto().isDisplayed()).toBeTruthy();
    expect(page.obtenerTituloCrearProducto()).toEqual('Crear producto');
    expect(page.obtenerFormCrearProductos().isDisplayed()).toBeTruthy();
  });

  it('debe validar los campos para crear producto', () => {
    page.navigateTo();
    page.darClickEnBtnLimpiar();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnIngresar();
    page.darClickEnNavProducto();
    page.darClickEnBtnNuevoProducto();
    page.darClickEnBtnGuardarProducto();
    const elementoCodigo = page.obtenerCompErrorCodigo();
    const elementoNombre = page.obtenerCompErrorNombre();
    const elementoValor = page.obtenerCompErrorValor();
    const elementoDescuento = page.obtenerCompErrorDesceunto();
    expect(elementoCodigo.isDisplayed()).toBeTruthy();
    expect(elementoNombre.isDisplayed()).toBeTruthy();
    expect(elementoValor.isDisplayed()).toBeTruthy();
    expect(elementoDescuento.isDisplayed()).toBeTruthy();
    expect(elementoCodigo.getText()).toEqual('Debe ingresar el código del producto');
    expect(elementoNombre.getText()).toEqual('Debe ingresar el nombre del producto');
    expect(elementoValor.getText()).toEqual('Debe ingresar el valor del producto');
    expect(elementoDescuento.getText()).toEqual('Debe ingresar el descuento del producto');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
