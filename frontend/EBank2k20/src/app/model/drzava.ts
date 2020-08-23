import { Nalog } from './nalog';
import { Valuta } from './valuta';

export class Drzava{
    /*
    private long id;
	private String sifra;
	private String naziv;
	
	private List<ValutaDTO> valute = new ArrayList<ValutaDTO>();
	private List<NalogDTO> nalozi = new ArrayList<NalogDTO>();
    */

   id: number;
   sifra: string;
   naziv: string;

   valute: Valuta[] = [];
   nalozi: Nalog[] = [];

   constructor(id:number,sifra:string,naziv:string,
    nalozi: Nalog[],valute: Valuta[]){
    this.id = id;
    this.sifra = sifra;
    this.naziv = naziv;
    this.valute = valute;
    this.nalozi = nalozi;
    }
}