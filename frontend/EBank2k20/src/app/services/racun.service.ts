import { Injectable } from '@angular/core';
import { Server } from '../util/server';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Racun } from '../model/racun';

@Injectable({
  providedIn: 'root'
})
export class RacunService {

  private readonly racunBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.racun}`;

  
  constructor(private _http: HttpClient,
    private _router: Router
    ) { }

  getAllAktivni() : Observable<Racun[]>{
    return this._http.get<Racun[]>(this.racunBaseUrl);
  }

  getAllNeodobreniByBanka(korime): Observable<Racun[]>{
    return this._http.get<Racun[]>(`${this.racunBaseUrl}/izvrsilac/${korime}`);
  }
}
