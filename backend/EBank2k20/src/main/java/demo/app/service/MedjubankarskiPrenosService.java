package demo.app.service;

import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.DnevnoStanje;
import demo.app.entity.Drzava;
import demo.app.entity.MedjubankarskiPrenos;
import demo.app.entity.Poruka;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.enums.TipPoruke;
import demo.app.enums.TipTransfera;
import demo.app.repository.MedjubankarskiPrenosRepository;
import demo.app.web.dto.MedjubankarskiPrenosDTO;
import demo.app.web.dto.NalogDTO;




@Service
@Transactional(readOnly = true)
public class MedjubankarskiPrenosService  implements MedjubankarskiPrenosServiceInterface, MedjubankarskiPrenosDTOServiceInterface {

	@Autowired
	MedjubankarskiPrenosRepository mpr;
	
	@Autowired
	NalogService ns;
	@Autowired
	RacunService rs;
	@Autowired
	PorukaService ps;
	@Autowired
	IzvestajIObradeService is;
	@Autowired
	DnevnoStanjeService dss;
	
	private static final double MIN_IZNOS_RTGS = 250000;
	@Override
	public List<MedjubankarskiPrenosDTO> getAllDTOs(List<MedjubankarskiPrenos> md) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MedjubankarskiPrenosDTO getMedjubankarskiPrenosDTO(MedjubankarskiPrenos m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedjubankarskiPrenos> findAll() {
		return mpr.findAll();
	}

	@Override
	public MedjubankarskiPrenos findOne(long id) {
		return mpr.findById(id).orElse(null);
	}

	@Override
	public MedjubankarskiPrenos save(MedjubankarskiPrenos medj) {
		return mpr.save(medj);
	}

	@Override
	public void remove(long id) {
		mpr.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void saveMedjubankarskiPrenos(NalogDTO dto, Racun racunDuznika, Racun racunPrimaoca,Drzava drzava, Valuta valuta) {
		racunDuznika.setRezervisanIznos(dto.getIznos());
		rs.save(racunDuznika);
		Date datumPrijema = is.getDateFromMillis(System.currentTimeMillis());
		Poruka rtgs_clearing = new Poruka();
		if(dto.getIznos() >= MIN_IZNOS_RTGS || dto.isHitno()) { //RTGS
			rtgs_clearing.setTipPoruke(TipPoruke.MT103);
		}else if(dto.getIznos() < MIN_IZNOS_RTGS) { //CLEARING
			rtgs_clearing.setTipPoruke(TipPoruke.MT102);
		}
		rtgs_clearing.setIznos(dto.getIznos());
		rtgs_clearing.setSwiftDuznikaBanke(racunDuznika.getBanka().getSwift());
		rtgs_clearing.setSwiftPrimaocaBanke(racunPrimaoca.getBanka().getSwift());
		rtgs_clearing.setObracunskiRacunDuznikaBanke(racunDuznika.getBanka().getObracunskiRacun());
		rtgs_clearing.setObracunskiRacunPrimaocaBanke(racunPrimaoca.getBanka().getObracunskiRacun());
		rtgs_clearing.setDuznik(racunDuznika.getKlijent().getIme() + " " + racunDuznika.getKlijent().getPrezime());
		rtgs_clearing.setPrimalac(racunPrimaoca.getKlijent().getIme() + " " + racunPrimaoca.getKlijent().getPrezime());
		rtgs_clearing.setSvrhaPlacanja(dto.getSvrhaPlacanja());
		rtgs_clearing.setDatumNaloga(datumPrijema);
		rtgs_clearing.setDatumValute(datumPrijema);
		rtgs_clearing.setRacunDuznika(racunDuznika.getBrojRacuna());
		rtgs_clearing.setRacunPrimaoca(racunPrimaoca.getBrojRacuna());
		rtgs_clearing.setPozivNaBrojOdobrenja(dto.getPozivNaBrojOdobrenja());
		rtgs_clearing.setModelOdobrenja(dto.getModelOdobrenja());
		rtgs_clearing.setPozivNaBrojZaduzenja(dto.getPozivNaBrojZaduzenja());
		rtgs_clearing.setModelZaduzenja(dto.getModelZaduzenja());
		rtgs_clearing.setSifraValute(valuta.getSifra());
		rtgs_clearing.setPoruka2(null);
		rtgs_clearing = ps.save(rtgs_clearing);
		Poruka rtgs900 = new Poruka();
		rtgs900.setSwiftDuznikaBanke(racunDuznika.getBanka().getSwift());
		rtgs900.setObracunskiRacunDuznikaBanke(racunDuznika.getBanka().getObracunskiRacun());
		rtgs900.setDatumValute(datumPrijema);
		rtgs900.setSifraValute(valuta.getSifra());
		rtgs900.setIznos(dto.getIznos());
		rtgs900.setPoruka2(rtgs_clearing);
		rtgs900.setTipPoruke(TipPoruke.MT900);
		rtgs900 = ps.save(rtgs900);
		Poruka rtgs910 = new Poruka();
		rtgs910.setSwiftPrimaocaBanke(racunPrimaoca.getBanka().getSwift());
		rtgs910.setObracunskiRacunPrimaocaBanke(racunPrimaoca.getBanka().getObracunskiRacun());
		rtgs910.setDatumValute(datumPrijema);
		rtgs910.setSifraValute(valuta.getSifra());
		rtgs910.setIznos(dto.getIznos());
		rtgs910.setPoruka2(rtgs_clearing);
		rtgs910.setTipPoruke(TipPoruke.MT910);
		rtgs910 = ps.save(rtgs910);
		
		ns.saveTransakcija(dto,racunDuznika,racunPrimaoca,drzava,valuta,1); //skida se novac duzniku
		
		MedjubankarskiPrenos mprenos = new MedjubankarskiPrenos();
		mprenos.setBankaDuznika(racunDuznika.getBanka());
		mprenos.setBankaPrimaoca(racunPrimaoca.getBanka());
		mprenos.setIznos(dto.getIznos());
		mprenos.setValuta(valuta.getSifra());
		if(rtgs_clearing.getTipPoruke() == TipPoruke.MT103)
			mprenos.setTipTransfera(TipTransfera.RTGS);
		else if(rtgs_clearing.getTipPoruke() == TipPoruke.MT102)
			mprenos.setTipTransfera(TipTransfera.CLEARING);
		mprenos.setRacunDuznika(racunDuznika);
		mprenos.setRacunPrimaoca(racunPrimaoca);
		mprenos.setDatumPrenosa(datumPrijema);
		save(mprenos);
		
		is.exportMedjubankarskiPrenos(mprenos);
		
	}

	

}
