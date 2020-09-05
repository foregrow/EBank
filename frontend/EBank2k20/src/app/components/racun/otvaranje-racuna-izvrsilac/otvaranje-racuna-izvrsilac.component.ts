import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Klijent } from 'src/app/model/klijent';
import { RacunService } from 'src/app/services/racun.service';

@Component({
  selector: 'app-otvaranje-racuna-izvrsilac',
  templateUrl: './otvaranje-racuna-izvrsilac.component.html',
  styleUrls: ['./otvaranje-racuna-izvrsilac.component.css']
})
export class OtvaranjeRacunaIzvrsilacComponent implements OnInit {

  klijenti;
  racuni;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _rs: RacunService
  ) { }

  ngOnInit(): void {
    this.getNeodobreniOfBanka();
    this.getAllNeodobreniByBanka();
  }

  getNeodobreniOfBanka(){
    this._kls.getOdobreniOrNeodobreniOfBanka(this._ks.getLoggedInUserKorIme(),0).subscribe(
      data => {
        this.klijenti = data;
      });
  }

  getAllNeodobreniByBanka(){
    this._rs.getAllNeodobreniByBanka(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.racuni =data;
      }
    )
  }

  submit(param,obj,paramZaBackEnd){
    if(param === 'accepted'){
      //put

      if(paramZaBackEnd == 1){
        // ako je 1 onda je id = klijent id, a 0 se salje za racun
        var klijent = new Klijent(obj.id,null,null,null,null,null,null,null,null,null,null);
        this._kls.update(paramZaBackEnd,0,klijent).subscribe(
          data=>{
            this.getNeodobreniOfBanka();
            //this.getAllNeodobreniByBanka();
            alert('Uspesno ste odobrili zahtev!');
          },error=>{
            this.getNeodobreniOfBanka();
            //this.getAllNeodobreniByBanka();
            alert(error.error);
          });
      }else if(paramZaBackEnd == 0){
        //ako je 0 onda je id = racunId 
        var klijent = new Klijent(obj.klijent.id,null,null,null,null,null,null,null,null,null,null);
        this._kls.update(paramZaBackEnd,obj.id,klijent).subscribe(
          data=>{
            //this.getNeodobreniOfBanka();
            this.getAllNeodobreniByBanka();
            alert('Uspesno ste odobrili zahtev!');
          },error=>{
            //this.getNeodobreniOfBanka();
            this.getAllNeodobreniByBanka();
            alert(error.error);
          });
      }
      
    }else if(param === 'rejected'){
      //delete
      if(paramZaBackEnd == 1){
        
        // ako je paramZaBackEnd=1 onda se salje 0 za racunId jer je nepotrebno
        this._kls.delete(obj.id,0,paramZaBackEnd).subscribe(
          data=>{
            this.getNeodobreniOfBanka();
            //this.getAllNeodobreniByBanka();
            alert('Uspesno ste odbili zahtev!');
          },error=>{
            this.getNeodobreniOfBanka();
            //this.getAllNeodobreniByBanka();
            alert(error.error);
          });
      }else if(paramZaBackEnd == 0){
        console.log(obj.id);
        this._kls.delete(obj.klijent.id,obj.id,paramZaBackEnd).subscribe(
          data=>{
            //this.getNeodobreniOfBanka();
            this.getAllNeodobreniByBanka();
            alert('Uspesno ste odbili zahtev!');
          },error=>{
            //this.getNeodobreniOfBanka();
            this.getAllNeodobreniByBanka();
            alert(error.error);
          });
      }
      
    }
  }


}
