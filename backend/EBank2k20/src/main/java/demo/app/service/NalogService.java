package demo.app.service;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import demo.app.entity.DnevnoStanje;
import demo.app.entity.Nalog;
import demo.app.repository.NalogRepository;
import demo.app.web.dto.RacunIzvestajDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
@Transactional(readOnly = true)
public class NalogService  implements NalogServiceInterface, NalogDTOServiceInterface {

	@Autowired
	NalogRepository nr;
	
	@Autowired
	DnevnoStanjeService dss;
	
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
	public List<Nalog> naloziDnevnogStanja(Date odDatum, Date doDatum, long racunId) {
		return nr.naloziDnevnogStanja(odDatum, doDatum, racunId);
	}
	
	public void exportReport(){
		List<DnevnoStanje> dnevnoStanje = dss.findAll();
		List<RacunIzvestajDTO> izvestaji = new ArrayList<RacunIzvestajDTO>();
		RacunIzvestajDTO iz = new RacunIzvestajDTO();
		izvestaji.add(iz);
		for(DnevnoStanje ds : dnevnoStanje) {
			for(Nalog n : ds.getNalozi()) {
				iz = new RacunIzvestajDTO();
				iz.setBrojIzvoda(ds.getBrojIzvoda());
				iz.setDatumPrometa(ds.getDatumPrometa());
				iz.setNovoStanje(ds.getNovoStanje());
				iz.setPrethodnoStanje(ds.getPrethodnoStanje());
				iz.setIznos(n.getIznos());
				iz.setDuznik(n.getDuznik());
				iz.setPrimaoc(n.getPrimaoc());
				iz.setSvrhaPlacanja(n.getSvrhaPlacanja());
				iz.setVrstaPlacanja(n.getVrstaPlacanja());
				iz.setValuta(n.getValuta().getSifra());
				//System.out.println(iz.getBrojIzvoda());
				izvestaji.add(iz);
			}
		}
		System.out.println("dnevnoStanje.size()" +dnevnoStanje.size());
		System.out.println("izvestaji.size()" +izvestaji.size());
		//izvestaji.remove(1);
		for(RacunIzvestajDTO r: izvestaji)
			System.out.println(r.getBrojIzvoda());
		//JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parameterMap, new JREmptyDataSource());
		String path = "D:\\Stuff\\Programming\\jasperData";
		
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:izvestajRacuna.jrxml");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			System.out.println("file = resource utils");
		}
		//File f = ResourceUtils.getFile("D:\\Workspaces\\poslovna\\src\\main\\resources\\dnevnoStanje.jasper");
		//System.out.println(file.getAbsolutePath());
		JasperReport jasperReport = null;
		try {
			jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		} catch (JRException e) {
			
			e.printStackTrace();
			System.out.println("jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());");
		}
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(izvestaji);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("CollectionBeanParam", dataSource);
		JasperPrint jasperPrint = null;
		try {
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource)");
		}
		
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\izvestaj.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("JasperExportManager.exportReportToPdfFile(jasperPrint, path+\"\\\\dnevnoStanje.pdf\");");
		}
		
	}
	
	

}
