import { Klijent } from './klijent';

export class Korisnik {
   id: number;
   korisnickoIme: string;
   lozinka: string;
   uloga: string;
   klijent: Klijent;

   constructor(id:number,korisnickoIme:string,lozinka:string,
    uloga:string, klijent: Klijent){
    this.id = id;
    this.korisnickoIme = korisnickoIme;
    this.lozinka = lozinka;
    this.uloga = uloga;
    this.klijent = klijent;
    }
}