import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Router } from '@angular/router';
import { KlijentService } from 'src/app/services/klijent.service';
import { ValutaService } from 'src/app/services/valuta.service';
import { BankaService } from 'src/app/services/banka.service';
import { Klijent } from 'src/app/model/klijent';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { RacunService } from 'src/app/services/racun.service';

@Component({
  selector: 'app-otvaranje-racuna-ulogovanog',
  templateUrl: './otvaranje-racuna-ulogovanog.component.html',
  styleUrls: ['./otvaranje-racuna-ulogovanog.component.css']
})
export class OtvaranjeRacunaUlogovanogComponent implements OnInit {


  addEditForm: FormGroup;
  banke = [];
  valute = [];
  bankaSelected = false;
  valutaSelected = false;
  klijent;
  racuni = [];
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _kls:KlijentService,
    private _bs:BankaService,
    private _vs:ValutaService,
    private _ks:KorisnikService,
    private _rs:RacunService
  ) {}

  ngOnInit(): void {
    this.addEditForm = this._fb.group({
      banka: [[null,this._fb.array(this.banke)]],
      valuta: [[null,this._fb.array(this.valute)]],
    });
    this.getByKorIme();
    this.getAllRacunAktivacijaUToku(this._ks.getLoggedInUserKorIme());
    this.getAllBanke();
    this.getAllValute();

    this.banka.valueChanges.subscribe(data =>
      {
      this.bankaSelected = true;
      });
      this.valuta.valueChanges.subscribe(data =>
      {
      this.valutaSelected = true;
      });
  }

  getAllRacunAktivacijaUToku(korIme){
    this._rs.getAllRacunAktivacijaUToku(korIme).subscribe(
      data=>{
        this.racuni = data
      }
    );
  }

  getAllValute(){
    this._vs.getAll().subscribe(
      data=>{this.valute = data}
    );
  }

  getAllBanke(){
    this._bs.getAll().subscribe(
      data=>{this.banke = data}
    );
  }

  getByKorIme(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.klijent = data;
      }
    );
  }

  submitZahtev(param){

    let banka = this.banka.value;
    let valuta = this.valuta.value;

    let klijent = new Klijent(this.klijent.id,null,null,null,null,null,null,null,null,null,null);

    this._kls.add(klijent,banka.id,valuta.id,param).subscribe(
      data=>{
        this.getByKorIme();
        this.getAllRacunAktivacijaUToku(this._ks.getLoggedInUserKorIme());
        this.getAllBanke();
        this.getAllValute();
        alert("Uspesno poslat zahtev!");
      },error=>{
        alert("Greska!");
      });
          
  }

  get banka() {
    return this.addEditForm.get('banka') as FormArray;
  }
  get valuta() {
    return this.addEditForm.get('valuta') as FormArray;
  }
}
