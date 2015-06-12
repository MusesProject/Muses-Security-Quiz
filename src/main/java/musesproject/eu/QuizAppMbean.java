package musesproject.eu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.PieChartModel;



public class QuizAppMbean {
	
	private  List<Question> sampleQuestions = new ArrayList<Question>();
	
	private List<Quizz> newQuizz = new ArrayList<Quizz>();
	
	private Quizz quizzChosen = new Quizz();

	private QuizzResult resultQuizzChosen = new QuizzResult();
	
	private Employee employee;
	
	private QuestionOption selectedOption;
	
	private List<ListQuestions> quizz = new ArrayList<ListQuestions> ();
	
	private Question question = new Question();

	private int response;
	
	private QuizzResult results = new QuizzResult();
	
	private String step = "1";
	
	private ArrayList<String> questionslist = new ArrayList<String>();
	
	private ArrayList<Integer> responseslist = new ArrayList<Integer>();

	private ArrayList<Quizz> mylists = new ArrayList<Quizz>();
	
	private Boolean quizzDone;
	
	private BarChartModel barModel;
	 
	private PieChartModel pieModel1;
	 
	private HorizontalBarChartModel horizontalBarModel;
	 
	private int wrongAnswer = 0;
	 
	private int correctAnswer = 0;
	
	private boolean  step1Done = false;
	
	private boolean  step2Done = false;

	private boolean  step3Done = false;

	private boolean  step4Done = false;

	private boolean  step5Done = false;

	private boolean  step6Done = false;

	private boolean  step7Done = false;

	private boolean  step8Done = false;

	private boolean  step9Done = false;

	private boolean  step10Done = false;


		
	 public boolean isStep1Done() {
		return step1Done;
	}


	public void setStep1Done(boolean step1Done) {
		this.step1Done = step1Done;
	}


	public boolean isStep2Done() {
		return step2Done;
	}


	public void setStep2Done(boolean step2Done) {
		this.step2Done = step2Done;
	}


	public boolean isStep3Done() {
		return step3Done;
	}


	public void setStep3Done(boolean step3Done) {
		this.step3Done = step3Done;
	}


	public boolean isStep4Done() {
		return step4Done;
	}


	public void setStep4Done(boolean step4Done) {
		this.step4Done = step4Done;
	}


	public boolean isStep5Done() {
		return step5Done;
	}


	public void setStep5Done(boolean step5Done) {
		this.step5Done = step5Done;
	}


	public boolean isStep6Done() {
		return step6Done;
	}


	public void setStep6Done(boolean step6Done) {
		this.step6Done = step6Done;
	}


	public boolean isStep7Done() {
		return step7Done;
	}


	public void setStep7Done(boolean step7Done) {
		this.step7Done = step7Done;
	}


	public boolean isStep8Done() {
		return step8Done;
	}


	public void setStep8Done(boolean step8Done) {
		this.step8Done = step8Done;
	}


	public boolean isStep9Done() {
		return step9Done;
	}


	public void setStep9Done(boolean step9Done) {
		this.step9Done = step9Done;
	}


	public boolean isStep10Done() {
		return step10Done;
	}


	public void setStep10Done(boolean step10Done) {
		this.step10Done = step10Done;
	}


	/**
	 * @return the wrongAnswer
	 */
	public int getWrongAnswer() {
		return wrongAnswer;
	}


	/**
	 * @param wrongAnswer the wrongAnswer to set
	 */
	public void setWrongAnswer(int wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}	 
	
	
	/**
	 * @return the correctAnswer
	 */
	public int getCorrectAnswer() {
		return correctAnswer;
	}


	/**
	 * @param correctAnswer the correctAnswer to set
	 */
	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}


	/**
	 * @return the mylists
	 */
	public ArrayList<Quizz> getMylists() {
		return mylists;
	}


	/**
	 * @param mylists the mylists to set
	 */
	public void setMylists(ArrayList<Quizz> mylists) {
		this.mylists = mylists;
	}

	private boolean showRes;
	public static int totalCorrAnswer;
	public static int totalWrongAnswer;
	
	public static int totalfinalCorrAnswer;
	public static int totalFinalWrongAnswer;
	
	public static double averageCorrAnswer;
	public static double averageWrongAnswer;



	int counter = 0;

	public void init() {

		sampleQuestions.clear();
		totalCorrAnswer = 0;
	}
	
	
	 public double averageWrongAnswers(){
	    	List<QuizzResult> listQuizzResult = QuizzResult.findAllQuizzResults();
	    	int wrongAnswers = 0;
	    	
	    	for (int i = 0; i < listQuizzResult.size(); i++) {
	    		wrongAnswers = wrongAnswers + listQuizzResult.get(i).getWrongAnswers();
			}
	    	//System.out.println("sum: "+(wrongAnswers/listQuizzResult.size()));
	    	
	    	double val = (double)wrongAnswers/(double)listQuizzResult.size();
			val = val*10;
			val = Math.round(val);
			val = val /10;
	    	return val;
	    	
	    }
	    
	    public double averageCorrectAnswers(){
	    	List<QuizzResult> listQuizzResult = QuizzResult.findAllQuizzResults();
	    	int correctAnswers = 0;
	    	
	    	for (int i = 0; i < listQuizzResult.size(); i++) {
	    		correctAnswers = correctAnswers + listQuizzResult.get(i).getCorrectAnswers();
			}
	    	//System.out.println("sum: "+correctAnswers/listQuizzResult.size());
	    	
	    	double val = (double)correctAnswers/(double)listQuizzResult.size();
			val = val*10;
			val = Math.round(val);
			val = val /10;
	    	return val;
	    	
	    }
	
	
	public Employee findEmployeebyEmail(String email){
		
		List<Employee> list = Employee.findAllEmployees();
		for (int i = 0; i < list.size(); i++) {
			if (email.equalsIgnoreCase(list.get(i).getEmail()))
				return list.get(i);	
		}
	
	return null;
	}
	
	@PostConstruct
	public void initQuizzes(){
		
		totalCorrAnswer = 0;
		
		totalWrongAnswer = 0;
		
		averageCorrAnswer = averageCorrectAnswers();
		
		averageWrongAnswer = averageWrongAnswers();
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getUserPrincipal().getName();
		
		employee = findEmployeebyEmail(facesContext.getExternalContext().getUserPrincipal().getName());	

		newQuizz = Quizz.findAllQuizzes();
		//newQuizz.add(quizz1);
	
		/*
		 QuizzQuestion question1 = new QuizzQuestion();
	        
		 question1.setQuestionText("XXXXX OOODN");
	     
	     
		 question1.persist();
	     QuizzResponse qr = new QuizzResponse();
	     qr.setText("text");
	     qr.setOptionsresponse(question1);
	     
	     QuizzResponse qr1 = new QuizzResponse();
	     qr1.setText("text");
	     qr1.setOptionsresponse(question1);
	     
	     QuizzResponse qr2 = new QuizzResponse();
	     qr2.setText("text");
	     qr2.setOptionsresponse(question1);
	     
	     qr.persist();
	     qr1.persist();
	     qr2.persist();
	     
	    
		
		QuizzQuestion q = new QuizzQuestion();
		
		quizzChosen.getListQuestions().size();
	     
		quizzChosen.getListQuestions().get(0).getListresponses().size() */
		
		/*QuizzQuestion q = QuizzQuestion.findQuizzQuestion((long)2401);
		q.setQuestionText("TIene que crear una contraseña nueva. Cómo lo hace?");
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Elijo una palabra difícil de olvidar, por ejemplo mi propio nombre.");
		answers.add("Combino una palabra fácil de recordar ´con un número, por ejemplo el nombre de mi calle y mi distrito postal. ");
		answers.add("Combino una palabra sin sentido con un número o un carácter especial. ");
		answers.add("Elijo una palabra sin sentido que tenga 8 caracteres como mínimo, incluyendo mayúsculas, minúsculas, números y caracteres especiales. ");
		q.setAnswers(answers);
		q.setResponse(3);

		q.merge();
		
		QuizzQuestion q11 = QuizzQuestion.findQuizzQuestion((long)2411);
		q11.setQuestionText("Va a entrar en su oficina y dos desconocidos van detrás suyo. Qué hace?");
		ArrayList<String> answers11 = new ArrayList<String>();
		answers11.add("Les saludo educadamente y les dejo pasar primero, sin preguntar nada.");
		answers11.add("Les pregunto educadamente donde van y les indico el camino");
		answers11.add("Les paro, les pregunto quiénes son y de donde vienen, y llamo a la persona a la que dicen que van a ver para confirmar que dicen la verdad.");
		answers11.add("Les pregunto a quién van a ver y les acompaño hasta donde está esta persona, o los dejo en manos de seguridad para que les acompañe. ");
		q11.setAnswers(answers11);
		q11.setResponse(3);

		q11.merge();
		
		QuizzQuestion q8 = QuizzQuestion.findQuizzQuestion((long)2421);
		q8.setQuestionText("Está trabajando en la cafetería del aeropuerto mientras espera a que salga su vuelo. Tiene que ir al mostrador a pedir un café… qué hace con su portátil?");
		ArrayList<String> answers8 = new ArrayList<String>();
		answers8.add("Me lo llevo al mostrador.");
		answers8.add("Le pido a la persona de la mesa de al lado que me lo vigile.");
		answers8.add("No lo pierdo de vista mientras voy al mostrador, pido el café y vuelvo.");
		answers8.add("Lo meto en su bolsa y Ia pongo debajo de la silla.");
		q8.setAnswers(answers8);
		q8.setResponse(0);

		q8.merge();
	
		QuizzQuestion q1 = QuizzQuestion.findQuizzQuestion((long)2431);
		q1.setQuestionText("Cuál es la forma más segura de guardar sus archivos de trabajo?");
		ArrayList<String> answers1 = new ArrayList<String>();
		answers1.add("Mantengo la información en el ordenador y copio los ficheros más importantes en una memoria USB. ");
		answers1.add("Guardo toda mi información en el servidor de mi empresa.");
		answers1.add("Guardo toda mi información en el servidor de mi empresa pero además tengo una copia de seguridad con algún servicio en la  nube.");
		answers1.add("Guardo toda mi información en el servidor de mi empresa y me envío los archivos más importantes a mi dirección de correo personal. ");
		q1.setAnswers(answers1);
		q1.setResponse(1);

		q1.merge();
		
		QuizzQuestion q2 = QuizzQuestion.findQuizzQuestion((long)2441);
		q2.setQuestionText("Recibe en su dirección de correo de trabajo un mensaje que parece un spam (correo no deseado). Qué hace con él?");
		ArrayList<String> answers2 = new ArrayList<String>();
		answers2.add("Accedo a los enlaces indicados en el mensaje para comprobar si es realmente un spam.");
		answers2.add("Contesto pidiendo que me eliminen de su lista de correos.");
		answers2.add("Marco el mensaje como spam y lo envío a los informáticos de mi empresa.");
		answers2.add("Sigo las instrucciones para borrar mi dirección de su lista de correos. ");
		q2.setAnswers(answers2);
		q2.setResponse(2);

		q2.merge();
		
		QuizzQuestion q3 = QuizzQuestion.findQuizzQuestion((long)2451);
		q3.setQuestionText("Recibe un correo de un colega de trabajo, con un fichero adjunto al que no se hace referencia en el texto del mensaje. Qué hace?");
		ArrayList<String> answers3 = new ArrayList<String>();
		answers3.add("Contacto a mi colega y le pregunto sobre el archivo. Si lo ha adjuntado conscientemente al mensaje, y es algo que se supone tengo que ver, lo abro.");
		answers3.add("Abro el archivo para ver su contenido.");
		answers3.add("Ignorarlo - si se supone que debo conocer el contenido del archivo, seguro que mi colega ya me dirá algo.");
		answers3.add("Paso el correo a los informáticos de mi empresa y lo borro de mi ordenador.");
		q3.setAnswers(answers3);
		q3.setResponse(0);

		q3.merge();
		
		
		QuizzQuestion q4 = QuizzQuestion.findQuizzQuestion((long)2461);
		q4.setQuestionText("Cómo puede estar seguro de que solamente se conecta a Internet a través de redes seguras? ");
		ArrayList<String> answers4 = new ArrayList<String>();
		answers4.add("Si se necesita un código WPA o WPA2 para acceder a una red, es que está encriptada y por lo tanto es segura. Me conecto.");
		answers4.add("Solamente me conecto a una red si estoy seguro de que tiene un método fuerte de autenticación (WPA, WPA2), pero además solamente uso sitios seguros identificados por el prefijo https (y no por http), o me conecto a través de VPN.");
		answers4.add("Solamente accedo a Internet a través de la red de mi empresa, lo demás es demasiado arriesgado.");
		answers4.add("Confío en que mi software de protección funcione correctamente, y conecto mi portátil a cualquier red que encuentre disponible cuando me haga falta.");
		q4.setAnswers(answers4);
		q4.setResponse(1);

		q4.merge();
		
		
		QuizzQuestion q5 = QuizzQuestion.findQuizzQuestion((long)2471);
		q5.setQuestionText("Cuándo debe encriptar sus datos para protegerlos?");
		ArrayList<String> answers5 = new ArrayList<String>();
		answers5.add("Cuando se trata de información confidencial, solamente la guardo y envío en formato encriptado. ");
		answers5.add("Encripto la información confidencial cuando voy a transmitirla de alguna forma. ");
		answers5.add("Encripto toda mi información para asegurarme de que está protegida. ");
		answers5.add("No es necesario encriptar la información siempre que se usen conexiones seguras y se envíe solamente a personas autorizadas a recibirla.");
		q5.setAnswers(answers5);
		q5.setResponse(0);

		q5.merge();
		
		QuizzQuestion q6 = QuizzQuestion.findQuizzQuestion((long)2481);
		q6.setQuestionText("Cuál es la forma más segura de navegar por Internet? ");
		ArrayList<String> answers6 = new ArrayList<String>();
		answers6.add("Compruebo que mi red esté encriptada y que mi software de protección esté actualizado, y uso el navegador que tenga las mejores calificaciones de seguridad.");
		answers6.add("Compruebo que las opciones de privacidad y seguridad de mi navegador sean las correctas, me suscribo a alertas para estar actualizado sobre temas de seguridad, tomo precauciones antes de instalar plug-ins y extensiones, instalo plug-ins de seguridad y mantengo actualizado mi navegador.");
		answers6.add("Uso un software que protege mi identidad y borra mis huellas.");
		answers6.add("Solamente visito webs seguras.");
		q6.setAnswers(answers6);
		q6.setResponse(1);

		q6.merge();
		
		
		
		QuizzQuestion q7 = QuizzQuestion.findQuizzQuestion((long)2491);
		q7.setQuestionText("Tengo que tomar precauciones específicas para proteger mi móvil de empresa de ataques de seguridad?");
		ArrayList<String> answers7 = new ArrayList<String>();
		answers7.add("No, siempre que tenga activados un PIN, contraseña o patrón de bloqueo.");
		answers7.add("Mientras no pierda el móvil, no hay riesgo de seguridad");
		answers7.add("Instalo software de seguridad, tengo cuidado con lo que me descargo, no contesto los correos o mensajes de texto sospechosos, compruebo que tengo todo el software actualizado, encripto los datos, hago copias de seguridad con regularidad, y uso el bloqueo de pantalla.");
		answers7.add("Compruebo que tanto el Sistema operativo como las apps estén actualizadas para que no haya agujeros de seguridad, y uso el bloqueo de pantalla. ");
		q7.setAnswers(answers7);
		q7.setResponse(2);

		q7.merge();*/
		
		
		
		
		/*QuizzQuestion q9 = QuizzQuestion.findQuizzQuestion((long)2401);
		q9.setQuestionText("A business partner gives you an USB stick with data that you need to process. How do you access the data in a safe way?");
		ArrayList<String> answers9 = new ArrayList<String>();
		answers9.add("I just plug the USB stick in and copy the data – the USB stick of a business partner can be expected to be safe.");
		answers9.add("I apologize to the business partner and explain that I need the data via a different channel as it is not safe to copy it from an USB device");
		answers9.add("I take the USB drive to out IT department and have them check it. If they do not detect anything problematic I copy the data.");
		answers9.add("I plug the USB stick in and check it with my anti-virus software. If it is pronounced clean I copy the data.");
		q9.setAnswers(answers6);
		q9.setResponse(1);

		q9.merge();*/
		
	}
	
	
	public boolean alreadyDone(){
		FacesContext facesContext = FacesContext.getCurrentInstance();

		Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();
		String description = params.get("description");
		for (int i = 0; i < employee.getMyQuizzes().size(); i++) {
			if(employee.getMyQuizzes().get(i).getDescription().equalsIgnoreCase(description)){
				
				quizzDone = true;
				return true;
				
			}
			
		}
		quizzDone = false;
		return false;
		
	}
	
	public void check (){
		
    
	}
	
	public String lunchquizz(){
		

		return "step1";
	}
	public void start(){
		
		
		totalCorrAnswer = 0;
		
		totalWrongAnswer = 0;
		
		step1Done = false;
		step2Done = false;
		step3Done = false;
		step4Done = false;
		step5Done = false;
		step6Done = false;
		step7Done = false;
		step8Done = false;
		step9Done = false;
		step10Done = false;
		
	}
	public String moveToNextQuestion() {
		
		System.out.println("size: "+sampleQuestions.size());
		
		System.out.println("response: "+response);
		
		System.out.println("step: "+step);
		
		//results.setDescription(quizzChosen.getDescription());
		
		/*
		totalfinalCorrAnswer = 0;
		
		totalFinalWrongAnswer = 0;
		*/
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getUserPrincipal().getName();
		
		employee = findEmployeebyEmail(facesContext.getExternalContext().getUserPrincipal().getName());	
		
		

		if(step.equalsIgnoreCase("1")){
			
			if(step1Done!=true){
				
				questionslist.add(quizzChosen.getListQuestions().get(0).getQuestionText());
				quizzChosen.getListQuestions().get(0).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(0).getResponse()) {
					totalCorrAnswer++;
				}
	
				step1Done = true;
	
				return "step2";
			}else{
				return "step2";
			}
		}

		if(step.equalsIgnoreCase("2")){
			
			if(step2Done!=true){
			
				questionslist.add(quizzChosen.getListQuestions().get(1).getQuestionText());
				quizzChosen.getListQuestions().get(1).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(1).getResponse()) {
					totalCorrAnswer++;
				}
				System.out.println("bool2: "+step2Done);
	
				step2Done = true;
	
				return "step3";
			}else{
			    
		        return "step3";

			}
		}

		if(step.equalsIgnoreCase("3")){
			
			if(step3Done!=true){

			
				questionslist.add(quizzChosen.getListQuestions().get(2).getQuestionText());
				quizzChosen.getListQuestions().get(2).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(2).getResponse()) {
					totalCorrAnswer++;
				}
				System.out.println("bool3: "+step3Done );
	
				step3Done = true;
	
				return "step4";
			}else{
				return "step4";

			}
		}

		if(step.equalsIgnoreCase("4")){
			
			if(step4Done!=true){

				questionslist.add(quizzChosen.getListQuestions().get(3).getQuestionText());
				quizzChosen.getListQuestions().get(3).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(3).getResponse()) {
					totalCorrAnswer++;
				}
				System.out.println("bool4: "+step4Done );
				step4Done = true;
	
				return "step5";
			}else{
				return "step5";

			}
		}
		if(step.equalsIgnoreCase("5")){
			
			if(step5Done!=true){

				questionslist.add(quizzChosen.getListQuestions().get(4).getQuestionText());
				quizzChosen.getListQuestions().get(4).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(4).getResponse()) {
					totalCorrAnswer++;
				}
				step5Done = true;
	
				return "step6";
			}else{
				return "step6";

			}
		}
		if(step.equalsIgnoreCase("6")){
			
			if(step6Done!=true){
			
				questionslist.add(quizzChosen.getListQuestions().get(5).getQuestionText());
				quizzChosen.getListQuestions().get(5).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(5).getResponse()) {
					totalCorrAnswer++;
				}
				step6Done = true;
	
				return "step7";
			}else{
				return "step7";

			}
		}
		if(step.equalsIgnoreCase("7")){
			
			if(step7Done!=true){

				questionslist.add(quizzChosen.getListQuestions().get(6).getQuestionText());
				quizzChosen.getListQuestions().get(6).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(6).getResponse()) {
					totalCorrAnswer++;
				}
				step7Done = true;
				
	
				return "step8";
			}else{
				return "step8";

			}
		}
		if(step.equalsIgnoreCase("8")){
			
			if(step8Done!=true){

			questionslist.add(quizzChosen.getListQuestions().get(7).getQuestionText());
			quizzChosen.getListQuestions().get(7).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(7).getResponse()) {
				totalCorrAnswer++;
			}
			step8Done = true;

			return "step9";
			}else{
				return "step9";
			}
		}
		if(step.equalsIgnoreCase("9")){
			
			if(step9Done!=true){
			
			questionslist.add(quizzChosen.getListQuestions().get(8).getQuestionText());
			quizzChosen.getListQuestions().get(8).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(8).getResponse()) {
				totalCorrAnswer++;
			}
			step9Done = true;

			return "step10";
			}else{
				return "step10";
			}
		}
		if(step.equalsIgnoreCase("10")){
			
			
			if(step10Done !=true){

				questionslist.add(quizzChosen.getListQuestions().get(9).getQuestionText());
				quizzChosen.getListQuestions().get(9).setYourresponse(response);
				responseslist.add(response);
				if (response == quizzChosen.getListQuestions().get(9).getResponse()) {
					totalCorrAnswer++;
				}
				step10Done = true;
				
				//results.setQuestions(questionslist);
				results.setResponses(responseslist);
				
				totalWrongAnswer = quizzChosen.getListQuestions().size() - totalCorrAnswer;
				
				
				totalfinalCorrAnswer = totalCorrAnswer;
				
				totalFinalWrongAnswer = totalWrongAnswer;
				
				
				totalCorrAnswer = 0;
				
				totalWrongAnswer = 0; 
				
				//quizzChosen.persist();
				
				List<Quizz> set = new ArrayList<Quizz>();
				 	 	   
		        if(employee.getMyQuizzes()!=null){
	 	 	    	
	 	 	    	set = employee.getMyQuizzes();
	 	 	    	
	
	 	 	    }
		
		
	 	        //list.add(quizzChosen1);
				set.add(quizzChosen);
				employee.setMyQuizzes(set);
				
				//employee.merge();
				
				mylists = new ArrayList<Quizz>(employee.getMyQuizzes());	
			
				
				
				
				//results.persist();
				//quizzChosen.persist();
				//quizzChosen.setResultQuiz(resultQuiz);
				
				results.setQuizz(quizzChosen);
				results.setEmployee(employee);
				results.setCorrectAnswers(totalfinalCorrAnswer);
				results.setWrongAnswers(totalFinalWrongAnswer);
				results.setResponses(responseslist);
				QuizzResult results1 = results;
				
				
				
				Date now = new Date();
				results1.setDate(now);
				results1.persist();
				List<QuizzResult> resultQuiz = new ArrayList<QuizzResult>();
				if(employee.getMyQuizResults()!=null){
	 	 	    	
					resultQuiz = employee.getMyQuizResults();
	 	 	    	
	 	 	    }
				resultQuiz.add(results1);
	
				employee.setMyQuizResults(resultQuiz);
				employee.merge();
				//employee.merge();
	
				results = new QuizzResult();
				//quizzChosen = new Quizz();
				responseslist = new ArrayList<Integer>();
				
				averageCorrAnswer = averageCorrectAnswers();
				averageWrongAnswer = averageWrongAnswers();
				
				
				step10Done = true;
	
				return "result";
			}else{
				return "result";
			}
		}
		
		if(step.equalsIgnoreCase("final")){
			
			employee.getMyQuizzes().get(0).getDescription();
			
			
			
		}
		
		
		
		return " ";
	
	}

	

	
	
	
	public void clearQuizz(){
		
		sampleQuestions.clear();

		totalCorrAnswer = 0;
		
	}
	
	
	
	public String addQuestionnary(){
			 	       
	 			
		sampleQuestions.clear();
		totalCorrAnswer = 0;
		

		List<QuestionOption> q1Options = new ArrayList<QuestionOption>();

		q1Options.add(new QuestionOption("Data Encryption Standard", false));
		q1Options.add(new QuestionOption("security policy", true));
		q1Options.add(new QuestionOption("public key certificate", false));
		q1Options.add(new QuestionOption("access control list", false));
		q1Options.add(new QuestionOption("copyright", false));
		Question q1 = new Question(1, "This is a document that states in writing how a company plans to protect the company's physical and IT assets.", q1Options, null);
		sampleQuestions.add(q1);

		List<QuestionOption> q2Options = new ArrayList<QuestionOption>();
		q2Options.add(new QuestionOption("Buffer overflow", false));
		q2Options.add(new QuestionOption("Bastion host", false));
		q2Options.add(new QuestionOption("malware", true));
		q2Options.add(new QuestionOption("Ping Sweep", false));
		q2Options.add(new QuestionOption("Passphrase", false));
		Question q2 = new Question(1, "This is a program or file that is specifically developed for the purpose of doing harm.", q2Options, null);
		sampleQuestions.add(q2);

		List<QuestionOption> q3Options = new ArrayList<QuestionOption>();
		q3Options.add(new QuestionOption("Firewall", true));
		q3Options.add(new QuestionOption("Sandbox", false));
		q3Options.add(new QuestionOption("Rootkit", false));
		q3Options.add(new QuestionOption("Password cracker", false));
		q3Options.add(new QuestionOption("General protection fault", false));		
		Question q3 = new Question(1, "This is a set of related programs, usually located at a network gateway server, that protects the resources of a private network from other networks.", q3Options, null);
		sampleQuestions.add(q3);

		List<QuestionOption> q4Options = new ArrayList<QuestionOption>();
		q4Options.add(new QuestionOption("Instrusion detection", false));
		q4Options.add(new QuestionOption("Security Identifier", false));
		q4Options.add(new QuestionOption("Antigen", false));
		q4Options.add(new QuestionOption("Probe", true));
		q4Options.add(new QuestionOption("Antivirus software", false));
		Question q4 = new Question(1, "This is a class of programs that searches your hard drive and floppy disks for any known or potential viruses. ", q4Options, null);
		sampleQuestions.add(q4);
		
		List<QuestionOption> q5Options = new ArrayList<QuestionOption>();
		q5Options.add(new QuestionOption("Cyberterrorism", false));
		q5Options.add(new QuestionOption("Debugging", false));
		q5Options.add(new QuestionOption("Hijacking", false));
		q5Options.add(new QuestionOption("Nonrepudiation", false));
		q5Options.add(new QuestionOption("Social engineering", true));
		Question q5 = new Question(1, "In computer security, this describes a non-technical kind of intrusion that relies heavily on human interaction. It often involves tricking people into breaking their own security procedures. ", q5Options, null);
		sampleQuestions.add(q5);
		
		List<QuestionOption> q6Options = new ArrayList<QuestionOption>();
		q6Options.add(new QuestionOption("War dialer", false));
		q6Options.add(new QuestionOption("Spam trap", false));
		q6Options.add(new QuestionOption("Smurf", false));
		q6Options.add(new QuestionOption("Trojan horse", true));
		q6Options.add(new QuestionOption("Walled garden", false));
		Question q6 = new Question(1, "This is a program in which malicious or harmful code is contained inside apparently harmless programming or data.", q6Options, null);
		sampleQuestions.add(q6);
		
		List<QuestionOption> q7Options = new ArrayList<QuestionOption>();
		q7Options.add(new QuestionOption("Conditional access", false));
		q7Options.add(new QuestionOption("Anonymizer", false));
		q7Options.add(new QuestionOption("Bypass", false));
		q7Options.add(new QuestionOption("User profile", false));
		q7Options.add(new QuestionOption("Authentification", true));
		Question q7 = new Question(1, "This is the process of determining whether someone or something is, in fact, who or what it is declared to be.", q7Options, null);
		sampleQuestions.add(q7);
		
		List<QuestionOption> q8Options = new ArrayList<QuestionOption>();
		q8Options.add(new QuestionOption("Brute force cracking", false));
		q8Options.add(new QuestionOption("Tunneling", false));
		q8Options.add(new QuestionOption("Encryption", true));
		q8Options.add(new QuestionOption("Cipertext feedback", false));
		q8Options.add(new QuestionOption("Cloaking", false));
		Question q8 = new Question(1, "This is the conversion of data into a ciphertext that cannot be easily understood by unauthorized people.", q8Options, null);
		sampleQuestions.add(q8);
		
		List<QuestionOption> q9Options = new ArrayList<QuestionOption>();
		q9Options.add(new QuestionOption("Digital Signature", false));
		q9Options.add(new QuestionOption("Smart Card", false));
		q9Options.add(new QuestionOption("Public Key", false));
		q9Options.add(new QuestionOption("Password", true));
		q9Options.add(new QuestionOption("Signature File", false));
		Question q9 = new Question(1, "To be effective, this should ideally contain at least one digit and not match a natural language word.", q9Options, null);
		sampleQuestions.add(q9);
		
		List<QuestionOption> q10Options = new ArrayList<QuestionOption>();
		q10Options.add(new QuestionOption("RSA", false));
		q10Options.add(new QuestionOption("AUP", true));
		q10Options.add(new QuestionOption("SET", false));
		q10Options.add(new QuestionOption("VPN", false));
		q10Options.add(new QuestionOption("PKI", false));
		Question q10 = new Question(1, "This is an agreement a company may ask an employee to sign that specifies what is considered to be appropriate (or inappropriate) use of e-mail or Web browsing.", q10Options, null);
		sampleQuestions.add(q10);
		
		return "qqActivity";
	}
	

	public QuestionOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(QuestionOption selectedOption) {
		this.selectedOption = selectedOption;
	}

	public List<Question> getSamplequestions() {
		return sampleQuestions;
	}
	
	public int getTotalCorrAnswer() {
		return totalCorrAnswer;
	}

	public void setTotalCorrAnswer(int totalCorrAnswer) {
		this.totalCorrAnswer = totalCorrAnswer;
	}

	public boolean isShowRes() {
		return showRes;
	}

	public void setShowRes(boolean showRes) {
		this.showRes = showRes;
	}

	public int getTotalWrongAnswer() {
		return totalWrongAnswer;
	}

	public void setTotalWrongAnswer(int totalWrongAnswer) {
		this.totalWrongAnswer = totalWrongAnswer;
	}
	/**
	 * @return the quizz
	 */
	public List<ListQuestions> getQuizz() {
		return quizz;
	}
	/**
	 * @param quizz the quizz to set
	 */
	public void setQuizz(List<ListQuestions> quizz) {
		this.quizz = quizz;
	}
	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	/**
	 * @return the newQuizz
	 */
	public List<Quizz> getNewQuizz() {
		return newQuizz;
	}
	/**
	 * @param newQuizz the newQuizz to set
	 */
	public void setNewQuizz(List<Quizz> newQuizz) {
		this.newQuizz = newQuizz;
	}
	/**
	 * @return the quizzChosen
	 */
	public Quizz getQuizzChosen() {
		return quizzChosen;
	}
	/**
	 * @param quizzChosen the quizzChosen to set
	 */
	public void setQuizzChosen(Quizz quizzChosen) {
		this.quizzChosen = quizzChosen;
	}
	/**
	 * @return the response
	 */
	public int getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(int response) {
		this.response = response;
	}
	/**
	 * @return the step
	 */
	public String getStep() {
		return step;
	}
	/**
	 * @param step the step to set
	 */
	public void setStep(String step) {
		this.step = step;
	}
	/**
	 * @return the results
	 */
	public QuizzResult getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(QuizzResult results) {
		this.results = results;
	}
	/**
	 * @return the questionslist
	 */
	public ArrayList<String> getQuestionslist() {
		return questionslist;
	}
	/**
	 * @param questionslist the questionslist to set
	 */
	public void setQuestionslist(ArrayList<String> questionslist) {
		this.questionslist = questionslist;
	}
	/**
	 * @return the responseslist
	 */
	public ArrayList<Integer> getResponseslist() {
		return responseslist;
	}
	/**
	 * @param responseslist the responseslist to set
	 */
	public void setResponseslist(ArrayList<Integer> responseslist) {
		this.responseslist = responseslist;
	}

	/**
	 * @return the totalfinalCorrAnswer
	 */
	public int getTotalfinalCorrAnswer() {
		return totalfinalCorrAnswer;
	}

	/**
	 * @param totalfinalCorrAnswer the totalfinalCorrAnswer to set
	 */
	public void setTotalfinalCorrAnswer(int totalfinalCorrAnswer) {
		this.totalfinalCorrAnswer = totalfinalCorrAnswer;
	}

	/**
	 * @return the totalFinalWrongAnswer
	 */
	public int getTotalFinalWrongAnswer() {
		return totalFinalWrongAnswer;
	}

	/**
	 * @param totalFinalWrongAnswer the totalFinalWrongAnswer to set
	 */
	public void setTotalFinalWrongAnswer(int totalFinalWrongAnswer) {
		this.totalFinalWrongAnswer = totalFinalWrongAnswer;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
        Collections.sort(employee.getMyQuizResults());
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	/**
	 * @return the quizzDone
	 */
	public Boolean getQuizzDone() {
		return quizzDone;
	}


	/**
	 * @param quizzDone the quizzDone to set
	 */
	public void setQuizzDone(Boolean quizzDone) {
		this.quizzDone = quizzDone;
	}

	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        correctAnswer = resultQuizzChosen.getCorrectAnswers();
        wrongAnswer = resultQuizzChosen.getWrongAnswers();
        
        ChartSeries userDatas = new ChartSeries();
        userDatas.setLabel("Usted");
        userDatas.set("Correcto", correctAnswer);
        userDatas.set("Incorrecto", wrongAnswer);
        
        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("Otros");
        averageDatas.set("Correcto",averageCorrAnswer);
        averageDatas.set("Incorrecto", averageWrongAnswer);

        model.addSeries(userDatas);
        model.addSeries(averageDatas);
        
        return model;
    }
    
    private void createBarModels() {

        createBarModel();
        createHorizontalBarModel();
    }
    
    private void createBarModel() {
        barModel = initBarModel();
        
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
        
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Quizz");
        
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Promedio");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
        correctAnswer = resultQuizzChosen.getCorrectAnswers();
        wrongAnswer = resultQuizzChosen.getWrongAnswers();
        
        ChartSeries userDatas = new ChartSeries();
        userDatas.setLabel("Usted");
        userDatas.set("Correcto", correctAnswer);
        userDatas.set("Incorrecto", wrongAnswer);

        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("Otros");
        averageDatas.set("Correcto", averageCorrAnswer);
        averageDatas.set("Incorrecto", averageWrongAnswer);

        horizontalBarModel.addSeries(userDatas);
        horizontalBarModel.addSeries(averageDatas);
        
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Promedio");
        xAxis.setMin(0);
        xAxis.setMax(10);
        
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quiz");        
    }

    private void createPieModel() {
        pieModel1 = new PieChartModel();
        
        correctAnswer = resultQuizzChosen.getCorrectAnswers();
        wrongAnswer = resultQuizzChosen.getWrongAnswers();
        
       

         
        pieModel1.set("Correcto", correctAnswer);
        pieModel1.set("Incorrecto", wrongAnswer);
        pieModel1.setFill(false);
        pieModel1.setShowDataLabels(true);
         
        pieModel1.setTitle("Pie Chart");
        pieModel1.setLegendPosition("w");
    }
    
	
	/**
	 * @return the resultQuizzChosen
	 */
	public QuizzResult getResultQuizzChosen() {
		
		createBarModels();
		createPieModel();
	        
		return resultQuizzChosen;
	}


	/**
	 * @param resultQuizzChosen the resultQuizzChosen to set
	 */
	public void setResultQuizzChosen(QuizzResult resultQuizzChosen) {
		this.resultQuizzChosen = resultQuizzChosen;
	}


	/**
	 * @return the barModel
	 */
	public BarChartModel getBarModel() {
		return barModel;
	}


	/**
	 * @param barModel the barModel to set
	 */
	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}


	/**
	 * @return the horizontalBarModel
	 */
	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}


	/**
	 * @param horizontalBarModel the horizontalBarModel to set
	 */
	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}


	/**
	 * @return the pieModel1
	 */
	public PieChartModel getPieModel1() {
		return pieModel1;
	}


	/**
	 * @param pieModel1 the pieModel1 to set
	 */
	public void setPieModel1(PieChartModel pieModel1) {
		this.pieModel1 = pieModel1;
	}
}
