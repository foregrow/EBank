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
  ukinutiRacuni = [];
  constructor(
    private _urs: UkidanjeRacunaService,
    private _kls: KlijentService,
    private _ks: KorisnikService) {}

  ngOnInit(): void {

    this.getAllForBankaUToku();
    this.getAllForBankaUkinuti();
  }


  getAllForBankaUToku(){
    this._urs.getAllForBanka(this._ks.getLoggedInUserKorIme(), 0).subscribe(
      data=>{
        this.ukidanjaUToku = data;
      });
  }
  getAllForBankaUkinuti(){
    this._urs.getAllForBanka(this._ks.getLoggedInUserKorIme(), 1).subscribe(
      data=>{
        this.ukinutiRacuni = data;
      });
  }

  submitUkidanje(param,id){
    if(param === 'accepted'){
      var ukidanje = new UkidanjeRacuna(id,null,null,null,null,null);
      this._urs.update(ukidanje).subscribe(
        data=>{
          alert("Uspesno ukidanje racuna! ");
          this.getAllForBankaUToku();
          this.getAllForBankaUkinuti();
        },error=>{
          alert(error.error);
        });
    }else if(param === 'rejected'){
      //odbijeno ukidanje racuna (delete)
      this._urs.delete(id).subscribe(
        data=>{
          alert("Uspesno odbijanje zahteva za ukidanje! ");
          this.getAllForBankaUToku();
          this.getAllForBankaUkinuti();
        },error=>{
          alert(error.error);
        });
    }
  }

}
