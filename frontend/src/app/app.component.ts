import { Component, OnInit } from '@angular/core';
import { Item } from '@ceiba/navbar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Compraventa';
  public companies: Item[] = [
    { url: '/home', nombre: 'Home' },
    { url: '/producto', nombre: 'Producto' },
    { url: '/compra', nombre: 'Compra' }
  ];
  itemActivo = location.pathname;

  constructor(private router: Router) {}

  ngOnInit() {
    const elemento = this.companies.find(item => location.pathname.includes(item.url));
    this.itemActivo = elemento ? elemento.url : this.companies[0].url;
  }

  navegar(url: string) {
    this.itemActivo = url;
    this.router.navigate([url]);
  }
}
