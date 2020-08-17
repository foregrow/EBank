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

@Service
@Transactional(readOnly = true)
public class KorisnikService  implements KorisnikServiceInterface, UserDetailsService {

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
	public Korisnik save(Korisnik kor) {
		return kr.save(kor);
	}
	@Override
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
}
