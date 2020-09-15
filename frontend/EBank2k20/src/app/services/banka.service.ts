import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Banka } from '../model/banka';
import { HttpClient } from '@angular/common/http';
import { Server } from '../util/server';

@Injectable({
  providedIn: 'root'
})
export class BankaService {

  //http://localhost:8080/api/banka
  private readonly bankaBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.banka}`;
  constructor(private _http: HttpClient) { }

  getAll() : Observable<Banka[]>{
    return this._http.get<Banka[]>(this.bankaBaseUrl);
  }

  getById(id): Observable<Banka[]>{
    return this._http.get<Banka[]>(`${this.bankaBaseUrl}/${+id}`);
  }

  bankeKlijenta(korIme: string): Observable<Banka[]>{
    return this._http.get<Banka[]>(`${this.bankaBaseUrl}/bankeKlijenta/${korIme}`);
  }

  delete(id): Observable<Banka[]>{
    return this._http.delete<Banka[]>(`${this.bankaBaseUrl}/${+id}`);
  }

  add(data:Banka){
    return this._http.post<any>(this.bankaBaseUrl,data);
  }

  update(data:Banka){
    return this._http.put<any>(this.bankaBaseUrl,data);
  }
}
