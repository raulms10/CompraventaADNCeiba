import { HomePage } from './home.po';
import { browser, logging } from 'protractor';

describe('workspace-project Home', () => {
  const CEDULA = '123456789';
  const NOMBRE = 'Juanita Gomez';

  let page: HomePage;

  beforeEach(() => {
    page = new HomePage();
  });

  it('debe mostrar nombre de Compraventa TodoEnUno', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Compraventa TodoEnUno');
  });

  it('debe mostrar mensaje publicitario bienvenida', () => {
    page.navigateTo();
    expect(page.obtenerMensajePublicidad()).toEqual('¡Compra lo que quieres o véndelo!');
  });

  it('Debe validar campos para ingresar', () => {
    page.navigateTo();
    page.darClickEnBtnLimpiar();
    page.modificarCedulaInput('');
    page.modificarNombreInput('');
    page.darClickEnBtnIngresar();
    const elementoCedula = page.obtenerCompErrorCedula();
    const elementoNombre = page.obtenerCompErrorNombre();
    expect(elementoCedula.isDisplayed()).toBeTruthy();
    expect(elementoNombre.isDisplayed()).toBeTruthy();
    expect(elementoCedula.getText()).toEqual('Debe ingresar su cédula');
    expect(elementoNombre.getText()).toEqual('Debe ingresar su nombre');
  });

  it('Debe limpiar los campos para ingresar', () => {
    page.navigateTo();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnLimpiar();
    expect(page.obtenerCedulaDeInput()).toEqual('');
    expect(page.obtenerNombreDeInput()).toEqual('');
  });

  it('Debe ingresar a productos', () => {
    page.navigateTo();
    page.modificarCedulaInput(CEDULA);
    page.modificarNombreInput(NOMBRE);
    page.darClickEnBtnIngresar();
    const elementoProducto = page.obtenerCompProducto();
    expect(elementoProducto.isDisplayed()).toBeTruthy();
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
