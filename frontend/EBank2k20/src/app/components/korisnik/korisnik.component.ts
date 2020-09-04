import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent implements OnInit {

  klijent;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService
  ) { }

  ngOnInit(): void {
    this.getByKorId();
  }

  getByKorId(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data => {
        this.klijent = data;
      });
  }

}
