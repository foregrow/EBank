package demo.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Klijent;
import demo.app.entity.Korisnik;
import demo.app.entity.Racun;
import demo.app.enums.UlogaKorisnika;
import demo.app.repository.KlijentRepository;
import demo.app.util.PasswordBCrypt;
import demo.app.web.dto.KlijentDTO;

@Service
@Transactional(readOnly = true)
public class KlijentService implements KlijentServiceInterface,KlijentDTOServiceInterface{
	
	@Autowired
	KlijentRepository kr;
	
	@Autowired
	RacunService rs;
	
	@Autowired
	KorisnikService ks;
	
	@Override
	public List<Klijent> findAll(){
		return kr.findAll();
	}


	@Override
	public Klijent findOne(long id) {
		
		return kr.findById(id).orElse(null);
	}

	@Override
	@Transactional(readOnly = false)
	public Klijent save(Klijent klijent) {
	
		return kr.save(klijent);
	}

	
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		
		kr.deleteById(id);
	}
	
	@Override
	public Klijent getByKorisnikId(long id) {
		return kr.getByKorisnikId(id);
	}
	
	@Override
	public List<KlijentDTO> getAllDTOs(List<Klijent> klijenti) {
		List<KlijentDTO> dtos = new ArrayList<>();
		for(Klijent k : klijenti) {
			KlijentDTO dto = new KlijentDTO(k);
			dto.setRacuniListFromSet(k.getRacuni());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public KlijentDTO getKlijentDTO(Klijent klijent) {
		KlijentDTO dto = new KlijentDTO(klijent);
		dto.setRacuniListFromSet(klijent.getRacuni());
		return dto;
	}
	
	@Override
	public KlijentDTO getKlijentDTOWithActivniRacuni(Klijent klijent) {
		KlijentDTO dto = new KlijentDTO(klijent);
		dto.setAktivniRacuniListFromSet(klijent.getRacuni());
		return dto;
	}


	/*@Override
	public List<Klijent> getOdobreniByBankaId(long id) {
		return kr.getOdobreniByBankaId(id);
	}
	
	@Override
	public List<Klijent> getNeodobreniByBankaId(long id) {
		return kr.getNeodobreniByBankaId(id);
	}*/


	@Transactional(readOnly = false)
	public void zahtevZaOtvaranjeRacuna(Klijent klijent, Racun racun, int param) {
		
		
		
		if(param == 1) {
			klijent.getRacuni().add(racun);
			kr.save(klijent);
			Korisnik k = new Korisnik();
			String korIme = ks.createKorIme(klijent.getIme(), klijent.getPrezime());
			k.setKorisnickoIme(korIme);
			k.setUloga(UlogaKorisnika.KORISNIK);
			k.setLozinka(PasswordBCrypt.hashPassword(klijent.getIme()));
			k.setKlijent(klijent);
			ks.save(k);
			klijent.setKorisnik(k);
			kr.save(klijent);
		}
		racun.setKlijent(klijent);
		rs.save(racun);
		
	}


	@Override
	public List<Klijent> getAllWithAktivanRacunByBanka(long id) {
		return kr.getAllWithAktivanRacunByBanka(id);
	}
	
	
}
