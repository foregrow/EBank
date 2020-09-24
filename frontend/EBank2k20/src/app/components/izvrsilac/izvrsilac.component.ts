import { Component, OnInit } from '@angular/core';
import { KlijentService } from 'src/app/services/klijent.service';
import { KorisnikService } from 'src/app/services/korisnik.service';
import { Router } from '@angular/router';
import { RacunService } from 'src/app/services/racun.service';
import { Nalog } from 'src/app/model/nalog';
import { NalogService } from 'src/app/services/nalog.service';

@Component({
  selector: 'app-izvrsilac',
  templateUrl: './izvrsilac.component.html',
  styleUrls: ['./izvrsilac.component.css']
})
export class IzvrsilacComponent implements OnInit {

  racuni;
  klijenti;
  nalozi;
  constructor(
    private _router: Router,
    private _kls: KlijentService,
    private _ks: KorisnikService,
    private _rs: RacunService,
    private _ns: NalogService
  ) { }

  ngOnInit(): void {
    this.getAktivniOfBanka();
    this.getAllByBanka();
  }

  getAktivniOfBanka(){
    this._kls.getAktivniOfBanka(this._ks.getLoggedInUserKorIme(),1).subscribe(
      data => {
        this.klijenti = data;
      });
  }

  getAllByBanka(){
    this._rs.getAllByBanka(this._ks.getLoggedInUserKorIme()).subscribe(
      data => {
        this.racuni = data;
      });
  }

  selectedFiles: File[] = [];
  onFileChanged(e: any){
    for(var i=0;i<e.target.files.length;i++){
      var file = e.target.files[i];
      this.selectedFiles.push(file);
    }
  }
  onImport(){
    if(this.selectedFiles.length <= 0)
    alert("Niste dodali nijedan fajl! ");
  else{
    var fileData: FormData  = new FormData();
    for(var i =0;i<this.selectedFiles.length;i++){
      fileData.append('files', this.selectedFiles[i], this.selectedFiles[i].name);
    }
    this._ns.upload(fileData).subscribe(
      data=>{
        this.nalozi = data;
        alert('Uspesno !');
      });
  }
  }

}
