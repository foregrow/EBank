import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormArray, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { UkidanjeRacunaService } from 'src/app/services/ukidanje-racuna.service';
import { KlijentService } from 'src/app/services/klijent.service';
import { HttpClient } from '@angular/common/http';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { VirtualTimeScheduler } from 'rxjs';
import { Racun } from 'src/app/model/racun';
import { UkidanjeRacuna } from 'src/app/model/ukidanjeracuna';

@Component({
  selector: 'app-ukidanje-racuna',
  templateUrl: './ukidanje-racuna.component.html',
  styleUrls: ['./ukidanje-racuna.component.css']
})
export class UkidanjeRacunaComponent implements OnInit {

  klijent;
  addEditForm: FormGroup;
  aktivniRacuni = [];
  racuniZaPrenosNovca = [];
  selectedRacunZaUkidanje = false;
  selectedRacunZaPrenosNovca = false;
  ukidanjaUToku = [];
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _urs: UkidanjeRacunaService,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}

  ngOnInit(): void{
    this.addEditForm = this._fb.group({
      racunZaUkidanje: [[null,this._fb.array(this.aktivniRacuni)]],
      racunZaPrenosNovca: [[null,this._fb.array(this.racuniZaPrenosNovca)]],
      obrazlozenje: ['',Validators.required]
    });
    this.getByKorImeAndPopulateAktivniRacuni();
    this.getAllUkidanjaForKlijentUToku();
    this.racunZaUkidanje.valueChanges.subscribe(data =>
      {
        this.setRacuniZaPrenosNovca();
      });
      this.racunZaPrenosNovca.valueChanges.subscribe(data =>
        {
          this.selectedRacunZaPrenosNovca = true;
        });
    
  }

  getAllUkidanjaForKlijentUToku(){
    this._urs.getAllForKlijentUToku(this._ks.getLoggedInUserKorIme()).subscribe(
      data=>{
        this.ukidanjaUToku = data;
      }
    )
  }
  getByKorImeAndPopulateAktivniRacuni(){
    this._kls.getByKorIme(this._ks.getLoggedInUserKorIme()).subscribe(
      data => {
        this.klijent = data;
        this.aktivniRacuni = this.klijent.racuni;
      });
  }
  setRacuniZaPrenosNovca(){
    this.selectedRacunZaUkidanje = true;
    this.racuniZaPrenosNovca = [];
    this.racunZaPrenosNovca.setValue(null);
    let selectedRacun = this.aktivniRacuni.indexOf(this.racunZaUkidanje.value);
    for(var r=0;r<this.aktivniRacuni.length;r++){
      if(r !== selectedRacun)
        this.racuniZaPrenosNovca.push(this.aktivniRacuni[r]);
    }
  }


  submitZahtev(){
    let racunZaUkidanje = this.racunZaUkidanje.value;
    let racunZaPrenosNovca = this.racunZaPrenosNovca.value;
    let obrazlozenje = this.obrazlozenje.value;
    var ukidanje = new UkidanjeRacuna(null,obrazlozenje,null,false,racunZaUkidanje,racunZaPrenosNovca);
    this._urs.add(ukidanje).subscribe(
      data=>{
        this.racunZaUkidanje.setValue(null);
        this.racunZaPrenosNovca.setValue(null);
        this.obrazlozenje.setValue('');
        this.ukidanjaUToku = data;
        alert('Uspesno poslat zahtev za ukidanje racuna!');
      },error=>{
        alert(error.error);
      }
    )
  }

  get racunZaUkidanje() {
    return this.addEditForm.get('racunZaUkidanje')  as FormArray;
  }

  get racunZaPrenosNovca() {
    return this.addEditForm.get('racunZaPrenosNovca')  as FormArray;
  }

  get obrazlozenje() {
    return this.addEditForm.get('obrazlozenje');
  }

}
