package demo.app.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import demo.app.enums.TipPoruke;

@Entity
public class Poruka {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	private String pozivNaBrojZaduzenja;
	@Column(unique = false, nullable = true)
	private String sifraValute;
	@OneToOne
	private Poruka poruka2;
	
	
	public Poruka () {
		
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


	public Poruka getPoruka2() {
		return poruka2;
	}


	public void setPoruka2(Poruka poruka2) {
		this.poruka2 = poruka2;
	}


	public String getSifraValute() {
		return sifraValute;
	}


	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}


	public Poruka getPoruka() {
		return poruka2;
	}


	public void setPoruka(Poruka poruka) {
		this.poruka2 = poruka;
	}
	
	
	

	
}
