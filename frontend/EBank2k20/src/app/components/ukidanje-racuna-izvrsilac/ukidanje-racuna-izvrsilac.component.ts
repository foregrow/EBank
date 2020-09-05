import { Component, OnInit } from '@angular/core';
import { UkidanjeRacunaService } from 'src/app/services/ukidanje-racuna.service';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { UkidanjeRacuna } from 'src/app/model/ukidanjeracuna';

@Component({
  selector: 'app-ukidanje-racuna-izvrsilac',
  templateUrl: './ukidanje-racuna-izvrsilac.component.html',
  styleUrls: ['./ukidanje-racuna-izvrsilac.component.css']
})
export class UkidanjeRacunaIzvrsilacComponent implements OnInit {

  ukidanjaUToku = [];
  constructor(
    private _urs: UkidanjeRacunaService,
    private _kls: KlijentService,
    private _ks: KorisnikService) {}

  ngOnInit(): void {

    this.getAllForBankaUToku();
  }


  getAllForBankaUToku(){
    this._urs.getAllForBankaUToku(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.ukidanjaUToku = data;
      });
  }

  submitUkidanje(param,id){
    if(param === 'accepted'){
      var ukidanje = new UkidanjeRacuna(id,null,null,null,null,null);
      this._urs.update(ukidanje).subscribe(
        data=>{
          this.getAllForBankaUToku();
        },error=>{
          alert(error.error);
        });
    }else if(param === 'rejected'){
      //odbijeno ukidanje racuna (delete)
      this._urs.delete(id).subscribe(
        data=>{
          this.getAllForBankaUToku();
        },error=>{
          alert(error.error);
        });
    }
  }

}
