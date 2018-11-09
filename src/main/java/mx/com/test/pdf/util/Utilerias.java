package mx.com.test.pdf.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class Utilerias {

	public byte[] obtenerPdf(String _archivo) throws Exception {
		
		System.out.println("buscando archivo: "+_archivo);

		String PATH = System.getProperty(Constantes.USER_HOME)
				.concat(File.separator).concat(Constantes.CARPETA_CONFIG)
				.concat(File.separator).concat(_archivo);

		File pdfFile = new File(PATH);
		byte[] bytes = null;

		try (InputStream inputStream = new FileInputStream(pdfFile)) {

			try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

				int data;
				while ((data = inputStream.read()) >= 0) {
					outputStream.write(data);
				}

				bytes = outputStream.toByteArray();

			}

		}

		System.out.println("si salio el pdf");
		return bytes;

	}

}
