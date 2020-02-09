import { Component, AfterViewInit, ElementRef } from '@angular/core';
import { MatMenuModule, MatButtonModule, MatIconModule, MatCardModule } from '@angular/material';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'ZumitosMaki-responsive';
  imports: [
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
  ]
}

