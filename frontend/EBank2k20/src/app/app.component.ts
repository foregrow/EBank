import { Component } from '@angular/core';
import { KorisnikService } from './services/korisnik.service';
import { RouterLink, ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(public korisnikService: KorisnikService,
    public route: ActivatedRoute,
    public router: Router){}

  klijentOtvaranjeRacuna(){
    this.router.navigate(['zahtev-otvaranja-racuna-klijenta']);
  }
  klijentUkidanjeRacuna(){
    this.router.navigate(['zahtev-ukidanja-racuna']);
  }
  izvrsilacUkidanjeRacuna(){
    this.router.navigate(['proces-zahteva-ukidanja']);
  }
  izvrsilacOtvaranjeRacuna(){
    this.router.navigate(['proces-zahteva-otvaranja']);
  }

  
}
