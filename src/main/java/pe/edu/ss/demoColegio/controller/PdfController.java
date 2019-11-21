package pe.edu.ss.demoColegio.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.service.AlumnoService;


@Controller
public class PdfController {
	@Autowired
	private AlumnoService alumnoService;

	@Autowired
	private ServletContext context;
	
	@GetMapping(value="createPdf")
	public void createPdf(HttpServletRequest request, HttpServletResponse response) {
		try {
		List<Alumno> alumnos = alumnoService.findAll();
		boolean isFlag = alumnoService.createPdf(alumnos, context, request, response);
		if(isFlag) {
			String fullPath = request.getServletContext().getRealPath("resources/reports/"+"alumnos"+".pdf");
			filedownload(fullPath,response,"alumnos.pdf");
		}
		}catch(Exception e){
			
		}
	}
	private void filedownload(String fullPath, HttpServletResponse response, String fileName) {
		File file = new File(fullPath);
		final int BUFFER_SIZE = 4096;
		if(file.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(file);
				String mimeType = context.getMimeType(fullPath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachment; filename="+fileName);
				OutputStream outputStream = response.getOutputStream();
				byte[] buffer  = new byte[BUFFER_SIZE];
				int bytesRead = -1;
				while((bytesRead = inputStream.read(buffer))!=-1) {
					outputStream.write(buffer,0,bytesRead);
				}
				inputStream.close();
				outputStream.close();
				file.delete();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}