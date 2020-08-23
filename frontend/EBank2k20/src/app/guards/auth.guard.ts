import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { KorisnikService } from '../services/korisnik.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private _korService: KorisnikService,
              private _router: Router){}
              
  canActivate(): boolean{
    if(this._korService.loggedIn()){
      return true
    }else{
      this._router.navigate(['login']);
      return false
    }
  }
  
}
