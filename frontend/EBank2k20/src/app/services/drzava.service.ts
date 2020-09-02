import { Injectable } from '@angular/core';
import { Server } from '../util/server';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Drzava } from '../model/drzava';

@Injectable({
  providedIn: 'root'
})
export class DrzavaService {

 //http://localhost:8080/api/drzava
 private readonly drzavaBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.drzava}`;
 constructor(private _http: HttpClient) { }

 getAll() : Observable<Drzava[]>{
   return this._http.get<Drzava[]>(this.drzavaBaseUrl);
 }

 getById(id): Observable<Drzava[]>{
   return this._http.get<Drzava[]>(`${this.drzavaBaseUrl}/${+id}`);
 }

 delete(id): Observable<Drzava[]>{
   return this._http.delete<Drzava[]>(`${this.drzavaBaseUrl}/${+id}`);
 }

 add(data:Drzava){
   return this._http.post<any>(this.drzavaBaseUrl,data);
 }

 update(data:Drzava){
   return this._http.put<any>(this.drzavaBaseUrl,data);
 }
}
