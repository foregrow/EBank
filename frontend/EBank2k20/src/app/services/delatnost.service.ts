import { Injectable } from '@angular/core';
import { Server } from '../util/server';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Delatnost } from '../model/delatnost';

@Injectable({
  providedIn: 'root'
})
export class DelatnostService {

  //http://localhost:8080/api/delatnost
  private readonly delatnostBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.delatnost}`;
  constructor(private _http: HttpClient) { }

  getAll() : Observable<Delatnost[]>{
    return this._http.get<Delatnost[]>(this.delatnostBaseUrl);
  }

  getById(id): Observable<Delatnost[]>{
    return this._http.get<Delatnost[]>(`${this.delatnostBaseUrl}/${+id}`);
  }

  delete(id): Observable<Delatnost[]>{
    return this._http.delete<Delatnost[]>(`${this.delatnostBaseUrl}/${+id}`);
  }

  add(data:Delatnost){
    return this._http.post<any>(this.delatnostBaseUrl,data);
  }

  update(data:Delatnost){
    return this._http.put<any>(this.delatnostBaseUrl,data);
  }
}
