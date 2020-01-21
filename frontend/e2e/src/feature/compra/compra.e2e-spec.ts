import { CompraPage } from './compra.po';
import { browser, logging } from 'protractor';

describe('workspace-project Compra', () => {
  const CEDULA = '123456789';
  const NOMBRE = 'Juanita Gomez';

  let page: CompraPage;

  beforeEach(() => {
    page = new CompraPage();
  });

  it('debe ingresar a compra', () => {
    page.navigateTo();
    page.darClickEnBtnLimpiar();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnIngresar();
    page.darClickEnNavCompra();
    expect(page.obtenerCompCompra().isDisplayed()).toBeTruthy();
    expect(page.obtenerTituloTablaCompras()).toEqual('Catálogo');
    expect(page.obtenerTablaCompras().isDisplayed()).toBeTruthy();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
