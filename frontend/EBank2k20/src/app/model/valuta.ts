import { Drzava } from './drzava';
import { Racun } from './racun';
import { Nalog } from './nalog';
import { KursUValuti } from './kursuvaluti';

export class Valuta{
    /*
    private long id;
	private String sifra;
	private String naziv;
	
	private DrzavaDTO drzava;
	
	private List<RacunDTO> racuni = new ArrayList<RacunDTO>();
	private List<KursUValutiDTO> kursKaoOsnovnaValuta = new ArrayList<KursUValutiDTO>();
	private List<KursUValutiDTO> kursKaoSporednaValuta = new ArrayList<KursUValutiDTO>();
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
    */

   id: number;
   sifra: string;
   naziv: string;

   drzava: Drzava;

   racuni: Racun[] = [];
   kursKaoOsnovnaValuta: KursUValuti[] = [];
   kursKaoSporednaValuta: KursUValuti[] = [];
   nalozi: Nalog[] = [];

   constructor(id:number,sifra:string,naziv:string,drzava:Drzava,
    racuni:Racun[],kursKaoOsnovnaValuta: KursUValuti[],kursKaoSporednaValuta: KursUValuti[],
    nalozi: Nalog[]){
    this.id = id;
    this.sifra = sifra;
    this.naziv = naziv;
    this.drzava = drzava;
    this.racuni = racuni;
    this.kursKaoOsnovnaValuta = kursKaoOsnovnaValuta;
    this.kursKaoSporednaValuta = kursKaoSporednaValuta;
    this.nalozi = nalozi;
    }
}