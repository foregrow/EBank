import { Racun } from './racun';

export class UkidanjeRacuna{
 
    /*
    private long id;
	private String obrazlozenje;
	private Date datumKreiranja;
	private boolean zavrseno;
	
	private RacunDTO racunZaUkidanje;
	private RacunDTO racunZaPrenosNovca;
    */

   id: number;
   obrazlozenje: string;
   datumKreiranja: any;
   zavrseno: boolean;

   racunZaUkidanje: Racun;
   racunZaPrenosNovca: Racun;

   constructor(id:number,obrazlozenje:string,datumKreiranja:any,
    zavrseno:boolean,racunZaUkidanje:Racun,racunZaPrenosNovca:Racun){
    this.id = id;
    this.obrazlozenje = obrazlozenje;
    this.datumKreiranja = datumKreiranja;
    this.zavrseno = zavrseno;
    this.racunZaUkidanje = racunZaUkidanje;
    this.racunZaPrenosNovca = racunZaPrenosNovca;
    }
}