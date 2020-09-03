import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Server } from './../util/server';
import { Injectable } from '@angular/core';
import { Klijent } from '../model/klijent';

@Injectable({
  providedIn: 'root'
})
export class KlijentService {

  private readonly klijentBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.klijent}`;
  constructor(private _http: HttpClient) { }

  getAll() : Observable<Klijent[]>{
    return this._http.get<Klijent[]>(this.klijentBaseUrl);
  }
}
