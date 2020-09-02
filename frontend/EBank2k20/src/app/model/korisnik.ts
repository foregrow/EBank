import { Klijent } from './klijent';
import { Banka } from './banka';

export class Korisnik {
    id: number;
    korisnickoIme: string;
    lozinka: string;
    uloga: string;
    klijent: Klijent;
    banka: Banka;

   constructor(id:number,korisnickoIme:string,lozinka:string,
    uloga:string, klijent: Klijent,banka: Banka){
    this.id = id;
    this.korisnickoIme = korisnickoIme;
    this.lozinka = lozinka;
    this.uloga = uloga;
    this.klijent = klijent;
    this.banka = banka;
    }
}