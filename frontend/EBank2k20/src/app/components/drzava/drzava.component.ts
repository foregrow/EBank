import { Component, OnInit } from '@angular/core';
import { DrzavaService } from 'src/app/services/drzava.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-drzava',
  templateUrl: './drzava.component.html',
  styleUrls: ['./drzava.component.css']
})
export class DrzavaComponent implements OnInit {
  drzave;
  constructor(
    private _ds: DrzavaService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }

  ngOnInit(): void {
  this.fetchData();
  }

  fetchData() {
    this._ds.getAll()
        .subscribe(data => this.drzave = data);
  }

  delete(id){
    this._ds.delete(id)
      .subscribe(
        data => this.drzave = data
      ); 
  }

  details(drzava){
    this._router.navigate(['drzave-detail/'+drzava.id]);
  }

  navigateToAdd(param){
    this._router.navigate(['drzave-detail/'+param]);
  }

}
