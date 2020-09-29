import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { HttpClient } from '@angular/common/http';
import { BankaService } from 'src/app/services/banka.service';
import { Korisnik } from 'src/app/model/korisnik';

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
  uloge: string[] = ['ROLE_ADMIN','ROLE_IZVRSILAC'];
  banke= [];
  izvrsilacSelected = false;
  ulogaSelected = false;
  bankaSelected = false;
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _ks:KorisnikService,
    private _bs: BankaService,
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
      ulogaEdit: [''],
      banka: [[null,this._fb.array(this.banke)]],
      bankaEdit: ['']
    });
    this.korisnickoIme.valueChanges.subscribe(data =>
      {
        this.existingKorIme();
      });
    this.uloga.valueChanges.subscribe(data =>
      {
        this.chosenUloga();
      });
    this.banka.valueChanges.subscribe(data =>
      {
        this.bankaSelected = true;
      });
    this.getAllKorImena();
    this.getAllBanke();
    if(this.addEditParam !== 'add'){
      this.addEditForm.controls['korisnickoIme'].disable();
      this.addEditForm.controls['ulogaEdit'].disable();
      this.addEditForm.controls['bankaEdit'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }
  }

  getByIdAndSetValues(id){
    this._ks.getById(id).subscribe(
       data => {
         this.korisnik = data;
         this.addEditForm.patchValue({
          korisnickoIme: this.korisnik.korisnickoIme,
          ulogaEdit: this.korisnik.uloga
        });
        if(this.korisnik.uloga === 'ROLE_IZVRSILAC'){
          this.addEditForm.patchValue({
            bankaEdit: this.korisnik.banka.naziv
          });
        }
       });     
   }

  getAllKorImena(){
    this._ks.getAll().subscribe(
      data=>{
        var korisnici = data;
        korisnici.forEach(item=> 
          this.korImena.push(item.korisnickoIme));
      });
  }

  getAllBanke(){
    this._bs.getAll().subscribe(
      data=>{
        this.banke = data;
      });
  }

  existingKorIme(){
    if(this.korImena.includes(this.korisnickoIme.value))
      this.korImeExists = true;
    else
      this.korImeExists = false;
  }

  chosenUloga(){
    var cUloga = this.uloga.value;
    if(cUloga === 'ROLE_ADMIN'){
      this.ulogaSelected = true;
      this.izvrsilacSelected = false;
    }else if(cUloga === 'ROLE_IZVRSILAC'){
      this.ulogaSelected = true;
      this.izvrsilacSelected = true;
    }else{
      this.ulogaSelected = false;
      this.izvrsilacSelected = false;
    }
      
  }


  submitKorisnik(param){
    var korIme = this.korisnickoIme.value;
    var lozinka = this.lozinka.value;
    var uloga = this.uloga.value;
    if(param === 'add'){
      var banka = this.banka.value;
      let kor;
      if(uloga === 'ROLE_IZVRSILAC')
        kor = new Korisnik(null,korIme,lozinka,uloga,null,banka);
      else if(uloga === 'ROLE_ADMIN')
        kor = new Korisnik(null,korIme,lozinka,uloga,null,null);

      this._ks.add(kor).subscribe(
        data=>{
          alert('Uspesno dodavanje!');
          this._router.navigate(['korisnici']);
        },error=>{
          alert("Greska!");
        });
      
    }else if(param === 'edit'){
      var kor = new Korisnik(this.korisnik.id,null,lozinka,null,null,null);
      this._ks.update(kor).subscribe(
        data=>{
          this.getByIdAndSetValues(this.korisnik.id);
          alert('Uspesna izmena!');
          
        },error=>{
          alert("Greska!");
        });
    }
  }


  get korisnickoIme() {
    return this.addEditForm.get('korisnickoIme');
  }

  get lozinka() {
    return this.addEditForm.get('lozinka');
  }

  get uloga() {
    return this.addEditForm.get('uloga')  as FormArray;
  }

  get banka() {
    return this.addEditForm.get('banka')  as FormArray;
  }

  get bankaEdit() {
    return this.addEditForm.get('bankaEdit');
  }

  get ulogaEdit() {
    return this.addEditForm.get('ulogaEdit');
  }

}
