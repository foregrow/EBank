import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Nalog } from '../model/nalog';
import { Server } from '../util/server';

@Injectable({
  providedIn: 'root'
})
export class NalogService {

  private readonly nalogBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.nalog}`;
  constructor(private _http: HttpClient) { }

  
  addNalog(data:Nalog){
    return this._http.post<any>(this.nalogBaseUrl,data);
  }
  upload(data){
    return this._http.post<any>(`${this.nalogBaseUrl}/importFiles`, data);
  }

}
