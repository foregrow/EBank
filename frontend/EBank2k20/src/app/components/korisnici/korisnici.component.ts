import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { KorisnikService } from 'src/app/services/korisnik.service';

@Component({
  selector: 'app-korisnici',
  templateUrl: './korisnici.component.html',
  styleUrls: ['./korisnici.component.css']
})
export class KorisniciComponent implements OnInit {

  korisnici;
  constructor(private _ks: KorisnikService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }

    ngOnInit(): void {
      this.fetchKorisnici();
      }
    
      fetchKorisnici() {
        this._ks.getAll()
            .subscribe(data => this.korisnici = data);
      }

}
