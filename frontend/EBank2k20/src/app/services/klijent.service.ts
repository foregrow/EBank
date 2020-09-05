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

  getByKorIme(korIme): Observable<Klijent[]>{
    return this._http.get<Klijent[]>(`${this.klijentBaseUrl}/korisnik/${korIme}`);
  }
  getOdobreniOrNeodobreniOfBanka(korIme,param): Observable<Klijent[]>{
    return this._http.get<Klijent[]>(`${this.klijentBaseUrl}/banka/${korIme}/${+param}`);
  }

  //klijent salje zahtev za otvaranje racuna
  add(data:Klijent,bid,vid){
    return this._http.post<any>(`${this.klijentBaseUrl}/zahtev/${+bid}/${+vid}`,data);
  }

  update(data:Klijent){
    return this._http.put<any>(this.klijentBaseUrl,data);
  }

  delete(id){
    return this._http.delete(`${this.klijentBaseUrl}/${+id}`);
  }
}
