import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ValutaService } from 'src/app/services/valuta.service';
import { HttpClient } from '@angular/common/http';
import { Drzava } from 'src/app/model/drzava';
import { DrzavaService } from 'src/app/services/drzava.service';
import { Valuta } from 'src/app/model/valuta';

@Component({
  selector: 'app-valuta-detail',
  templateUrl: './valuta-detail.component.html',
  styleUrls: ['./valuta-detail.component.css']
})
export class ValutaDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  valuta;
  sifreValuta: string[] = [];
  sifraExists = false;
  drzavaSelected = false;
  drzave: Drzava[] = [];
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _vs:ValutaService,
    private _ds: DrzavaService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      naziv: ['',Validators.required],
      sifra: ['',[Validators.required,Validators.pattern("^[A-Z]{3}")]],
      drzava: [[null,this._fb.array(this.drzave)]],
      drzavaEdit: ['']
    });
    this.getAllSifre();
    this.getAllDrzave();
    this.sifra.valueChanges.subscribe(data =>
      {
        this.existingSifra();
      });
    this.drzava.valueChanges.subscribe(data =>
      {
        this.drzavaSelected = true;
      });
    if(this.addEditParam !== 'add'){
      //znaci da je edit..
      this.addEditForm.controls['sifra'].disable();
      this.addEditForm.controls['naziv'].disable();
      this.addEditForm.controls['drzavaEdit'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }
  }


  getByIdAndSetValues(id){
    this._vs.getById(id).subscribe(
       data => {
         this.valuta = data;
        console.log(this.valuta);
         this.addEditForm.patchValue({
          naziv: this.valuta.naziv,
          sifra: this.valuta.sifra,
          drzavaEdit: `${this.valuta.drzava.naziv}, ${this.valuta.drzava.sifra}`
        });
       });     
   }
   getAllDrzave(){
    this._ds.getAll()
      .subscribe(
        data =>{
          var drzave = data;
          drzave.forEach(item => {
            this.drzave.push(item);
          });
        });
  }
   getAllSifre(){
    this._vs.getAll()
      .subscribe(
        data =>{
          var valute = data;
          valute.forEach(item => {
            this.sifreValuta.push(item.sifra);
          });
        });
  }
  existingSifra(){
    if(this.sifreValuta.includes(this.sifra.value))
      this.sifraExists = true;
    else
      this.sifraExists = false;
  }


  submitValuta(param){
    if(param === 'add'){
      var naziv = this.naziv.value;
      var sifra = this.sifra.value;
      var drzava = this.drzava.value;
      var valuta = new Valuta(null,sifra,naziv,drzava,null,null,null,null);
      this._vs.add(valuta).subscribe(
        response => {
          this._router.navigate(['valute']);
        },
        error =>{ alert('Greska prilikom dodavanja!');
      });
    }
  }

  get naziv() {
    return this.addEditForm.get('naziv');
  }
  get sifra() {
    return this.addEditForm.get('sifra');
  }
  get drzava() {
    return this.addEditForm.get('drzava')  as FormArray;
  }
  get drzavaEdit() {
    return this.addEditForm.get('drzavaEdit');
  }
}
