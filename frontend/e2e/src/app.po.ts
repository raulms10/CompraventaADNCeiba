import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get(browser.baseUrl) as Promise<any>;
  }

  getTitleText() {
    return element(by.css('app-root mat-toolbar span')).getText() as Promise<string>;
  }

  getTitlePageText() {
    return browser.getTitle() as Promise<string>;
  }
}
