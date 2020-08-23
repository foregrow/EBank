import { Klijent } from './klijent';

export class Delatnost{
    id: number;
    sifra: string;
    naziv: string;

    klijenti: Klijent[] = [];

    constructor(id:number,sifra:string,naziv:string,
    klijenti: Klijent[]){
    this.id = id;
    this.sifra = sifra;
    this.naziv = naziv;
    this.klijenti = klijenti;
    }
}