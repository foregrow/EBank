import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Klijent } from 'src/app/model/klijent';
import { RacunService } from 'src/app/services/racun.service';
import { Racun } from 'src/app/model/racun';

@Component({
  selector: 'app-otvaranje-racuna-izvrsilac',
  templateUrl: './otvaranje-racuna-izvrsilac.component.html',
  styleUrls: ['./otvaranje-racuna-izvrsilac.component.css']
})
export class OtvaranjeRacunaIzvrsilacComponent implements OnInit {

  racuni;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _rs: RacunService
  ) { }

  ngOnInit(): void {
    this.getAllNeodobreniByBanka();
  }

  getAllNeodobreniByBanka(){
    this._rs.getAllNeodobreniByBanka(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.racuni =data;
      }
    )
  }

  submit(param,obj){
    if(param === 'accepted'){
      //put
      var racun = new Racun(obj.id,null,null,null,null,null,null,null,null,null,null,null);
      console.log(racun);
      this._rs.update(racun).subscribe(
        data=>{
          this.getAllNeodobreniByBanka();
          alert("Uspesno odobren zahtev!");
        },error=>{
          this.getAllNeodobreniByBanka();
          alert("Uspesno odobren zahtev!");
        });
          
    }else if(param === 'rejected'){
      //delete
      this._rs.delete(obj.id).subscribe(
        data =>{
          this.getAllNeodobreniByBanka();
          alert("Uspesno odbijen zahtev!");
        },error=>{
          this.getAllNeodobreniByBanka();
          alert("Uspesno odbijen zahtev!");
        });
      
    }
  }


}
