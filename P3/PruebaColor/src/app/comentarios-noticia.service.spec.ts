import { TestBed } from '@angular/core/testing';

import { ComentariosNoticiaService } from './comentarios-noticia.service';

describe('ComentariosNoticiaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ComentariosNoticiaService = TestBed.get(ComentariosNoticiaService);
    expect(service).toBeTruthy();
  });
});
