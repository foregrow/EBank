import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Klijent } from 'src/app/model/klijent';

@Component({
  selector: 'app-otvaranje-racuna-izvrsilac',
  templateUrl: './otvaranje-racuna-izvrsilac.component.html',
  styleUrls: ['./otvaranje-racuna-izvrsilac.component.css']
})
export class OtvaranjeRacunaIzvrsilacComponent implements OnInit {

  klijenti;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService
  ) { }

  ngOnInit(): void {
    this.getNeodobreniOfBanka();
  }

  getNeodobreniOfBanka(){
    this._kls.getOdobreniOrNeodobreniOfBanka(this._ks.getLoggedInUserKorIme(),0).subscribe(
      data => {
        this.klijenti = data;
      });
  }

  submit(param,id){
    if(param === 'accepted'){
      //put
      var klijent = new Klijent(id,null,null,null,null,null,null,null,null,null,null);
      this._kls.update(klijent).subscribe(
        data=>{
          this.getNeodobreniOfBanka();
          alert('Uspesno ste odobrili zahtev!');
        },error=>{
          this.getNeodobreniOfBanka();
          alert(error.error);
        });
    }else if(param === 'rejected'){
      //delete
      this._kls.delete(id).subscribe(
        data=>{
          this.getNeodobreniOfBanka();
          alert('Uspesno ste odbili zahtev!');
        },error=>{
          this.getNeodobreniOfBanka();
          alert(error.error);
        });
    }
  }

}
