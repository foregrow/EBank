import { Korisnik } from './korisnik';

export class Klijent{

   id: number;
   ime: string;
   prezime: string;
   jmbg: string;
   telefon: string;
   adresa: string;
   tipKlijenta: string;

   korisnik: Korisnik;
   //delatnost: Delatnost;
   //racuni: Racun[] = [];

   constructor(id:number,ime:string,prezime:string,
    jmbg:string, telefon:string, adresa:string, tipKlijenta: string, korisnik: Korisnik){
    this.id = id;
    this.ime = ime;
    this.prezime = prezime;
    this.jmbg = jmbg;
    this.telefon = telefon;
    this.adresa = adresa;
    this.tipKlijenta = tipKlijenta;
    this.korisnik = korisnik;
    }
}