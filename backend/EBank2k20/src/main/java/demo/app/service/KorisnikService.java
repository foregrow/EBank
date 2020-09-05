package demo.app.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.app.entity.Korisnik;
import demo.app.repository.KorisnikRepository;
import demo.app.web.dto.KorisnikDTO;

@Service
@Transactional(readOnly = true)
public class KorisnikService  implements KorisnikServiceInterface, KorisnikDTOServiceInterface, UserDetailsService {

	@Autowired
	KorisnikRepository kr;
	
	@Override
	public List<Korisnik> findAll() {
		return kr.findAll();
	}
	@Override
	public Korisnik findOne(long id) {
		return kr.findById(id).orElse(null);
	}
	@Override
	@Transactional(readOnly = false)
	public Korisnik save(Korisnik kor) {
		return kr.save(kor);
	}
	@Override
	@Transactional(readOnly = false)
	public void remove(long id) {
		kr.deleteById(id);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik user = kr.findByKorisnickoIme(username);
			
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getUloga().toString()));
		return new org.springframework.security.core.userdetails.User(user.getKorisnickoIme(),user.getLozinka(), grantedAuthorities);
	}
	@Override
	public Korisnik findByKorisnickoIme(String username) {
		return kr.findByKorisnickoIme(username);
	}
	
	@Override
	public List<KorisnikDTO> getAllDTOs(List<Korisnik> korisnici) {
		List<KorisnikDTO> dtos = new ArrayList<>();
		for (Korisnik s : korisnici) {
			KorisnikDTO dto = new KorisnikDTO(s);
			dtos.add(dto);
		}
		return dtos;	
	}
	@Override
	public KorisnikDTO getKorisnikDTO(Korisnik k) {
		KorisnikDTO dto = new KorisnikDTO(k);
		return dto;
	}
	
	public String createKorIme(String ime, String prezime) {
		long count = 0;
		StringBuilder strKorIme = new StringBuilder();
		strKorIme.append(ime);
		strKorIme.append(".");
		strKorIme.append(prezime);
		Korisnik kor = kr.findByKorisnickoIme(strKorIme.toString());
		while(kor != null) {
			strKorIme.append(++count);
			kor = kr.findByKorisnickoIme(strKorIme.toString());
		}
		
		return strKorIme.toString();
	}
}
