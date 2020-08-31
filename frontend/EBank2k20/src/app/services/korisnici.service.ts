import { Korisnik } from './../model/korisnik';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Server } from './../util/server';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class KorisniciService {
  private readonly korisniciBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.korisnik}`;
  constructor(private _http: HttpClient) { }

  getAll() : Observable<Korisnik[]>{
    return this._http.get<Korisnik[]>(this.korisniciBaseUrl);
  }
}
