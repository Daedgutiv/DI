import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../player';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PlayerService } from '../player.service';
import { escapeIdentifier } from '@angular/compiler/src/output/abstract_emitter';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-player-detail',
  templateUrl: './player-detail.component.html',
  styleUrls: ['./player-detail.component.css']
})
export class PlayerDetailComponent implements OnInit {
  @Input() player: Player;

  posiciones= [
    "Delantero",
    "Medio",
    "Defensa",
    "Portero",];

  group: FormGroup;
  
  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private location: Location
  ) {
    AppComponent.ocultar=false;
   }

  ocultar:boolean=true;
  
  ngOnInit() {
    this.getPlayer();
    this.group = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]),
      dorsal: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(2), Validators.pattern('[0-9]{1,2}')])
    });
  }

  getPlayer(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.playerService.getPlayer(id).subscribe(player => this.player = player);
  }

  save(): void {
    this.playerService.updatePlayer(this.player)
      .subscribe(() => this.goBack());
  }

  goBack(): void {
    this.location.back();
  }
}
