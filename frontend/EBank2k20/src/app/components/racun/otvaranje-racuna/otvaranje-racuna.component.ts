import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BankaService } from 'src/app/services/banka.service';
import { DelatnostService } from 'src/app/services/delatnost.service';
import { ValutaService } from 'src/app/services/valuta.service';
import { Klijent } from 'src/app/model/klijent';

@Component({
  selector: 'app-otvaranje-racuna',
  templateUrl: './otvaranje-racuna.component.html',
  styleUrls: ['./otvaranje-racuna.component.css']
})
export class OtvaranjeRacunaComponent implements OnInit {

  addEditForm: FormGroup;
  jmbgovi: string[] = [];
  jmbgExists = false;
  tipoviKlijenta = ['FizickoLice','PravnoLice'];
  banke = [];
  delatnosti = [];
  valute = [];
  bankaSelected = false;
  valutaSelected = false;
  tipSelected = false;
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _ks:KlijentService,
    private _bs:BankaService,
    private _ds:DelatnostService,
    private _vs:ValutaService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {

    this.addEditForm = this._fb.group({
      ime: ['',Validators.required],
      prezime: ['',Validators.required],
      adresa: ['',Validators.required],
      jmbg: ['',[Validators.required,Validators.pattern("^[0-9]{13}")]],
      telefon: ['',[Validators.required,Validators.pattern("^[0-9]{10}")]],
      tipKlijenta: [[null,this._fb.array(this.tipoviKlijenta)]],
      banka: [[null,this._fb.array(this.banke)]],
      delatnost: [[null,this._fb.array(this.delatnosti)]],
      valuta: [[null,this._fb.array(this.valute)]],
    });
    this.getAllJmbgs();
    this.getAllBanke();
    this.getAllDelatnosti();
    this.getAllValute();
    this.jmbg.valueChanges.subscribe(data =>
    {
      this.existingJmbg();
    });
    this.banka.valueChanges.subscribe(data =>
    {
    this.bankaSelected = true;
    });
    this.valuta.valueChanges.subscribe(data =>
    {
    this.valutaSelected = true;
    });
    this.tipKlijenta.valueChanges.subscribe(data =>
    {
    this.tipSelected = true;
    });


  }

  getAllBanke(){
    this._bs.getAll().subscribe(
      data=>{this.banke = data}
    );
  }
  getAllDelatnosti(){
    this._ds.getAll().subscribe(
      data=>{this.delatnosti = data}
    );
  }
  getAllValute(){
    this._vs.getAll().subscribe(
      data=>{this.valute = data}
    );
  }
  getAllJmbgs(){
    this._ks.getAll().subscribe(
      data=>{
        var klijenti = data;
        klijenti.forEach(k=> 
          this.jmbgovi.push(k.jmbg));
      }
    );
  }

  existingJmbg(){
    if(this.jmbgovi.includes(this.jmbg.value))
      this.jmbgExists = true;
    else
      this.jmbgExists = false;
  }

  submitZahtev(){
    let ime = this.ime.value;
    let prezime = this.prezime.value;
    let jmbg = this.jmbg.value;
    let adresa = this.adresa.value;
    let telefon = this.telefon.value;
    let banka = this.banka.value;
    let valuta = this.valuta.value;
    let tipKlijenta = this.tipKlijenta.value;

    let klijent;
    if(this.delatnosti.includes(this.delatnost.value)){
      let delatnost = this.delatnost.value;
      klijent = new Klijent(null,ime,prezime,jmbg,telefon,adresa,tipKlijenta,null,delatnost,null,null);
    }else{
      klijent = new Klijent(null,ime,prezime,jmbg,telefon,adresa,tipKlijenta,null,null,null,null);
    }
    console.log(this.delatnost);
    
    this._ks.add(klijent,banka.id,valuta.id).subscribe(
      data=>{
        this._router.navigate(['login']);
        alert("Uspesno poslat zahtev!");
      },error=>{
        alert("Greska!");
      });
          
  }

  get ime() {
    return this.addEditForm.get('ime');
  }
  get prezime() {
    return this.addEditForm.get('prezime');
  }
  get adresa() {
    return this.addEditForm.get('adresa');
  }
  get jmbg() {
    return this.addEditForm.get('jmbg');
  }
  get telefon() {
    return this.addEditForm.get('telefon');
  }
  get delatnost() {
    return this.addEditForm.get('delatnost') as FormArray;
  }
  get banka() {
    return this.addEditForm.get('banka') as FormArray;
  }
  get valuta() {
    return this.addEditForm.get('valuta') as FormArray;
  }
  get tipKlijenta() {
    return this.addEditForm.get('tipKlijenta') as FormArray;
  }

}
