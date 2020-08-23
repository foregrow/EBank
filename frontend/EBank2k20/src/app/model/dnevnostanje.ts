import { Racun } from './racun';
import { Nalog } from './nalog';

export class DnevnoStanje{
    /*
    private long id;
	private String brojIzvoda;
	private Date datumPrometa;
	private double prethodnoStanje;
	private double prometUKorist;
	private double prometNaTeret;
	private double novoStanje;
	
	private RacunDTO racun;
	//nalog je u stvari transakcija
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
    */

   id: number;
   brojIzvoda: string;
   datumPrometa: any;
   prethodnoStanje: number;
   prometUKorist: number;
   prometNaTeret: number;
   novoStanje: number;

   racun: Racun;
   nalozi: Nalog[] = [];

   constructor(id:number,brojIzvoda:string,datumPrometa:any,
    prethodnoStanje:number, prometUKorist:number, prometNaTeret:number, novoStanje: number, 
    racun: Racun,nalozi: Nalog[]){
    this.id = id;
    this.brojIzvoda = brojIzvoda;
    this.datumPrometa = datumPrometa;
    this.prethodnoStanje = prethodnoStanje;
    this.prometUKorist = prometUKorist;
    this.prometNaTeret = prometNaTeret;
    this.novoStanje = novoStanje;
    this.racun = racun;
    this.nalozi = nalozi;
    }
}