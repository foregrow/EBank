import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import { Observable } from 'rxjs';
import { Server } from '../util/server';

@Injectable({
  providedIn: 'root'
})
export class IzvestajService {

  //http://localhost:8080/api/izvestaj
 private readonly izvestajBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.izvestaj}`;
 constructor(private _http: HttpClient) { }

 getIzvestaj(odMili,doMili,racunId,bankaId,klijentId,tipIzvestaja) : any{
   return this._http.get(`${this.izvestajBaseUrl}/download/${+odMili}/${+doMili}/${+racunId}/${+bankaId}/${+klijentId}/${+tipIzvestaja}`, 
   {  responseType: 'blob' as 'json' }).subscribe((res) => {
    var file = new Blob([res as BlobPart], { type: 'application/pdf' });      
    var fileName = `izvestaj_${file.size.toString()}`;   
    FileSaver.saveAs(file, fileName);
   },error=>{
     alert("Za uneti datum ne postoji izvestaj!");
   });
  }

}
