import { Component, OnInit } from '@angular/core';
import { DelatnostService } from 'src/app/services/delatnost.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-delatnost',
  templateUrl: './delatnost.component.html',
  styleUrls: ['./delatnost.component.css']
})
export class DelatnostComponent implements OnInit {

  delatnosti;
  constructor(
    private _ds: DelatnostService,
    private _activatedRoute: ActivatedRoute, 
    private _router: Router) { }

  ngOnInit(): void {
  this.fetchDelatnosti();
  }

  fetchDelatnosti() {
    this._ds.getAll()
        .subscribe(data=>{
          this.delatnosti = data;
        });
  }

  navigateToAdd(param){
    this._router.navigate(['delatnosti-detail/'+param]);
  }
  details(delatnost){
    this._router.navigate(['delatnosti-detail/'+delatnost.id]);
  }
  delete(id){
    console.log(id);
    this._ds.delete(id)
      .subscribe(
        data => this.delatnosti = data
      ); 
  }

}
