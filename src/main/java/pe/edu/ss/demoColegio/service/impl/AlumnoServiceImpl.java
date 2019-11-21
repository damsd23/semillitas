package pe.edu.ss.demoColegio.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.repository.AlumnoRepository;
import pe.edu.ss.demoColegio.service.AlumnoService;

@Service
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Alumno> findAll() throws Exception {
		
		return alumnoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Alumno> findById(String id) throws Exception {
		
		return alumnoRepository.findById(id);
	}

	@Transactional
	@Override
	public Alumno save(Alumno entity) throws Exception {
		
		return alumnoRepository.save(entity);
	}

	@Transactional
	@Override
	public Alumno update(Alumno entity) throws Exception {
		
		return alumnoRepository.save(entity);
	}

	@Transactional
	@Override
	public void deleteById(String id) throws Exception {
		
		alumnoRepository.deleteById(id);
	}

	@Transactional
	@Override
	public void deleteAll() throws Exception {
		
		alumnoRepository.deleteAll();
	
	}

	@Override
	public boolean createPdf(List<Alumno> alumnos, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		Document document = new Document(PageSize.A4, 15, 15, 45, 30);
	
		try {
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			boolean exists = new File(filePath).exists();
			if (!exists) {
				new File(filePath).mkdirs();
			}

			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file + "/" + "alumnos" + ".pdf"));
			document.open();
			Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);

			Paragraph paragraph = new Paragraph("Reporte de Matriculados", mainFont);
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);

			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);

			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

			float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f };
			table.setWidths(columnWidths);

			PdfPCell codigo = new PdfPCell(new Paragraph("Codigo", tableHeader));
			codigo.setBorderColor(BaseColor.BLACK);
			codigo.setPaddingLeft(10);
			codigo.setHorizontalAlignment(Element.ALIGN_CENTER);
			codigo.setVerticalAlignment(Element.ALIGN_CENTER);
			codigo.setBackgroundColor(BaseColor.GRAY);
			codigo.setExtraParagraphSpace(5f);
			table.addCell(codigo);

			PdfPCell DNI = new PdfPCell(new Paragraph("DNI", tableHeader));
			DNI.setBorderColor(BaseColor.BLACK);
			DNI.setPaddingLeft(10);
			DNI.setHorizontalAlignment(Element.ALIGN_CENTER);
			DNI.setVerticalAlignment(Element.ALIGN_CENTER);
			DNI.setBackgroundColor(BaseColor.GRAY);
			DNI.setExtraParagraphSpace(5f);
			table.addCell(DNI);

			PdfPCell nombre = new PdfPCell(new Paragraph("Nombre", tableHeader));
			nombre.setBorderColor(BaseColor.BLACK);
			nombre.setPaddingLeft(10);
			nombre.setHorizontalAlignment(Element.ALIGN_CENTER);
			nombre.setVerticalAlignment(Element.ALIGN_CENTER);
			nombre.setBackgroundColor(BaseColor.GRAY);
			nombre.setExtraParagraphSpace(5f);
			table.addCell(nombre);

			PdfPCell ApePat = new PdfPCell(new Paragraph("ApePat", tableHeader));
			ApePat.setBorderColor(BaseColor.BLACK);
			ApePat.setPaddingLeft(10);
			ApePat.setHorizontalAlignment(Element.ALIGN_CENTER);
			ApePat.setVerticalAlignment(Element.ALIGN_CENTER);
			ApePat.setBackgroundColor(BaseColor.GRAY);
			ApePat.setExtraParagraphSpace(5f);
			table.addCell(ApePat);

			PdfPCell ApeMat = new PdfPCell(new Paragraph("ApeMat", tableHeader));
			ApeMat.setBorderColor(BaseColor.BLACK);
			ApeMat.setPaddingLeft(10);
			ApeMat.setHorizontalAlignment(Element.ALIGN_CENTER);
			ApeMat.setVerticalAlignment(Element.ALIGN_CENTER);
			ApeMat.setBackgroundColor(BaseColor.GRAY);
			ApeMat.setExtraParagraphSpace(5f);
			table.addCell(ApeMat);
			
			PdfPCell fechaIngreso = new PdfPCell(new Paragraph("FechaIngreso", tableHeader));
			fechaIngreso.setBorderColor(BaseColor.BLACK);
			fechaIngreso.setPaddingLeft(10);
			fechaIngreso.setHorizontalAlignment(Element.ALIGN_CENTER);
			fechaIngreso.setVerticalAlignment(Element.ALIGN_CENTER);
			fechaIngreso.setBackgroundColor(BaseColor.GRAY);
			fechaIngreso.setExtraParagraphSpace(5f);
			table.addCell(fechaIngreso);
			
			for (Alumno alumno : alumnos) {
				
				PdfPCell codigoValue = new PdfPCell(new Paragraph(alumno.getCodigo() + "", tableBody));
				codigoValue.setBorderColor(BaseColor.BLACK);
				codigoValue.setPaddingLeft(10);
				codigoValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				codigoValue.setVerticalAlignment(Element.ALIGN_CENTER);
				codigoValue.setBackgroundColor(BaseColor.WHITE);
				codigoValue.setExtraParagraphSpace(5f);
				table.addCell(codigoValue);

				PdfPCell DNIValue = new PdfPCell(new Paragraph(alumno.getDNI(), tableBody));
				DNIValue.setBorderColor(BaseColor.BLACK);
				DNIValue.setPaddingLeft(10);
				DNIValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				DNIValue.setVerticalAlignment(Element.ALIGN_CENTER);
				DNIValue.setBackgroundColor(BaseColor.WHITE);
				DNIValue.setExtraParagraphSpace(5f);
				table.addCell(DNIValue);

				PdfPCell nombreValue = new PdfPCell(new Paragraph(alumno.getNombre(), tableBody));
				nombreValue.setBorderColor(BaseColor.BLACK);
				nombreValue.setPaddingLeft(10);
				nombreValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				nombreValue.setVerticalAlignment(Element.ALIGN_CENTER);
				nombreValue.setBackgroundColor(BaseColor.WHITE);
				nombreValue.setExtraParagraphSpace(5f);
				table.addCell(nombreValue);

				PdfPCell ApePatValue = new PdfPCell(new Paragraph(alumno.getApePat() + "", tableBody));
				ApePatValue.setBorderColor(BaseColor.BLACK);
				ApePatValue.setPaddingLeft(10);
				ApePatValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ApePatValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ApePatValue.setBackgroundColor(BaseColor.WHITE);
				ApePatValue.setExtraParagraphSpace(5f);
				table.addCell(ApePatValue);

				PdfPCell ApeMatValue = new PdfPCell(new Paragraph(alumno.getApeMat() + "", tableBody));
				ApeMatValue.setBorderColor(BaseColor.BLACK);
				ApeMatValue.setPaddingLeft(10);
				ApeMatValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				ApeMatValue.setVerticalAlignment(Element.ALIGN_CENTER);
				ApeMatValue.setBackgroundColor(BaseColor.WHITE);
				ApeMatValue.setExtraParagraphSpace(5f);
				table.addCell(ApeMatValue);
				
				PdfPCell fechaIngresoValue = new PdfPCell(new Paragraph(alumno.getFechaIngreso() + "", tableBody));
				fechaIngresoValue.setBorderColor(BaseColor.BLACK);
				fechaIngresoValue.setPaddingLeft(10);
				fechaIngresoValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				fechaIngresoValue.setVerticalAlignment(Element.ALIGN_CENTER);
				fechaIngresoValue.setBackgroundColor(BaseColor.WHITE);
				fechaIngresoValue.setExtraParagraphSpace(5f);
				table.addCell(fechaIngresoValue);
			}
			
			document.add(table);
			document.close();
			return true;

		} catch (Exception e) {
			System.out.println("Error");
			return false;
		}
	}

}
