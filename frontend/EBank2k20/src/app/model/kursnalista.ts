import { Banka } from './banka';
import { KursUValuti } from './kursuvaluti';

export class KursnaLista{
    /*
    private long id;
	private Date datum;
	private String broj;
	private Date datumPrimene;
	
	private BankaDTO banka;
	
	private List<KursUValutiDTO> kurseviUValutama = new ArrayList<KursUValutiDTO>();
    */

   id: number;
   datum: any;
   broj: string;
   datumPrimene: any;

   banka: Banka;
   kurseviUValutama: KursUValuti[] = [];

   constructor(id:number,datum:any,broj:string,datumPrimene:any,
    banka:Banka,kurseviUValutama: KursUValuti[]){
    this.id = id;
    this.datum = datum;
    this.broj = broj;
    this.datumPrimene = datumPrimene;
    this.banka = banka;
    this.kurseviUValutama = kurseviUValutama;
    }
}