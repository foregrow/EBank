import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { BankaService } from 'src/app/services/banka.service';
import { HttpClient } from '@angular/common/http';
import { Banka } from 'src/app/model/banka';

@Component({
  selector: 'app-banka-detail',
  templateUrl: './banka-detail.component.html',
  styleUrls: ['./banka-detail.component.css']
})
export class BankaDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  sifreBanaka: string[] = [];
  swiftBanaka: string[] = [];
  sifraExists = false;
  swiftExists = false;
  banka;
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _bs:BankaService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      naziv: ['',Validators.required],
      email: ['',[Validators.required,Validators.email]],
      adresa: ['',Validators.required],
      fax: ['',Validators.required],
      telefon: ['',Validators.required],
      web: ['',Validators.required],
      swift: ['',[Validators.required,Validators.pattern("^[A-Z]{6}[0-9]{2}")]],
      sifra: ['',[Validators.required,Validators.pattern("^[A-Z]{4}")]],
    });
    this.getAllSifreAndSwift();
    this.sifra.valueChanges.subscribe(data =>
      {
        this.existingSifra();
      });
    this.swift.valueChanges.subscribe(data =>
      {
        this.existingSwift();
      });
    if(this.addEditParam !== 'add'){
      //znaci da je edit..
      this.addEditForm.controls['swift'].disable();
      this.addEditForm.controls['sifra'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }
  }

  getByIdAndSetValues(id){
    this._bs.getById(id).subscribe(
       data => {
         this.banka = data;
 
         this.addEditForm.patchValue({
          naziv: this.banka.naziv,
          email: this.banka.email,
          adresa: this.banka.adresa,
          fax: this.banka.fax,
          telefon: this.banka.telefon,
          web: this.banka.web,
          swift: this.banka.swift,
          sifra: this.banka.sifra
        });

       });
       
   }


  getAllSifreAndSwift(){
    this._bs.getAll()
      .subscribe(
        data =>{
          var banke = data;
          banke.forEach(item => {
            this.sifreBanaka.push(item.sifra);
          });
          banke.forEach(item => {
            this.swiftBanaka.push(item.swift);
          });
        }
      )
  }
  existingSifra(){
    if(this.sifreBanaka.includes(this.sifra.value))
      this.sifraExists = true;
    else
      this.sifraExists = false;
  }
  existingSwift(){
    if(this.swiftBanaka.includes(this.swift.value))
      this.swiftExists = true;
    else
      this.swiftExists = false;
  }
  submitBanku(param){
    var naziv = this.naziv.value;
    var email = this.email.value;
    var adresa = this.adresa.value;
    var fax = this.fax.value;
    var telefon = this.telefon.value;
    var web = this.web.value;
    var swift = this.swift.value;
    var sifra = this.sifra.value;

    if(param === 'add'){
      var banka: Banka = new Banka(null,sifra,naziv,adresa,email,web,telefon,fax,swift,null,null,null,null);
      this._bs.add(banka).subscribe(
        response => {
          this._router.navigate(['banke']);
        },
        error =>{ alert('Greska prilikom dodavanja banke!');
      });
    }else if(param === 'edit'){
      var banka: Banka = new Banka(this.banka.id,sifra,naziv,adresa,email,web,telefon,fax,swift,null,null,null,null);

      this._bs.update(banka).subscribe(
        response =>{
          this.getByIdAndSetValues(response.id);
          alert('Uspesna izmena!');

        },error=>{
          alert('Greska!');
        }
      );
    }
  }

  get naziv() {
    return this.addEditForm.get('naziv');
  }
  get email() {
    return this.addEditForm.get('email');
  }
  get adresa() {
    return this.addEditForm.get('adresa');
  }
  get fax() {
    return this.addEditForm.get('fax');
  }
  get telefon() {
    return this.addEditForm.get('telefon');
  }
  get web() {
    return this.addEditForm.get('web');
  }
  get swift() {
    return this.addEditForm.get('swift');
  }
  get sifra() {
    return this.addEditForm.get('sifra');
  }
}
