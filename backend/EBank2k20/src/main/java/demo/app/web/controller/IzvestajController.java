package demo.app.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.app.service.IzvestajService;

@RestController
@CrossOrigin(origins ="*",allowedHeaders = "*")
@RequestMapping(value="api/izvestaj")
public class IzvestajController {

	@Autowired
	IzvestajService is;
	
	@RequestMapping(value="/download/{odStr}/{doStr}/{racunId}/{bankaId}/{tipIzvestaja}",method = RequestMethod.GET)
	public ResponseEntity<?> downloadIzvestaj(@PathVariable String odStr,@PathVariable String doStr,
			@PathVariable long racunId,@PathVariable long bankaId,@PathVariable int tipIzvestaja) throws ParseException, IOException{
		Date odDatum = is.getDateFromString("09-Sep-2020,13:00:14 PM"); //ovde ide odStr
		Date doDatum = is.getDateFromString("12-Sep-2020,13:00:14 PM"); //ovde ide doStr
		File pdf = is.getPdfIzvestaj(odDatum, doDatum, racunId, bankaId, tipIzvestaja); //tipIzvestaja je da znamo da li je dnevnostanje izvestaj ili stanje racuna za banku izvestaj
		if(pdf != null) {
			Path filePath = pdf.toPath();
			String mimeType = Files.probeContentType(filePath);
			URI uri = pdf.toURI();
			byte[] fileAsByteArray = Files.readAllBytes(Paths.get(uri));

			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType(mimeType))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+pdf.getName()+"\"")
					.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*")
					.body(new ByteArrayResource(fileAsByteArray));
		}else
			return new ResponseEntity<>("Greska prilikom preuzimanja fajla!",HttpStatus.NOT_FOUND);
		
	}
}
