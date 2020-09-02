import { Component, OnInit } from '@angular/core';
import { BankaService } from 'src/app/services/banka.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent implements OnInit {

  banke;
  constructor(
    private _bs: BankaService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }

  ngOnInit(): void {
  this.fetchData();
  }

  fetchData() {
    this._bs.getAll()
        .subscribe(data => this.banke = data);
  }

  navigateToAdd(param){
    this._router.navigate(['banke-detail/'+param]);
  }
  details(banka){
    this._router.navigate(['banke-detail/'+banka.id]);
  }
  delete(id){
    this._bs.delete(id)
      .subscribe(
        data => this.banke = data
      ); 
  }
  
    
}
