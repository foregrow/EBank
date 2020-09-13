package demo.app.service;

import java.io.File;


import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import demo.app.entity.DnevnoStanje;
import demo.app.entity.Nalog;
import demo.app.entity.Racun;
import demo.app.web.dto.IzvestajBankaRacuniDTO;
import demo.app.web.dto.IzvestajDnevnoStanjeRacunDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class IzvestajService implements IzvestajServiceInterface {

	private final String GRESKA = "GRESKA";
	
	@Autowired
	DnevnoStanjeService dss;
	
	@Autowired
	RacunService rs;
	
	@Override
	public List<IzvestajDnevnoStanjeRacunDTO> getAllIzvestajDnevnoStanjeRacunDTOs(Date odDatum, Date doDatum, long rid){
		List<DnevnoStanje> dnevnoStanje = dss.dnevnoStanjeZaRacunPoDatumu(odDatum, doDatum, rid);
		List<IzvestajDnevnoStanjeRacunDTO> izvestaji = new ArrayList<IzvestajDnevnoStanjeRacunDTO>();
		IzvestajDnevnoStanjeRacunDTO iz = new IzvestajDnevnoStanjeRacunDTO();
		izvestaji.add(iz);
		for(DnevnoStanje ds : dnevnoStanje) {
			for(Nalog n : ds.getNalozi()) {
				iz = new IzvestajDnevnoStanjeRacunDTO(ds.getBrojIzvoda(),ds.getDatumPrometa(),ds.getNovoStanje(),ds.getPrethodnoStanje(),
						n.getIznos(),n.getDuznik(),n.getPrimaoc(),n.getSvrhaPlacanja(),n.getVrstaPlacanja(),n.getValuta().getSifra());
				izvestaji.add(iz);
			}
		}
		
		return izvestaji;
	}
	
	@Override
	public List<IzvestajBankaRacuniDTO> getAllIzvestajBankaRacuniDTOs(long bid) {
		List<Racun> racuni = rs.getByBankaId(bid);
		List<IzvestajBankaRacuniDTO> izvestaji = new ArrayList<IzvestajBankaRacuniDTO>();
		IzvestajBankaRacuniDTO iz = new IzvestajBankaRacuniDTO();
		izvestaji.add(iz);
		for(Racun r : racuni) {
			iz = new IzvestajBankaRacuniDTO(r.getBrojRacuna(),r.getStanje(),r.getDatumKreiranja(),r.getBanka().getNaziv());
			izvestaji.add(iz);
		}
		return izvestaji;
	}
	
	public File getResourceFile(int tipIzvestaja) {
		File file = null;
		try {
			switch (tipIzvestaja) {
			case 0:
				file = ResourceUtils.getFile("classpath:reports\\izvestajRacuna.jrxml");
				break;
			case 1:
				file = ResourceUtils.getFile("classpath:reports\\izvestajRacunaBanke.jrxml");
				break;
			default:
				break;
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return file;
	}

	public Map<String, Object> getDataSourceAndFillReport(List<IzvestajDnevnoStanjeRacunDTO> dnevnoStanjeRacuna, List<IzvestajBankaRacuniDTO> racuniBanke, File file, long racunId) throws FileNotFoundException{
		String pdfExtension = ".pdf";
		String resourceDirectory = Paths.get("src","main","resources").toAbsolutePath().toString();
		StringBuilder outF = new StringBuilder();
		outF.append(resourceDirectory);
		outF.append("\\izvestajPdf");
		
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		} catch (JRException e) {	
			e.printStackTrace();
		}
		
		JRBeanCollectionDataSource dataSource = null;
		if(dnevnoStanjeRacuna != null) {
			if(dnevnoStanjeRacuna.size() > 1) {
				dataSource = new JRBeanCollectionDataSource(dnevnoStanjeRacuna);
				outF.append("\\dnevnoStanje\\dnevnoStanje");
				outF.append(Long.toString(racunId));
			}else
				return null;
			
			
		}
		else if(racuniBanke != null) {
			if(racuniBanke.size() > 1) {
				dataSource = new JRBeanCollectionDataSource(racuniBanke);
				outF.append("\\banka\\stanjeRacuna");
				outF.append(Long.toString(racunId));
			}else
				return null;
		}
		outF.append(pdfExtension);
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CollectionBeanParam", dataSource);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, outF.toString());
		} catch (JRException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jasperPrint", jasperPrint);
		map.put("outFile",  outF.toString());
		return map;
		
	}
	
	public String exportReportToPdfFileAndGetFileLocation(Map<String, Object> map) {
		String result = GRESKA; //GRESKA konstanta
		boolean successful = false;
		if(map == null) {
			return result;
		}
		JasperPrint jasperPrint = (JasperPrint) map.get("jasperPrint");
		String outFile = (String) map.get("outFile");
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, outFile);
			successful = true;
		} catch (JRException e) {
			e.printStackTrace();
		}
		if(successful)
			result = outFile;
		
		return result;
			
	}
	
	public Date getDateFromString(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
		Date date = null;
		try {
			date = formatter.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public File getPdfIzvestaj(Date odDatum, Date doDatum, long racunId, long bankaId, int tipIzvestaja) throws FileNotFoundException, ParseException {
		
		File f = null;
		List<IzvestajDnevnoStanjeRacunDTO> dnevnoStanjeIzvestaji = null;
		List<IzvestajBankaRacuniDTO> racuniBankeIzvestaji = null;
		//0 je za dnevno stanje zivestaj a 1 je za stanje racuna u banci izvestaj
		if(tipIzvestaja == 0) {
			f = this.getResourceFile(tipIzvestaja);
			dnevnoStanjeIzvestaji = this.getAllIzvestajDnevnoStanjeRacunDTOs(odDatum, doDatum, racunId);
		}
		else if(tipIzvestaja == 1) {
			f = this.getResourceFile(tipIzvestaja);
			racuniBankeIzvestaji = this.getAllIzvestajBankaRacuniDTOs(bankaId);
		}
		
		Map<String,Object> map = this.getDataSourceAndFillReport(dnevnoStanjeIzvestaji,racuniBankeIzvestaji,f,racunId);
		String fileLocation = this.exportReportToPdfFileAndGetFileLocation(map);
		if(fileLocation.equals(GRESKA)) //GRESKA je konstanta
			return null;
		
		//znaci da je putanja fajla validna i ovde se returnuje prethodno kreirani pdf fajl
		File pdfFile = new File(fileLocation);
		return pdfFile;
		
		
	}
	
	
}
