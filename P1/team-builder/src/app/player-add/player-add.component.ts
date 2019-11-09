import { Component, OnInit, Input } from '@angular/core';
import { Player } from '../player';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { PlayerService } from '../player.service';
import { FormGroup } from '@angular/forms';
import { ngModuleJitUrl } from '@angular/compiler';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-player-add',
  templateUrl: './player-add.component.html',
  styleUrls: ['./player-add.component.css']
})
export class PlayerAddComponent implements OnInit {
  @Input() player: Player;
  players: Player[];

  posicion = "";
  constructor(
    private route: ActivatedRoute,
    private playerService: PlayerService,
    private location: Location
  ) { }

  ngOnInit() {
  }

  radioCambio(position: string) {
    this.posicion = position;
  }

  esNum:Boolean;

  create(name: string, dorsal: number): void {
    name = name.trim();
    var position =this.posicion;
    if (!name || !dorsal || !position) { return; }
    if (isNaN(dorsal)){
      return;
    }
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
