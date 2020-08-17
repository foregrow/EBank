package demo.app.web.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.app.entity.KursnaLista;

public class KursnaListaDTO {
	
	private long id;
	private Date datum;
	private String broj;
	private Date datumPrimene;
	
	private BankaDTO banka;
	
	private List<KursUValutiDTO> kurseviUValutama = new ArrayList<KursUValutiDTO>();
	
	public KursnaListaDTO() {
		
	}
	
	public KursnaListaDTO(KursnaLista obj) {
		id = obj.getId();
		datum = obj.getDatum();
		broj = obj.getBroj();
		datumPrimene = obj.getDatumPrimene();
		if(obj.getBanka() != null)
			banka = new BankaDTO(obj.getBanka());
	}
	
	public KursnaListaDTO(long id, Date datum, String broj, Date datumPrimene, BankaDTO banka,
			List<KursUValutiDTO> kurseviUValutama) {
		super();
		this.id = id;
		this.datum = datum;
		this.broj = broj;
		this.datumPrimene = datumPrimene;
		this.banka = banka;
		this.kurseviUValutama = kurseviUValutama;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public Date getDatumPrimene() {
		return datumPrimene;
	}

	public void setDatumPrimene(Date datumPrimene) {
		this.datumPrimene = datumPrimene;
	}

	public BankaDTO getBanka() {
		return banka;
	}

	public void setBanka(BankaDTO banka) {
		this.banka = banka;
	}

	public List<KursUValutiDTO> getKurseviUValutama() {
		return kurseviUValutama;
	}
}
