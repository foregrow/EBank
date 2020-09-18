export class Poruka{

    id: number;
   tipPoruke: any;
   iznos: number;
   swiftDuznikaBanke: string;
   swiftPrimaocaBanke: string;
   datumValute: any;
   modelZaduzenja: string;
   pozivNaBrojZaduzenja: string;
   modelOdobrenja: string;
   pozivNaBrojOdobrenja: string;
   duznik: string;
   primalac: string;
   svrhaPlacanja: string;
   datumNaloga: any;
   racunPrimaoca: string;
   racunDuznika: string
   sifraValute: string;
   poruka2: Poruka;
   obracunskiRacunDuznikaBanke: string;
   obracunskiRacunPrimaocaBanke: string;

   constructor(id:number,tipPoruke:any,iznos:number,swiftDuznikaBanke:string,
    swiftPrimaocaBanke:string,obracunskiRacunDuznikaBanke:string,obracunskiRacunPrimaocaBanke:string,duznik:string,
    svrhaPlacanja:string,primalac: string,datumNaloga: any,datumValute: any,racunDuznika: string,racunPrimaoca: string,modelOdobrenja: string,
    modelZaduzenja: string,pozivNaBrojOdobrenja: string,pozivNaBrojZaduzenja: string,sifraValute:string){
        this.id = id;
		this.tipPoruke = tipPoruke;
		this.iznos = iznos;
		this.swiftDuznikaBanke = swiftDuznikaBanke;
		this.swiftPrimaocaBanke = swiftPrimaocaBanke;
		this.obracunskiRacunDuznikaBanke = obracunskiRacunDuznikaBanke;
		this.obracunskiRacunPrimaocaBanke = obracunskiRacunPrimaocaBanke;
		this.duznik = duznik;
		this.svrhaPlacanja = svrhaPlacanja;
		this.primalac = primalac;
		this.datumNaloga = datumNaloga;
		this.datumValute = datumValute;
		this.racunDuznika = racunDuznika;
		this.racunPrimaoca = racunPrimaoca;
		this.modelOdobrenja = modelOdobrenja;
		this.modelZaduzenja = modelZaduzenja;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.sifraValute = sifraValute;
    }
    /*
    private long id;
	@Column(unique = false, nullable = true)
	private TipPoruke tipPoruke;
	@Column(unique = false, nullable = true)
	private double iznos;
	@Column(unique = false, nullable = true)
	private String swiftDuznikaBanke;
	@Column(unique = false, nullable = true)
	private String swiftPrimaocaBanke;
	@Column(unique = false, nullable = true)
	private String obracunskiRacunDuznikaBanke;
	@Column(unique = false, nullable = true)
	private String obracunskiRacunPrimaocaBanke;
	@Column(unique = false, nullable = true)
	private String duznik;
	@Column(unique = false, nullable = true)
	private String svrhaPlacanja;
	@Column(unique = false, nullable = true)
	private String primalac;
	@Column(unique = false, nullable = true)
	private Date datumNaloga;
	@Column(unique = false, nullable = true)
	private Date datumValute;
	@Column(unique = false, nullable = true)
	private String racunDuznika;
	@Column(unique = false, nullable = true)
	private String racunPrimaoca;
	@Column(unique = false, nullable = true)
	private String modelOdobrenja;
	@Column(unique = false, nullable = true)
	private String modelZaduzenja;
	@Column(unique = false, nullable = true)
	private String pozivNaBrojOdobrenja;
	@Column(unique = false, nullable = true)
	private String pozivNaModelZaduzenja;
	@Column(unique = false, nullable = true)
	private String sifraValute;
	@OneToOne
	private Poruka poruka2;
    */
}