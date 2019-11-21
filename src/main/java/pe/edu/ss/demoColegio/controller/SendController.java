package pe.edu.ss.demoColegio.controller;


import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.ss.demoColegio.sendMail;
import pe.edu.ss.demoColegio.model.entity.Alumno;
import pe.edu.ss.demoColegio.model.entity.Matricula;

@RestController
public class SendController {
	
	@Autowired
	private sendMail smtpMailSender;
	
	//@Autowired
	Alumno alumno=new Alumno();
	
	Matricula matricula = new Matricula();

	@RequestMapping("send-mail")
	public void sendMail() throws MessagingException {
		
		smtpMailSender.send("dverdeguerv@gmail.com", "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es N00159324");
		smtpMailSender.send("betofavio29@gmail.com", "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es N00159324");
		smtpMailSender.send("tracyrosarioalvarez@gmail.com", "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es N00159324");
		smtpMailSender.send("enrikerojas93@gmail.com", "Matricula Registrada", "Estudiante, te matriculaste en el colegio Semillitas del saber, tu codigo de estudiante es N00159324");

	}
	

}
