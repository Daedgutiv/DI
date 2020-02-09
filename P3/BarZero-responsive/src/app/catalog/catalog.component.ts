import { Component, OnInit } from '@angular/core';
import { Catalogo } from '../catalogo';
import { CatalogoService } from '../catalogo.service';
import { CATALOGO } from '../listaCatalogo';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  catalogos: Catalogo[] = [];

  altura: string;

  constructor(private catalogoService: CatalogoService) { }

  ngOnInit() {
    this.getCatalogo();
  }

  getCatalogo() {
    this.catalogoService.getCatalogo().subscribe(catalogos => this.catalogos = catalogos.slice(0, CATALOGO.length));

    if (screen.width < 415) {
      this.altura = "1:0.6";
    } else if (screen.width > 415 && screen.width < 1150) {
      this.altura = "1:0.4";
    } else {
      this.altura = "1:0.3";
    }
  }

}
