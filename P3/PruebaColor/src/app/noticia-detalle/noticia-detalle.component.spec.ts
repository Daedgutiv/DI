import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NoticiaDetalleComponent } from './noticia-detalle.component';

describe('NoticiaDetalleComponent', () => {
  let component: NoticiaDetalleComponent;
  let fixture: ComponentFixture<NoticiaDetalleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NoticiaDetalleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NoticiaDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
