import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { IzvestajService } from 'src/app/services/izvestaj.service';
import * as FileSaver from 'file-saver';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent implements OnInit {

  klijent;
  datumOd;
  datumDo;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _is: IzvestajService
  ) { }

  ngOnInit(): void {
    this.getByKorIme();
  }

  onDatumSelected(event: any,param) { // without type info
    if(param === 'od')
      this.datumOd = event.target.value;
    else if(param === 'do')
      this.datumDo = event.target.value; 
  }

  download(racunId,klijentId,tipIzvestaja){
    if(this.datumOd === undefined || this.datumOd === null || this.datumDo === undefined || this.datumDo === null){
      alert("Morate selectovati oba datuma!");
      return;
    }
    let dateOd = new Date(this.datumOd).getTime();
    let dateDo = new Date(this.datumDo).getTime();
    if(dateOd - dateDo  > 0){
      alert("Pocetni datum mora biti pre krajnjeg datuma!")
      return;
    }
    this._is.getIzvestaj(dateOd,dateDo,racunId,-1,klijentId,tipIzvestaja);
    

  }
  getByKorIme(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data => {
        this.klijent = data;
      });
  }

}
