import { AppPage } from './app.po';
import { browser, logging } from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('debe tener el titulo de la pagina igual a Compraventa', () => {
    page.navigateTo();
    expect(page.getTitlePageText()).toEqual('Compraventa');
  });

  it('debe mostrar el mensaje de bievenida de TodoEnUno', () => {
    page.navigateTo();
    expect(page.getTitleText()).toEqual('Compraventa TodoEnUno');
  });

  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    } as logging.Entry));
  });
});
