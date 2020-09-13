package demo.app.service;

import java.util.Date;
import java.util.List;

import demo.app.web.dto.IzvestajBankaRacuniDTO;
import demo.app.web.dto.IzvestajDnevnoStanjeRacunDTO;

public interface IzvestajServiceInterface {

	List<IzvestajDnevnoStanjeRacunDTO> getAllIzvestajDnevnoStanjeRacunDTOs(Date odDatum, Date doDatum, long rid);
	List<IzvestajBankaRacuniDTO> getAllIzvestajBankaRacuniDTOs(long bid);
}
