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
import demo.app.web.dto.IzvestajDnevnoStanjeRacunDTO;
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

	
	

}
