import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Server } from '../util/server';
import { Observable } from 'rxjs';
import { Korisnik } from '../model/korisnik';


@Injectable({
  providedIn: 'root'
})
export class KorisnikService {

  private readonly korisnikBaseUrl = `${Server.baseUrl}/${Server.api}/${Server.korisnik}`;

  
  constructor(private _http: HttpClient,
    private _router: Router
    ) { }

  getAll() : Observable<Korisnik[]>{
    return this._http.get<Korisnik[]>(this.korisnikBaseUrl);
  }

  add(data:Korisnik){
    return this._http.post<any>(this.korisnikBaseUrl,data);
  }

  login(userData){
    console.log(userData);
    return this._http.post<any>(`${Server.baseUrl}/${Server.authenticateUrl}`, userData);
  }

  getRedirectedFromLogin(role){
    if(role == 'GRESKA'){
      this._router.navigate(['login']);
    }else{
      if(role == 'ADMIN'){
        this._router.navigate(['admin']);
      }else if(role == 'KORISNIK'){
        this._router.navigate(['korisnik']);
      }else if(role == 'IZVRSILAC'){
        this._router.navigate(['izvrsilac']);
      }
    }  
  }

  loggedIn(){
    if(!this.getRole() || !this.getToken()){
      return false;
    }
    return true;
  }

  getToken(){
    return localStorage.getItem('jwt');
  }

  getRole(){
    return localStorage.getItem('role');
  }

  logout(){
    localStorage.removeItem("jwt");
    localStorage.removeItem('role');
    this._router.navigate(['login'])
  }

  isAuthorized(roles): boolean {
    var isMatch = false;
    
    var role = localStorage.getItem('role');
    roles.forEach(element => {
      if(element === role){
        isMatch = true;
        return false;
      }
    });
    
    return isMatch;
  }
}
