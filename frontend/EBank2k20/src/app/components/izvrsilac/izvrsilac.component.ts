import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-izvrsilac',
  templateUrl: './izvrsilac.component.html',
  styleUrls: ['./izvrsilac.component.css']
})
export class IzvrsilacComponent implements OnInit {

  klijenti;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService
  ) { }

  ngOnInit(): void {
    this.getAktivniOfBanka();
  }

  getAktivniOfBanka(){
    this._kls.getAktivniOfBanka(this._ks.getLoggedInUserKorIme(),1).subscribe(
      data => {
        this.klijenti = data;
      });
  }

}
