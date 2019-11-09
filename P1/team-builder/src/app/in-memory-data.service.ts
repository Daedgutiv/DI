import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Player } from './player';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const players = [
      {id: 11, name: 'Castolo', dorsal: 9, position:'Delantero' },
      {id: 12, name: 'Espimas', dorsal: 8, position:'Medio' },
      {id: 13, name: 'Ordaz', dorsal: 11, position:'Delantero' },
      {id: 14, name: 'Minanda', dorsal: 10, position:'Medio' },
      {id: 15, name: 'Ivarov', dorsal: 1, position:'Portero' },
      {id: 16, name: 'Stremer', dorsal: 2, position:'Defensa' },
      {id: 17, name: 'Ximelez', dorsal: 7, position:'Medio' },
      {id: 18, name: 'Jaric', dorsal: 3, position:'Defensa' },
      {id: 19, name: 'Valeny', dorsal: 4, position:'Defensa' },
      {id: 20, name: 'Stein', dorsal: 5, position:'Medio' }
    ];
    return {players};
  }

  // Overrides the genId method to ensure that a player always has an id.
  // If the players array is empty,
  // the method below returns the initial number (11).
  // if the players array is not empty, the method below returns the highest
  // player id + 1.
  genId(players: Player[]): number {
    return players.length > 0 ? Math.max(...players.map(player => player.id)) + 1 : 11;
  }
}