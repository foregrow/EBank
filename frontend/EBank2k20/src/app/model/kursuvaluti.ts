import { Valuta } from './valuta';
import { KursnaLista } from './kursnalista';

export class KursUValuti{
    /*
    private long id;
	private long redniBroj;
	private double kupovni;
	private double srednji;
	private double prodajni;
	
	private ValutaDTO osnovnaValuta;
	private ValutaDTO sporednaValuta;
	private KursnaListaDTO kursnaLista;
    */

   id: number;
   redniBroj: number;
   kupovni: number;
   srednji: number;
   prodajni: number;

   osnovnaValuta: Valuta;
   sporednaValuta: Valuta;
   kursnaLista: KursnaLista;

   constructor(id:number,redniBroj:number,kupovni:number,srednji:number,
    prodajni:number,osnovnaValuta: Valuta,sporednaValuta:Valuta,kursnaLista:KursnaLista){
    this.id = id;
    this.redniBroj = redniBroj;
    this.kupovni = kupovni;
    this.srednji = srednji;
    this.prodajni = prodajni;
    this.osnovnaValuta = osnovnaValuta;
    this.sporednaValuta = sporednaValuta;
    this.kursnaLista = kursnaLista;
    }
}