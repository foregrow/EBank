import { Racun } from './racun';
import { KursnaLista } from './kursnalista';
import { Korisnik } from './korisnik';

export class Banka{
    /*
    private long id;
	private String sifra;
	private String naziv;
	private String adresa;
	private String email;
	private String web;
	private String telefon;
	private String fax;
	private String swift;
	private String obracunskiRacun;
	
	private List<RacunDTO> racuni = new ArrayList<RacunDTO>();
	private List<KursnaListaDTO> kursneListe = new ArrayList<KursnaListaDTO>();
    */
   id: number;
   sifra: string;
   naziv: string;
   adresa: string;
   email: string;
   web: string;
   telefon: string;
   fax: string;
   swift: string;
   obracunskiRacun: string;

   racuni: Racun[] = [];
   kursneListe: KursnaLista[] = [];
   izvrsioci: Korisnik[] = [];
   constructor(id:number,sifra:string,naziv:string,
    adresa:string, email:string, web:string, telefon: string, fax: string,
    swift: string,obracunskiRacun: string,racuni: Racun[],kursneListe: KursnaLista[],
    izvrsioci: Korisnik[]){
    this.id = id;
    this.sifra = sifra;
    this.naziv = naziv;
    this.adresa = adresa;
    this.email = email;
    this.web = web;
    this.telefon = telefon;
    this.fax = fax;
    this.swift = swift;
    this.obracunskiRacun = obracunskiRacun;
    this.racuni = racuni;
    this.kursneListe = kursneListe;
    this.izvrsioci = izvrsioci;
    }
}