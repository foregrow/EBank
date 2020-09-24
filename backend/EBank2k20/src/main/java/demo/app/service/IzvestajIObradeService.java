package demo.app.service;

import java.beans.XMLDecoder;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import demo.app.entity.MedjubankarskiPrenos;
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
public class IzvestajIObradeService implements IzvestajIObradeServiceInterface {

	private final String GRESKA = "GRESKA";
	
	@Autowired
	NalogService ns;
	
	@Autowired
	RacunService rs;
	
	@Override
	public List<IzvestajDnevnoStanjeRacunDTO> getAllIzvestajDnevnoStanjeRacunDTOs(Date odDatum, Date doDatum, long rid){
		List<Nalog> nalozi = ns.naloziDnevnogStanjaZaRacunPoDatumu(odDatum, doDatum, rid);
		List<IzvestajDnevnoStanjeRacunDTO> izvestaji = new ArrayList<IzvestajDnevnoStanjeRacunDTO>();
		IzvestajDnevnoStanjeRacunDTO iz = new IzvestajDnevnoStanjeRacunDTO();
		izvestaji.add(iz);

		double trenutnoStanje = 0;
		int count = 0;
		for(Nalog nalog : nalozi) {
			double novoStanje = nalog.getDnevnoStanje().getNovoStanje();
			double prethodnoStanje = nalog.getDnevnoStanje().getPrethodnoStanje();
			if(count==0) {
				trenutnoStanje = prethodnoStanje;
			}else {
				if(rid == nalog.getRacunDuznika().getId()) {
					novoStanje = trenutnoStanje;
					prethodnoStanje = novoStanje + nalog.getIznos(); 
					trenutnoStanje=prethodnoStanje;
					
				}else if(rid == nalog.getRacunPrimaoca().getId()) {
					novoStanje = trenutnoStanje;
					prethodnoStanje = novoStanje - nalog.getIznos(); 
					trenutnoStanje=prethodnoStanje;
				}
			}
			iz = new IzvestajDnevnoStanjeRacunDTO(nalog.getDnevnoStanje().getBrojIzvoda(),nalog.getDnevnoStanje().getDatumPrometa(),novoStanje,prethodnoStanje,
					nalog.getIznos(),nalog.getDuznik(),nalog.getPrimaoc(),nalog.getSvrhaPlacanja(),
					nalog.getVrstaPlacanja(),nalog.getValuta().getSifra());
			izvestaji.add(iz);
			count++;
		}		
		return izvestaji;
	}
	
	@Override
	public List<IzvestajBankaRacuniDTO> getAllIzvestajBankaRacuniDTOs(long bid, long kid) {
		List<Racun> racuni = rs.getByBankaIdAndKlijentIdAndOdobrenAndIzbrisan(bid,kid, true, false);
		List<IzvestajBankaRacuniDTO> izvestaji = new ArrayList<IzvestajBankaRacuniDTO>();
		IzvestajBankaRacuniDTO iz = new IzvestajBankaRacuniDTO();
		izvestaji.add(iz);
		for(Racun r : racuni) {
			iz = new IzvestajBankaRacuniDTO(r.getBrojRacuna(),r.getStanje(),r.getDatumKreiranja());
			izvestaji.add(iz);
		}
		return izvestaji;
	}
	
	public File getResourceFile(int tipIzvestaja) {
		File file = null;
		try {
			switch (tipIzvestaja) {
			case 0:
				file = ResourceUtils.getFile("classpath:izvestaji\\izvestajRacuna.jrxml");
				break;
			case 1:
				file = ResourceUtils.getFile("classpath:izvestaji\\izvestajRacunaBanke.jrxml");
				break;
			default:
				break;
			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		return file;
	}

	public Map<String, Object> getDataSourceAndFillReport(List<IzvestajDnevnoStanjeRacunDTO> dnevnoStanjeRacuna, List<IzvestajBankaRacuniDTO> racuniBanke, File file, long klijentID) throws IOException, ParseException{
		boolean xmlExists = false;

		String datum = this.getStringDateFormat();
		String resourceDirectory = Paths.get("src","main","resources").toAbsolutePath().toString();
		StringBuilder outF = new StringBuilder();
		StringBuilder outFXml = new StringBuilder();
		outF.append(resourceDirectory);
		outF.append("\\izvestajPdf");
		outFXml.append(resourceDirectory);
		outFXml.append("\\izvestajXml");
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
				
				outF.append("\\dnevnoStanje");
				outF.append("\\");
				outF.append(Long.toString(klijentID));
				Files.createDirectories(Paths.get(outF.toString()));
				outF.append("\\dnevnoStanje");
				outF.append(Long.toString(klijentID));
				outF.append("_");
				outF.append(datum);
				
				outFXml.append("\\");
				outFXml.append(Long.toString(klijentID));
				Files.createDirectories(Paths.get(outFXml.toString()));
				outFXml.append("\\dnevnoStanje");
				outFXml.append(Long.toString(klijentID));
				outFXml.append("_");
				outFXml.append(datum);
				xmlExists = true;
			}else
				return null;
			
			
		}
		else if(racuniBanke != null) {
			if(racuniBanke.size() > 1) {
				dataSource = new JRBeanCollectionDataSource(racuniBanke);
				outF.append("\\banka");
				outF.append("\\");
				outF.append(Long.toString(klijentID));
				Files.createDirectories(Paths.get(outF.toString()));
				outF.append("\\stanjeRacuna");
				outF.append(Long.toString(klijentID));
				outF.append("_");
				outF.append(datum);
			}else
				return null;
		}

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CollectionBeanParam", dataSource);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport,parameters,dataSource);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jasperPrint", jasperPrint);
		map.put("outFile",  outF.toString());
		map.put("xmlExists",  xmlExists);
		map.put("outFileXml",  outFXml.toString());
		
		return map;
		
	}
	
	public String exportReportToPdfFileAndGetFileLocation(Map<String, Object> map) {
		String pdfExtension = ".pdf";
		String xmlExtension = ".xml";
		String result = GRESKA; //GRESKA konstanta
		boolean successful = false;
		if(map == null) {
			return result;
		}
		JasperPrint jasperPrint = (JasperPrint) map.get("jasperPrint");
		String outFilePdf = (String) map.get("outFile") + pdfExtension;
		boolean xmlExists = (Boolean) map.get("xmlExists");
		String outFileXml = "";
		if(xmlExists)
			outFileXml = (String) map.get("outFileXml") + xmlExtension;
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, outFilePdf);
			if(xmlExists)
				this.exportReportToXml(jasperPrint, outFileXml);
			successful = true;
		} catch (JRException e) {
			e.printStackTrace();
		}
		if(successful)
			result = outFilePdf;
		
		return result;
			
	}
	public void exportReportToXml(JasperPrint jasperPrint, String outFile) {
		try {
			JasperExportManager.exportReportToXmlFile(jasperPrint, outFile, true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getStringDateFormat() throws ParseException {
		String result = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		Date todayWithZeroTime = formatter.parse(formatter.format(today));
		result = todayWithZeroTime.toString().substring(0, 10);
		
		return result;
	}
	public Date getDateFromMillis(long millis) {
		SimpleDateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
		Date date = new Date(millis);
		formatter.format(date);
		return date;
	}
	public File getPdfIzvestaj(Date odDatum, Date doDatum, long racunId, long bankaId, long klijentId, int tipIzvestaja) throws ParseException, IOException {
		
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
			racuniBankeIzvestaji = this.getAllIzvestajBankaRacuniDTOs(bankaId,klijentId);
		}
		
		Map<String,Object> map = this.getDataSourceAndFillReport(dnevnoStanjeIzvestaji,racuniBankeIzvestaji,f,klijentId);
		String fileLocation = this.exportReportToPdfFileAndGetFileLocation(map);
		if(fileLocation.equals(GRESKA)) //GRESKA je konstanta
			return null;
		
		//znaci da je putanja fajla validna i ovde se returnuje prethodno kreirani pdf fajl
		File pdfFile = new File(fileLocation);
		return pdfFile;
		
		
	}
	
	@Transactional
	public void exportMedjubankarskiPrenos(MedjubankarskiPrenos mp) {
		try {
			String resourceDirectory = Paths.get("src","main","resources").toAbsolutePath().toString();
			StringBuilder outFile = new StringBuilder();
			outFile.append(resourceDirectory);
			outFile.append("\\medjubankarskiXml");
			outFile.append("\\mprenos_");
			outFile.append(Long.toString(mp.getId()));
			outFile.append(".xml");
			FileOutputStream fos = new FileOutputStream(new File(outFile.toString()));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(mp);
			encoder.close();
			fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional
	public void exportNalog(Nalog nalog) {
		try {
			String resourceDirectory = Paths.get("src","main","resources").toAbsolutePath().toString();
			StringBuilder outFile = new StringBuilder();
			outFile.append(resourceDirectory);
			outFile.append("\\nalogXml");
			outFile.append("\\nalog_");
			outFile.append(Long.toString(nalog.getId()));
			outFile.append(".xml");
			FileOutputStream fos = new FileOutputStream(new File(outFile.toString()));
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(nalog);
			encoder.close();
			fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	
	@Transactional
	public Nalog importNalog(String fileName) {
		Nalog nalog = null;
		try {
			
			String resourceDirectory = Paths.get("src","main","resources").toAbsolutePath().toString();
			StringBuilder outFile = new StringBuilder();
			outFile.append(resourceDirectory);
			outFile.append("\\nalogXml\\");
			FileInputStream fis = null;
			XMLDecoder decoder = null;
			
			outFile.append(fileName);
			fis = new FileInputStream(new File(outFile.toString()));
			decoder = new XMLDecoder(fis);
			nalog = (Nalog) decoder.readObject();	
			decoder.close();
			fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return nalog;
	}
	
	
}
