import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  constructor(
    private _router: Router
  ) { }

  ngOnInit(): void {
  }

  viewBanke(){
    this._router.navigate(['banke']);
  }
  viewKorisnici(){
    this._router.navigate(['korisnici']);
  }
  viewDelatnosti(){
    this._router.navigate(['delatnosti']);
  }
  viewKlijenti(){
    this._router.navigate(['klijenti']);
  }

}
