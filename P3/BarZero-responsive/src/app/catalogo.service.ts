import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Catalogo } from './catalogo';
import { CATALOGO } from './listaCatalogo';

@Injectable({
  providedIn: 'root'
})
export class CatalogoService {

  constructor() { }

  getCatalogo(): Observable<Catalogo[]>{
    return of(CATALOGO);
  }

  getCatalogoId(id: number){
    return of(CATALOGO.find(catalogo=> catalogo.id===id));
  }
}
