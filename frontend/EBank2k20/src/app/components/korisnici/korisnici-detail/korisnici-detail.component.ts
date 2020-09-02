import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-korisnici-detail',
  templateUrl: './korisnici-detail.component.html',
  styleUrls: ['./korisnici-detail.component.css']
})
export class KorisniciDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  korisnik;
  korImena: string[] = [];
  korImeExists = false;
  uloge: string[] = ['ADMIN','KORISNIK','IZVRSILAC'];
  banke= [];
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _ks:KorisnikService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      korisnickoIme: ['',Validators.required],
      lozinka: ['',Validators.required],
      uloga: [this._fb.array(this.uloge)],
      banka: [[null,this._fb.array(this.banke)]],
      bankaEdit: ['']
    });
    
    if(this.addEditParam !== 'add'){
      
    }
  }

}
