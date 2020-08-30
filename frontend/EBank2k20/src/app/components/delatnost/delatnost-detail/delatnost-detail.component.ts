import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Delatnost } from 'src/app/model/delatnost';
import { DelatnostService } from 'src/app/services/delatnost.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-delatnost-detail',
  templateUrl: './delatnost-detail.component.html',
  styleUrls: ['./delatnost-detail.component.css']
})
export class DelatnostDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  delatnost;
  sifreDelatnosti: string[] = [];
  sifraExists = false;
  constructor(
    private _fb: FormBuilder,
    private _router: Router,
    private _ds:DelatnostService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) {}


  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      naziv: ['',Validators.required],
      sifra: ['',[Validators.required,Validators.pattern("^[A-Z]{1}[0-9]{3}")]],
    });
    this.getAllSifre();
    this.sifra.valueChanges.subscribe(data =>
      {
        this.existingSifra();
      });
    if(this.addEditParam !== 'add'){
      //znaci da je edit..
      this.addEditForm.controls['sifra'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }
  }

  getByIdAndSetValues(id){
    this._ds.getById(id).subscribe(
       data => {
         this.delatnost = data;
 
         this.addEditForm.patchValue({
          naziv: this.delatnost.naziv,
          sifra: this.delatnost.sifra
        });
       });     
   }

   getAllSifre(){
    this._ds.getAll()
      .subscribe(
        data =>{
          var delatnosti = data;
          delatnosti.forEach(item => {
            this.sifreDelatnosti.push(item.sifra);
          });
        });
  }
  existingSifra(){
    if(this.sifreDelatnosti.includes(this.sifra.value))
      this.sifraExists = true;
    else
      this.sifraExists = false;
  }


  submitDelatnost(param){
    var naziv = this.naziv.value;
    var sifra = this.sifra.value;
    if(param === 'add'){
      var delatnost: Delatnost = new Delatnost(null,sifra,naziv,null);
      this._ds.add(delatnost).subscribe(
        response => {
          this._router.navigate(['delatnosti']);
        },
        error =>{ alert('Greska prilikom dodavanja!');
      });
    }else if(param === 'edit'){
      var delatnost: Delatnost = new Delatnost(this.delatnost.id,sifra,naziv,null);

      this._ds.update(delatnost).subscribe(
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
  get sifra() {
    return this.addEditForm.get('sifra');
  }

}
