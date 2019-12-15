import { Component, AfterViewInit, ElementRef } from '@angular/core';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {
  title = 'Team Builder';

  static ocultar: boolean;
  constructor(private elementRef: ElementRef) {
    AppComponent.ocultar = true;
  }

  get staticOcultar() {
    return AppComponent.ocultar;
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = 'white';
  }
}
