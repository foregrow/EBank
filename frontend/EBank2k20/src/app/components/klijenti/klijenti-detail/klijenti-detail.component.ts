import { HttpClient } from '@angular/common/http';
import { KlijentService } from 'src/app/services/klijent.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-klijenti-detail',
  templateUrl: './klijenti-detail.component.html',
  styleUrls: ['./klijenti-detail.component.css']
})
export class KlijentiDetailComponent implements OnInit {

  addEditParam;
  addEditForm: FormGroup;
  klijent;
  constructor(private _fb: FormBuilder,
    private _router: Router,
    private _ks:KlijentService,
    private _route: ActivatedRoute, 
    private _http: HttpClient) { }

  ngOnInit(): void {
    this.addEditParam = this._route.snapshot.paramMap.get('id');
    if(isNaN(this.addEditParam) && this.addEditParam !== 'add'){
      this._router.navigate(['not-found']);
    }
    this.addEditForm = this._fb.group({
      ime: ['',Validators.required],
      prezime: ['',[Validators.required]],
      jmbg: ['',[Validators.required]],
      korisnickoIme: ['',[Validators.required]],
      tipKlijenta: ['',[Validators.required]],
      delatnost: ['',[Validators.required]],

    });
      this.addEditForm.controls['ime'].disable();
      this.addEditForm.controls['prezime'].disable();
      this.addEditForm.controls['jmbg'].disable();
      this.addEditForm.controls['korisnickoIme'].disable();
      this.addEditForm.controls['tipKlijenta'].disable();
      this.addEditForm.controls['delatnost'].disable();
      this.getByIdAndSetValues(this.addEditParam);
    }

    getByIdAndSetValues(id){
      this._ks.getById(id).subscribe(
         data => {
           this.klijent = data;
   
           this.addEditForm.patchValue({
            ime: this.klijent.ime,
            prezime: this.klijent.prezime,
            jmbg: this.klijent.jmbg,
            korisnickoIme: this.klijent.korisnik.korisnickoIme,
            tipKlijenta: this.klijent.tipKlijenta,
            delatnost: this.klijent.delatnost.naziv
          });
         });     
     }


  }


