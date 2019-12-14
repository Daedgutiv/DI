import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../player';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PlayerService } from '../player.service';
import { FormGroup } from '@angular/forms';
import { ngModuleJitUrl } from '@angular/compiler';
import { stringify } from '@angular/compiler/src/util';

import { FormControl, Validators } from '@angular/forms';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from '../app.component';

@Component({
  selector: 'app-player-add',
  templateUrl: './player-add.component.html',
  styleUrls: ['./player-add.component.css']
})
export class PlayerAddComponent implements OnInit {
  @Input() player: Player;
  players: Player[];
  selected: string = 'Delantero';

  posiciones = [
    "Delantero",
    "Medio",
    "Defensa",
    "Portero",];

  group: FormGroup;

  pos = "";
  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private location: Location
  ) {
    AppComponent.ocultar = false;
  }

  ngOnInit() {
    this.group = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(2), Validators.maxLength(16)]),
      dorsal: new FormControl('', [Validators.required, Validators.minLength(1), Validators.maxLength(2)])
    });
  }

  esNum: Boolean;

  create(name2: number, dorsal: number): void {
    var position = this.selected;
    if (!name2 || !dorsal || !position) { return; }
    if (isNaN(dorsal)) {
      return;
    }
    if (isNaN(name2) == false) {
      return;
    }
    var name: string;
    name = name2.toString();
    this.playerService.addPlayer({ name, dorsal, position } as Player)
      .subscribe(player => {
        this.players.push(player);
      });
    this.location.back();
  }
  goBack(): void {
    this.location.back();
  }

}
