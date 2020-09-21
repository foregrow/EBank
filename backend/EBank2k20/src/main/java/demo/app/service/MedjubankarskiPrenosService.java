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
		Poruka rtgs103_created = null;
		Poruka rtgs900_created = null;
		Poruka rtgs910_created = null;
		if(dto.getIznos() >= MIN_IZNOS_RTGS || dto.isHitno()) { //RTGS
			Poruka rtgs103 = new Poruka();
			rtgs103.setTipPoruke(TipPoruke.MT103);
			rtgs103.setIznos(dto.getIznos());
			rtgs103.setSwiftDuznikaBanke(racunDuznika.getBanka().getSwift());
			rtgs103.setSwiftPrimaocaBanke(racunPrimaoca.getBanka().getSwift());
			rtgs103.setObracunskiRacunDuznikaBanke(racunDuznika.getBanka().getObracunskiRacun());
			rtgs103.setObracunskiRacunPrimaocaBanke(racunPrimaoca.getBanka().getObracunskiRacun());
			rtgs103.setDuznik(racunDuznika.getKlijent().getIme() + " " + racunDuznika.getKlijent().getPrezime());
			rtgs103.setPrimalac(racunPrimaoca.getKlijent().getIme() + " " + racunPrimaoca.getKlijent().getPrezime());
			rtgs103.setSvrhaPlacanja(dto.getSvrhaPlacanja());
			rtgs103.setDatumNaloga(datumPrijema);
			rtgs103.setDatumValute(datumPrijema);
			rtgs103.setRacunDuznika(racunDuznika.getBrojRacuna());
			rtgs103.setRacunPrimaoca(racunPrimaoca.getBrojRacuna());
			rtgs103.setPozivNaBrojOdobrenja(dto.getPozivNaBrojOdobrenja());
			rtgs103.setModelOdobrenja(dto.getModelOdobrenja());
			rtgs103.setPozivNaBrojZaduzenja(dto.getPozivNaBrojZaduzenja());
			rtgs103.setModelZaduzenja(dto.getModelZaduzenja());
			rtgs103.setSifraValute(valuta.getSifra());
			rtgs103.setPoruka2(null);
			rtgs103_created = ps.save(rtgs103);
			Poruka rtgs900 = new Poruka();
			rtgs900.setSwiftDuznikaBanke(racunDuznika.getBanka().getSwift());
			rtgs900.setObracunskiRacunDuznikaBanke(racunDuznika.getBanka().getObracunskiRacun());
			rtgs900.setDatumValute(datumPrijema);
			rtgs900.setSifraValute(valuta.getSifra());
			rtgs900.setIznos(dto.getIznos());
			rtgs900.setPoruka2(rtgs103_created);
			rtgs900.setTipPoruke(TipPoruke.MT900);
			rtgs900_created = ps.save(rtgs900);
			Poruka rtgs910 = new Poruka();
			rtgs910.setSwiftPrimaocaBanke(racunPrimaoca.getBanka().getSwift());
			rtgs910.setObracunskiRacunPrimaocaBanke(racunPrimaoca.getBanka().getObracunskiRacun());
			rtgs910.setDatumValute(datumPrijema);
			rtgs910.setSifraValute(valuta.getSifra());
			rtgs910.setIznos(dto.getIznos());
			rtgs910.setPoruka2(rtgs103_created);
			rtgs910.setTipPoruke(TipPoruke.MT910);
			rtgs910_created = ps.save(rtgs910);
			
			ns.saveTransakcija(dto,racunDuznika,racunPrimaoca,drzava,valuta,1); //skida se novac duzniku
			
			MedjubankarskiPrenos mprenos = new MedjubankarskiPrenos();
			mprenos.setBankaDuznika(racunDuznika.getBanka());
			mprenos.setBankaPrimaoca(racunPrimaoca.getBanka());
			mprenos.setIznos(dto.getIznos());
			mprenos.setValuta(valuta.getSifra());
			mprenos.setTipTransfera(TipTransfera.RTGS);
			mprenos.setRacunDuznika(racunDuznika);
			mprenos.setRacunPrimaoca(racunPrimaoca);
			mprenos.setDatumPrenosa(datumPrijema);
			save(mprenos);
			
			/*MedjubankarskiPrenos mprenos = new MedjubankarskiPrenos();
			mprenos.setBankaDuznika(racunDuznika.getBanka());
			mprenos.setBankaPrimaoca(racunPrimaoca.getBanka());
			mprenos.setIznos(dto.getIznos());
			mprenos.setValuta(valuta.getSifra());
			mprenos.setTipTransfera(TipTransfera.RTGS);
			mprenos.setRacunDuznika(racunDuznika);
			mprenos.setRacunPrimaoca(racunPrimaoca);
			mprenos.setDatumPrenosa(datumPrijema);
			save(mprenos);
			
			DnevnoStanje ds = null;
			if(dnevnoStanjeZaDatum.isEmpty()) {
				ds = new DnevnoStanje();
				int count = dss.countDnevnoStanje();
				count++;
				ds.setBrojIzvoda(Integer.toString(count));
				ds.setDatumPrometa(datumPrijema);
				ds.setPrethodnoStanje(racunPrimaoca.getStanje()-dto.getIznos());
				ds.setNovoStanje(racunPrimaoca.getStanje());
				ds.setPrometNaTeret(0);
				ds.setPrometUKorist(dto.getIznos());
				ds.setRacun(racunPrimaoca);
				
				dss.save(ds);
				mprenos.setDnevnoStanje(ds);
				save(mprenos);
			}else {
				for(DnevnoStanje d : dnevnoStanjeZaDatum) {
					d.setPrethodnoStanje(d.getNovoStanje());
					d.setNovoStanje(racunPrimaoca.getStanje());
					d.setPrometNaTeret(0);
					d.setPrometUKorist(dto.getIznos());
					
					dss.save(d);
					mprenos.setDnevnoStanje(d);
					save(mprenos);
				}
			}*/
			
			is.exportMedjubankarskiPrenos(mprenos);
		}else { //CLEARING
			
		}
		
	}

	

}
