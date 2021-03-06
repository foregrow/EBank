import { Injectable } from '@angular/core';
import { Server } from '../util/server';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UkidanjeRacuna } from '../model/ukidanjeracuna';

@Injectable({
  providedIn: 'root'
})
export class UkidanjeRacunaService {

  //http://localhost:8080/api/ukidanjeRacuna
  private readonly ukidanjeRacunaBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.ukidanjeRacuna}`;
  constructor(private _http: HttpClient) { }

  getAllForBanka(korIme, param) : Observable<UkidanjeRacuna[]>{
    return this._http.get<UkidanjeRacuna[]>(`${this.ukidanjeRacunaBaseUrl}/izvrsilac/${korIme}/${+param}`);
  }
  getAllForKlijentUToku(korIme) : Observable<UkidanjeRacuna[]>{
    return this._http.get<UkidanjeRacuna[]>(`${this.ukidanjeRacunaBaseUrl}/klijent/${korIme}`);
  }

  add(data:UkidanjeRacuna){
    return this._http.post<any>(this.ukidanjeRacunaBaseUrl,data);
  }

  //prihvaceno ukidanje racuna
  update(data:UkidanjeRacuna){
    return this._http.put<any>(this.ukidanjeRacunaBaseUrl,data);
  }
  //odbijeno ukidanje racuna
  delete(id){
    return this._http.delete(`${this.ukidanjeRacunaBaseUrl}/${+id}`);
  }
}
