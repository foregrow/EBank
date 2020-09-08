import { KlijentService } from './../../services/klijent.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-klijenti',
  templateUrl: './klijenti.component.html',
  styleUrls: ['./klijenti.component.css']
})
export class KlijentiComponent implements OnInit {

  klijenti;
  constructor( 
    private _ks: KlijentService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }


    ngOnInit(): void {
      this.fetchData();
      }
    
      fetchData() {
        this._ks.getAll()
            .subscribe(data => this.klijenti = data);
      }

      details(kli){
        this._router.navigate(['klijenti-detail/'+kli.id]);
      }

}
