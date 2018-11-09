package mx.com.test.pdf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.com.test.pdf.util.Utilerias;

@RestController
@RequestMapping(value = "/pdf")
public class PdfViewerController {
	
	@Autowired
	private Utilerias utilerias;
	
	@RequestMapping(value = "/viewer/{archivoPdf}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPDF1(
			@PathVariable("archivoPdf") String archivoPdf) {
		
		System.out.println("llega archivo: "+archivoPdf);
		archivoPdf = archivoPdf.concat(".pdf");

		HttpHeaders headers = new HttpHeaders();

	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    //String filename = "pdf1.pdf";

	    //para ver en browser
	    headers.add("Content-Disposition", "inline; filename=" + archivoPdf);

	    //para descargarse
	    //headers.add("Content-Disposition", "attachment; filename=" + "example.pdf");

	    headers.setContentDispositionFormData(archivoPdf, archivoPdf);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = null;
		try {
			response = new ResponseEntity<byte[]>(utilerias.obtenerPdf(archivoPdf), headers, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return response;
	}
	
	/*@RequestMapping(value = "asPdf", method = RequestMethod.GET)
	public String getViewAsPdf(Model model) {
	    // put stuff in your model
	    return "pdfView";
	}*/

}
