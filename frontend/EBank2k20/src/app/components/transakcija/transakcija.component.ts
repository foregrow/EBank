import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Nalog } from 'src/app/model/nalog';
import { Racun } from 'src/app/model/racun';
import { DrzavaService } from 'src/app/services/drzava.service';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { NalogService } from 'src/app/services/nalog.service';
import { ValutaService } from 'src/app/services/valuta.service';

@Component({
  selector: 'app-transakcija',
  templateUrl: './transakcija.component.html',
  styleUrls: ['./transakcija.component.css']
})
export class TransakcijaComponent implements OnInit {

  addEditForm;
  drzave = [];
  valute = [];
  klijent;
  racuniDuznika = [];
  selectedRacunDuznika = false;
  selectedDrzava = false;
  selectedValuta = false;
  selectedHitno = false;
  constructor(private _fb: FormBuilder,private _ks: KorisnikService,
    private _vs: ValutaService,private _ns: NalogService,private _ds: DrzavaService,
    private _kls: KlijentService) { }

  ngOnInit(): void {
    this.addEditForm = this._fb.group({
      racunDuznika: [null,this._fb.array(this.racuniDuznika)],
      racunPrimaoca: ['',[Validators.required,Validators.pattern("^[0-9]{18}")]],
      svrhaPlacanja: ['',Validators.required],
      pozivNaBrojZaduzenja: ['',[Validators.required,Validators.pattern("^[0-9]\*")]],
      modelZaduzenja: ['',[Validators.required,Validators.pattern("^[0-9]\*")]],
      pozivNaBrojOdobrenja: ['',[Validators.required,Validators.pattern("^[0-9]\*")]],
      modelOdobrenja: ['',[Validators.required,Validators.pattern("^[0-9]\*")]],
      drzava: [[null,this._fb.array(this.drzave)]],
      valuta: [[null,this._fb.array(this.valute)]],
      iznos: ['',[Validators.required,Validators.pattern("^[0-9]\*")]],
      hitno: [false]
    });
    this.getKlijentByKorIme();
    this.getDrzave();
    this.getValute();
    this.racunDuznika.valueChanges.subscribe(
      data=>this.selectedRacunDuznika = true
    );
    this.valuta.valueChanges.subscribe(
      data=>this.selectedValuta = true
    );
    this.drzava.valueChanges.subscribe(
      data=>this.selectedDrzava = true
    );
  }

  
  getKlijentByKorIme(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data =>{
        this.klijent = data;
        this.racuniDuznika = this.klijent.racuni;
      }
    );
  }
  getDrzave(){
    this._ds.getAll().subscribe(
      data =>{
        this.drzave = data;
      }
    );
  }
  getValute(){
    this._vs.getAll().subscribe(
      data =>{
        this.valute = data;
      }
    );
  }

  submitTransakcija(){
    let racunDuznika = this.racunDuznika.value;
    let racunPrimaoca = this.racunPrimaoca.value;
    let svrhaPlacanja = this.svrhaPlacanja.value;
    let pozivNaBrojZaduzenja = this.pozivNaBrojZaduzenja.value;
    let modelZaduzenja = this.modelZaduzenja.value;
    let pozivNaBrojOdobrenja = this.pozivNaBrojOdobrenja.value;
    let modelOdobrenja = this.modelOdobrenja.value;
    let drzava = this.drzava.value;
    let valuta = this.valuta.value;
    let iznos = this.iznos.value;
    let hitno = this.hitno.value;
    if(racunDuznika.stanje - iznos < 0){
      alert('Nemate dovoljno novca na racunu!');
      return;
    }
    let primaocRacun = new Racun(null,racunPrimaoca,null,null,null,null,null,null,null,null,null);
    var nalog = new Nalog(null,null,svrhaPlacanja,null,null,null,modelZaduzenja,pozivNaBrojZaduzenja,hitno,iznos,null,null,racunDuznika,primaocRacun,
      null,drzava,null,valuta,pozivNaBrojOdobrenja,modelOdobrenja);
    this._ns.addNalog(nalog).subscribe(
      data=>{
        this.getKlijentByKorIme();
        this.getDrzave();
        this.getValute();
        this.addEditForm.reset();
        alert("Uspesno poslat novac!");
      },error=>{
        this.getKlijentByKorIme();
        this.getDrzave();
        this.getValute();
        alert(error.error);
      }
    );
  }

  get racunDuznika() {
    return this.addEditForm.get('racunDuznika') as FormArray;
  }

  get racunPrimaoca() {
    return this.addEditForm.get('racunPrimaoca');
  }

  get svrhaPlacanja() {
    return this.addEditForm.get('svrhaPlacanja');
  }

  get pozivNaBrojZaduzenja() {
    return this.addEditForm.get('pozivNaBrojZaduzenja');
  }

  get modelZaduzenja() {
    return this.addEditForm.get('modelZaduzenja');
  }

  get pozivNaBrojOdobrenja() {
    return this.addEditForm.get('pozivNaBrojOdobrenja');
  }
  get modelOdobrenja() {
    return this.addEditForm.get('modelOdobrenja');
  }
  get drzava() {
    return this.addEditForm.get('drzava') as FormArray;
  }
  get valuta() {
    return this.addEditForm.get('valuta') as FormArray;
  }
  get hitno() {
    return this.addEditForm.get('hitno');
  }
  get iznos() {
    return this.addEditForm.get('iznos');
  }
}
