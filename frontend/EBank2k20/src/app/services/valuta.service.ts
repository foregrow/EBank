import { Injectable } from '@angular/core';
import { Server } from '../util/server';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Valuta } from '../model/valuta';

@Injectable({
  providedIn: 'root'
})
export class ValutaService {

  //http://localhost:8080/api/valuta
  private readonly valutaBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.valuta}`;
  constructor(private _http: HttpClient) { }

  getAll() : Observable<Valuta[]>{
    return this._http.get<Valuta[]>(this.valutaBaseUrl);
  }

  getById(id): Observable<Valuta[]>{
    return this._http.get<Valuta[]>(`${this.valutaBaseUrl}/${+id}`);
  }

  delete(id): Observable<Valuta[]>{
    return this._http.delete<Valuta[]>(`${this.valutaBaseUrl}/${+id}`);
  }

  add(data:Valuta){
    return this._http.post<any>(this.valutaBaseUrl,data);
  }

  update(data:Valuta){
    return this._http.put<any>(this.valutaBaseUrl,data);
  }
}
