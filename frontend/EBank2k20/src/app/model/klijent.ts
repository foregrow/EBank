import { Korisnik } from './korisnik';
import { Delatnost } from './delatnost';
import { Racun } from './racun';

export class Klijent{

   id: number;
   ime: string;
   prezime: string;
   jmbg: string;
   telefon: string;
   adresa: string;
   tipKlijenta: string;
   odobren: boolean;

   korisnik: Korisnik;
   delatnost: Delatnost;
   racuni: Racun[] = [];

   constructor(id:number,ime:string,prezime:string,
    jmbg:string, telefon:string, adresa:string, tipKlijenta: string, korisnik: Korisnik,
    delatnost: Delatnost, racuni: Racun[], odobren: boolean){
    this.id = id;
    this.ime = ime;
    this.prezime = prezime;
    this.jmbg = jmbg;
    this.telefon = telefon;
    this.adresa = adresa;
    this.tipKlijenta = tipKlijenta;
    this.korisnik = korisnik;
    this.delatnost = delatnost;
    this.racuni = racuni;
    this.odobren = odobren;
    }
}