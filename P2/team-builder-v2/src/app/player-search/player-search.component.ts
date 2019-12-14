import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Player } from '../player';
import { PlayerService } from '../player.service';

@Component({
  selector: 'app-player-search',
  templateUrl: './player-search.component.html',
  styleUrls: [ './player-search.component.css' ]
})
export class PlayerSearchComponent implements OnInit {
  players$: Observable<Player[]>;
  private searchTerms = new Subject<any>();

  constructor(private playerService: PlayerService) {}

  // Push a search term into the observable stream.
  search(term: number): void {
      this.searchTerms.next(term);   
  }

  ngOnInit(): void {
    this.players$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      
      switchMap((term: number) => this.playerService.searchPlayers(term)),
    );
  }
}