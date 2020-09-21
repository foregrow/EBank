import { UkidanjeRacuna } from './ukidanjeracuna';
import { Banka } from './banka';
import { Klijent } from './klijent';
import { Valuta } from './valuta';
import { DnevnoStanje } from './dnevnostanje';

export class Racun{
    /*
    private long id;
	private String brojRacuna;
	private double stanje;
	private Date datumKreiranja;
	private boolean izbrisan;
	
	private UkidanjeRacunaDTO ukidanjeRacuna;
	
	private BankaDTO banka;
	private KlijentDTO klijent;
	private ValutaDTO valuta;
	
	private List<DnevnoStanjeDTO> dnevnoStanje = new ArrayList<DnevnoStanjeDTO>();
    */

   id: number;
   brojRacuna: string;
   stanje: number;
   datumKreiranja: any;
   izbrisan: boolean;
   odobren: boolean;
   ukidanjeRacuna: UkidanjeRacuna;
   banka: Banka;
   klijent: Klijent;
   valuta: Valuta;
   dnevnoStanje: DnevnoStanje[] = [];
   rezervisanIznos: number;

   constructor(id:number,brojRacuna:string,stanje:number,datumKreiranja:any,
    izbrisan:boolean,ukidanjeRacuna:UkidanjeRacuna,banka:Banka,klijent:Klijent,
    valuta:Valuta,dnevnoStanje:DnevnoStanje[], odobren: boolean,rezervisanIznos: number){
    this.id = id;
    this.brojRacuna = brojRacuna;
    this.stanje = stanje;
    this.datumKreiranja = datumKreiranja;
    this.izbrisan = izbrisan;
    this.ukidanjeRacuna = ukidanjeRacuna;
    this.banka = banka;
    this.klijent = klijent;
    this.valuta = valuta;
    this.dnevnoStanje = dnevnoStanje;
    this.odobren = odobren;
    this.rezervisanIznos = rezervisanIznos;
    }
}