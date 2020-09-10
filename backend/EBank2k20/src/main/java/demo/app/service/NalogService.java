package demo.app.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import demo.app.entity.Nalog;
import demo.app.repository.NalogRepository;
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
	
	public String exportReport() throws ParseException, FileNotFoundException, JRException {
		//String pdf = "pdf";
		String path = "D:\\jasperData";
		String strod = "2020-09-09";
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		Date odDatum = formatter.parse(strod);
		String strdo = "2020-09-11";
		DateFormat formatte2 = new SimpleDateFormat("yyyy-mm-dd");
		Date doDatum = formatte2.parse(strdo);
		//List<Nalog> nalozi = nr.naloziDnevnogStanja(odDatum, doDatum, 9);
		List<Nalog> nalozi = nr.findAll();
		if(nalozi.isEmpty()) {
			System.out.println("empty");
		}
		for(Nalog n : nalozi) {
			System.out.println(n.getDuznik());
		}
		//File file = ResourceUtils.getFile("classpath:dnevnoStanje.jrxml");
		File f = ResourceUtils.getFile("D:\\Workspaces\\poslovna\\src\\main\\resources\\dnevnoStanje.jrxml");
		//System.out.println(file.getAbsolutePath());
		JasperReport jasperReport = JasperCompileManager.compileReport(f.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(nalozi);
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("providedBy", "EBanking");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,dataSource);
		
		JasperExportManager.exportReportToPdfFile(jasperPrint, path+"\\dnevnoStanje.pdf");
		return "report generated in path: "  + path;
	}
	

}
