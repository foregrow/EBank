package demo.app.web.dto;

import java.util.Date;



import demo.app.entity.Poruka;
import demo.app.enums.TipPoruke;

public class PorukaDTO {

	

	private long id;
	private TipPoruke tipPoruke;
	private double iznos;
	private String swiftDuznikaBanke;
	private String swiftPrimaocaBanke;
	private String obracunskiRacunDuznikaBanke;
	private String obracunskiRacunPrimaocaBanke;
	private String duznik;
	private String svrhaPlacanja;
	private String primalac;
	private Date datumNaloga;
	private Date datumValute;
	private String racunDuznika;
	private String racunPrimaoca;
	private String modelOdobrenja;
	private String modelZaduzenja;
	private String pozivNaBrojOdobrenja;
	private String pozivNaBrojZaduzenja;
	private String sifraValute;
	private PorukaDTO poruka;
	
	
	public PorukaDTO () {
		
	}
	
	public PorukaDTO (Poruka p) {
		this.id = p.getId();
		this.tipPoruke = p.getTipPoruke();
		this.iznos = p.getIznos();
		this.swiftDuznikaBanke = p.getSwiftDuznikaBanke();
		this.swiftPrimaocaBanke = p.getSwiftPrimaocaBanke();
		this.obracunskiRacunDuznikaBanke = p.getObracunskiRacunDuznikaBanke();
		this.obracunskiRacunPrimaocaBanke = p.getObracunskiRacunPrimaocaBanke();
		this.duznik = p.getDuznik();
		this.svrhaPlacanja = p.getSvrhaPlacanja();
		this.primalac = p.getPrimalac();
		this.datumNaloga = p.getDatumNaloga();
		this.datumValute = p.getDatumValute();
		this.racunDuznika = p.getRacunDuznika();
		this.racunPrimaoca = p.getRacunPrimaoca();
		this.modelOdobrenja = p.getModelOdobrenja();
		this.modelZaduzenja = p.getModelZaduzenja();
		this.pozivNaBrojOdobrenja = p.getPozivNaBrojOdobrenja();
		this.pozivNaBrojZaduzenja = p.getPozivNaBrojZaduzenja();
		this.sifraValute = p.getSifraValute();
		if(p.getPoruka2() != null) {
			this.poruka = new PorukaDTO(p);
		}
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public TipPoruke getTipPoruke() {
		return tipPoruke;
	}


	public void setTipPoruke(TipPoruke tipPoruke) {
		this.tipPoruke = tipPoruke;
	}


	public double getIznos() {
		return iznos;
	}


	public void setIznos(double iznos) {
		this.iznos = iznos;
	}


	public String getSwiftDuznikaBanke() {
		return swiftDuznikaBanke;
	}


	public void setSwiftDuznikaBanke(String swiftDuznikaBanke) {
		this.swiftDuznikaBanke = swiftDuznikaBanke;
	}


	public String getSwiftPrimaocaBanke() {
		return swiftPrimaocaBanke;
	}


	public void setSwiftPrimaocaBanke(String swiftPrimaocaBanke) {
		this.swiftPrimaocaBanke = swiftPrimaocaBanke;
	}


	public String getObracunskiRacunDuznikaBanke() {
		return obracunskiRacunDuznikaBanke;
	}


	public void setObracunskiRacunDuznikaBanke(String obracunskiRacunDuznikaBanke) {
		this.obracunskiRacunDuznikaBanke = obracunskiRacunDuznikaBanke;
	}


	public String getObracunskiRacunPrimaocaBanke() {
		return obracunskiRacunPrimaocaBanke;
	}


	public void setObracunskiRacunPrimaocaBanke(String obracunskiRacunPrimaocaBanke) {
		this.obracunskiRacunPrimaocaBanke = obracunskiRacunPrimaocaBanke;
	}


	public String getDuznik() {
		return duznik;
	}


	public void setDuznik(String duznik) {
		this.duznik = duznik;
	}


	public String getSvrhaPlacanja() {
		return svrhaPlacanja;
	}


	public void setSvrhaPlacanja(String svrhaPlacanja) {
		this.svrhaPlacanja = svrhaPlacanja;
	}


	public String getPrimalac() {
		return primalac;
	}


	public void setPrimalac(String primalac) {
		this.primalac = primalac;
	}


	public Date getDatumNaloga() {
		return datumNaloga;
	}


	public void setDatumNaloga(Date datumNaloga) {
		this.datumNaloga = datumNaloga;
	}


	public Date getDatumValute() {
		return datumValute;
	}


	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}


	public String getRacunDuznika() {
		return racunDuznika;
	}


	public void setRacunDuznika(String racunDuznika) {
		this.racunDuznika = racunDuznika;
	}


	public String getRacunPrimaoca() {
		return racunPrimaoca;
	}


	public void setRacunPrimaoca(String racunPrimaoca) {
		this.racunPrimaoca = racunPrimaoca;
	}


	public String getModelOdobrenja() {
		return modelOdobrenja;
	}


	public void setModelOdobrenja(String modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}


	public String getModelZaduzenja() {
		return modelZaduzenja;
	}


	public void setModelZaduzenja(String modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}


	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}


	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}


	


	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public String getSifraValute() {
		return sifraValute;
	}


	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}


	public PorukaDTO getPoruka() {
		return poruka;
	}


	public void setPoruka(PorukaDTO poruka) {
		this.poruka = poruka;
	}
	
	
	

	
}
