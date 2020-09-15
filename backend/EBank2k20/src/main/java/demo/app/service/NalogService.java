package demo.app.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.DnevnoStanje;
import demo.app.entity.Drzava;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.entity.Valuta;
import demo.app.repository.NalogRepository;
import demo.app.web.dto.NalogDTO;


@Service
@Transactional(readOnly = true)
public class NalogService  implements NalogServiceInterface, NalogDTOServiceInterface {

	@Autowired
	NalogRepository nr;
	
	@Autowired
	DnevnoStanjeService dss;
	
	@Autowired
	IzvestajService is;
	
	@Autowired
	RacunService rs;

	
	@Override
	public List<Nalog> findAll() {
		return nr.findAll();
	}
	@Override
	public Nalog findOne(long id) {
		return nr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Nalog save(Nalog nalog) {
		return nr.save(nalog);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		nr.deleteById(id);
	}
	@Override
	public List<Nalog> naloziDnevnogStanjaZaRacunPoDatumu(Date odDatum, Date doDatum, long rid) {
		return nr.naloziDnevnogStanjaZaRacunPoDatumu(odDatum, doDatum, rid);
	}

	@Override
	@Transactional(readOnly = false)
	public void saveTransakcija(NalogDTO dto,Racun racunDuznika,Racun racunPrimaoca, Drzava drzava, Valuta valuta){

		Date datumPrijema = is.getDateFromMillis(System.currentTimeMillis());
		Date datumValute = is.getDateFromMillis(System.currentTimeMillis());

		List<DnevnoStanje> dnevnoStanjeZaDatum = new ArrayList<>();
		//List<DnevnoStanje> primaocDnevnoStanjeZaDatum = new ArrayList<>();
		
		
		Nalog nalog = null;
		for(int i=1;i<3;i++){
			nalog = new Nalog();
			nalog.setDatumPrijema(datumPrijema);
			nalog.setDatumValute(datumValute);
			nalog.setDuznik(racunDuznika.getKlijent().getIme() + " " + racunDuznika.getKlijent().getPrezime());
			nalog.setPrimaoc(racunPrimaoca.getKlijent().getIme() + " " + racunPrimaoca.getKlijent().getPrezime());
			nalog.setHitno(dto.isHitno());
			nalog.setIznos(dto.getIznos());
			nalog.setModelOdobrenja(dto.getModelOdobrenja());
			nalog.setPozivNaBrojOdobrenja(dto.getPozivNaBrojOdobrenja());
			nalog.setModelZaduzenja(dto.getModelZaduzenja());
			nalog.setPozivNaBrojZaduzenja(dto.getPozivNaBrojZaduzenja());
			nalog.setStatus(true);
			nalog.setSvrhaPlacanja(dto.getSvrhaPlacanja());
			nalog.setTipGreske("");
			nalog.setVrstaPlacanja("bezgotovinsko");
			nalog.setDrzava(drzava);
			nalog.setValuta(valuta);
			nalog.setRacunDuznika(racunDuznika);
			nalog.setRacunPrimaoca(racunPrimaoca);
			
			DnevnoStanje ds = null;
			double novoStanje = 0;
			if(i == 1) {
				dnevnoStanjeZaDatum = dss.dnevnoStanjeZaDatum(datumPrijema, racunDuznika.getId());
				novoStanje = racunDuznika.getStanje()-nalog.getIznos();
				racunDuznika.setStanje(novoStanje);
			}else if(i == 2) {
				dnevnoStanjeZaDatum = dss.dnevnoStanjeZaDatum(datumPrijema, racunPrimaoca.getId());
				novoStanje = racunPrimaoca.getStanje()+nalog.getIznos();	
				racunPrimaoca.setStanje(novoStanje);
			}
			
			if(dnevnoStanjeZaDatum.isEmpty()) {
				ds = new DnevnoStanje();
				int count = dss.countDnevnoStanje();
				count++;
				ds.setBrojIzvoda(Integer.toString(count));
				ds.setDatumPrometa(datumPrijema);
				if(i == 1) {
					ds.setPrethodnoStanje(novoStanje+nalog.getIznos());
					ds.setNovoStanje(novoStanje);
					ds.setPrometNaTeret(nalog.getIznos());
					ds.setPrometUKorist(0);
					ds.setRacun(racunDuznika);
				}else if(i == 2) {
					ds.setPrethodnoStanje(novoStanje-nalog.getIznos());
					ds.setNovoStanje(novoStanje);
					ds.setPrometNaTeret(0);
					ds.setPrometUKorist(nalog.getIznos());
					ds.setRacun(racunPrimaoca);
				}
				dss.save(ds);
				nalog.setDnevnoStanje(ds);
				nr.save(nalog);
			}else {
				
				for(DnevnoStanje d : dnevnoStanjeZaDatum) {
					d.setPrethodnoStanje(d.getNovoStanje());
					d.setNovoStanje(novoStanje);
					if(i == 1) {
						d.setPrometNaTeret(nalog.getIznos());
						d.setPrometUKorist(0);
					}else if(i == 2) {
						d.setPrometNaTeret(0);
						d.setPrometUKorist(nalog.getIznos());
					}
					nalog.setDnevnoStanje(d);
					dss.save(d);
					nr.save(nalog);
				}
			}
			
			
			if(i==1)
				rs.save(racunDuznika);
			else if(i==2)
				rs.save(racunPrimaoca);

		}
		
	}

	
	
	

}
