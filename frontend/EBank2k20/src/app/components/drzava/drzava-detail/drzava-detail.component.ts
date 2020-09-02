import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { DrzavaService } from 'src/app/services/drzava.service';
import { HttpClient } from '@angular/common/http';
import { Drzava } from 'src/app/model/drzava';

@Component({
  selector: 'app-drzava-detail',
  templateUrl: './drzava-detail.component.html',
  styleUrls: ['./drzava-detail.component.css']
})
export class DrzavaDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  drzava;
  sifreDrzava: string[] = [];
  sifraExists = false;
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _ds:DrzavaService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      naziv: ['',Validators.required],
      sifra: ['',[Validators.required,Validators.pattern("^[A-Z]{2}")]],
    });
    this.getAllSifre();
    this.sifra.valueChanges.subscribe(data =>
      {
        this.existingSifra();
      });
    if(this.addEditParam !== 'add'){
      //znaci da je edit..
      this.addEditForm.controls['sifra'].disable();
      this.addEditForm.controls['naziv'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }
  }


  getByIdAndSetValues(id){
    this._ds.getById(id).subscribe(
       data => {
         this.drzava = data;
        console.log(this.drzava);
         this.addEditForm.patchValue({
          naziv: this.drzava.naziv,
          sifra: this.drzava.sifra
        });
       });     
   }

   getAllSifre(){
    this._ds.getAll()
      .subscribe(
        data =>{
          var drzave = data;
          drzave.forEach(item => {
            this.sifreDrzava.push(item.sifra);
          });
        });
  }
  existingSifra(){
    if(this.sifreDrzava.includes(this.sifra.value))
      this.sifraExists = true;
    else
      this.sifraExists = false;
  }

  submitDrzava(param){
    if(param === 'add'){
      var naziv = this.naziv.value;
      var sifra = this.sifra.value;
      var drzava = new Drzava(null,sifra,naziv,null,null);
      this._ds.add(drzava).subscribe(
        response => {
          this._router.navigate(['drzave']);
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
}
