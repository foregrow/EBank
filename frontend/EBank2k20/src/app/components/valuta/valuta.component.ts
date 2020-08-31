import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ValutaService } from 'src/app/services/valuta.service';

@Component({
  selector: 'app-valuta',
  templateUrl: './valuta.component.html',
  styleUrls: ['./valuta.component.css']
})
export class ValutaComponent implements OnInit {

  valute;
  constructor(
    private _vs: ValutaService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }

  ngOnInit(): void {
  this.fetchBanke();
  }

  fetchBanke() {
    this._vs.getAll()
        .subscribe(data => this.valute = data);
  }

  navigateToAdd(param){
    this._router.navigate(['valute-detail/'+param]);
  }
  details(valuta){
    this._router.navigate(['valute-detail/'+valuta.id]);
  }
  delete(id){
    this._vs.delete(id)
      .subscribe(
        data => this.valute = data
      ); 
  }

}
