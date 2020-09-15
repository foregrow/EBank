import { Racun } from './racun';
import { Drzava } from './drzava';
import { DnevnoStanje } from './dnevnostanje';
import { Valuta } from './valuta';

export class Nalog{
    /*
    private long id;
	private String primaoc;
	private String svrhaPlacanja;
	private String duznik;
	private Date datumPrijema;
	private Date datumValute;
	private String modelZaduzenja;
	private String pozivNaBrojZaduzenja;
	private String modelOdobrenja;
	private String pozivNaBrojOdobrenja;
	private boolean hitno;
	private double iznos;
	private boolean status;
	private String tipGreske;
	private RacunDTO racunDuznika;
	private RacunDTO racunPrimaoca;
	private String vrstaPlacanja;
	
	private DrzavaDTO drzava;
	private DnevnoStanjeDTO dnevnoStanje;
	private ValutaDTO valuta;
    */

   id: number;
   primaoc: string;
   svrhaPlacanja: string;
   duznik: string;
   datumPrijema: any;
   datumValute: any;
   modelZaduzenja: string;
   pozivNaBrojZaduzenja: string;
   modelOdobrenja: string;
   pozivNaBrojOdobrenja: string;
   hitno: boolean;
   iznos: number;
   status: boolean;
   tipGreske: string;
   racunDuznika: Racun;
   racunPrimaoca: Racun;
   vrstaPlacanja: string;

   drzava: Drzava;
   dnevnoStanje: DnevnoStanje;
   valuta: Valuta;

   constructor(id:number,primaoc:string,svrhaPlacanja:string,duznik:string,
    datumPrijema:any,datumValute:any,modelZaduzenja:string,pozivNaBrojZaduzenja:string,
    hitno:boolean,iznos: number,status: boolean,tipGreske: string,racunDuznika: Racun,racunPrimaoca: Racun,vrstaPlacanja: string,
    drzava: Drzava,dnevnoStanje: DnevnoStanje,valuta: Valuta,pozivNaBrojOdobrenja:string,modelOdobrenja:string){
    this.id = id;
    this.primaoc = primaoc;
    this.svrhaPlacanja = svrhaPlacanja;
    this.duznik = duznik;
    this.datumPrijema = datumPrijema;
    this.datumValute = datumValute;
    this.modelZaduzenja = modelZaduzenja;
    this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
    this.hitno = hitno;
    this.iznos = iznos;
    this.status = status;
    this.tipGreske = tipGreske;
    this.racunDuznika = racunDuznika;
    this.racunPrimaoca = racunPrimaoca;
    this.vrstaPlacanja = vrstaPlacanja;
    this.drzava = drzava;
    this.dnevnoStanje = dnevnoStanje;
    this.valuta = valuta;
    this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
    this.modelOdobrenja = modelOdobrenja;
    }
}