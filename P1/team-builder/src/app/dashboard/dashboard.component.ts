import { Component, OnInit } from '@angular/core';
import { Player } from '../player';
import { PlayerService } from '../player.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  players: Player[] = [];
  players2: Player[] = [];
  constructor(private playerService: PlayerService) { }

  ngOnInit() {
    this.getPlayers();
    this.getPlayers2();
  }

  getPlayers(): void {
    this.playerService.getPlayers().subscribe(players => this.players = players.slice(1, 5));
  }
  getPlayers2(): void {
    this.playerService.getPlayers().subscribe(players => this.players2 = players.slice(6,10));
  }
}
