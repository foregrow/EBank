import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { Router } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { IzvestajService } from 'src/app/services/izvestaj.service';
import * as FileSaver from 'file-saver';
import { BankaService } from 'src/app/services/banka.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent implements OnInit {

  klijent;
  datumOd;
  datumDo;
  bankeKlijenta = [];
  selectedBanka;
  isBankaSelected = false;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _is: IzvestajService,
    private _bs: BankaService
  ) { }

  ngOnInit(): void {
    this.getByKorIme();
    this.getBankeKlijenta();
    
  }

  onBankaSelected(){
    if(this.selectedBanka != undefined && this.selectedBanka != null)
      this.isBankaSelected = true;
  }
  onDatumSelected(event: any,param) { // without type info
    if(param === 'od')
      this.datumOd = event.target.value;
    else if(param === 'do')
      this.datumDo = event.target.value; 
  }

  download(racunId,klijentId,bankaId,tipIzvestaja){
    if(tipIzvestaja === 0){
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
    }else if(tipIzvestaja === 1){
      if(bankaId != undefined && bankaId != null)
        this._is.getIzvestaj(0,0,-1,bankaId,klijentId,tipIzvestaja);
      
    }
    
  }
  getByKorIme(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data => {
        this.klijent = data;
      });
  }

  getBankeKlijenta(){
    this._bs.bankeKlijenta(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.bankeKlijenta = data;
      },error=>{
        console.log("Greska prilikom ucitavanja!");
      }
    );
  }

}
