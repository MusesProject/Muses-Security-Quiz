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
		
		/*QuizzQuestion q = QuizzQuestion.findQuizzQuestion((long)2411);
		q.setQuestionText("You have to create a new password – which approach do you take?");
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("I select a word I won’t forget like my partner’s name.");
		answers.add("I combine a word that I will easily remember with a number, such as the name of my street and my postal code.");
		answers.add("I use a meaningless word combined with a number or a special character.");
		answers.add("I create a password with a minimum of 8 letters, including high and low case, numbers and special characters.");
		q.setAnswers(answers);
		q.setResponse(3);

		q.merge();
	
		QuizzQuestion q1 = QuizzQuestion.findQuizzQuestion((long)2421);
		q.setQuestionText("You have to leave your work place for a short meeting one floor down. What do you do with your laptop?");
		ArrayList<String> answers1 = new ArrayList<String>();
		answers1.add("I lock my screen.");
		answers1.add("I lock my screen and lock my room.");
		answers1.add("I don’t do anything specific, as the office is a safe place anyway.");
		answers1.add("I take my laptop with me.");
		q1.setAnswers(answers1);
		q1.setResponse(1);

		q1.merge();
		
		QuizzQuestion q2 = QuizzQuestion.findQuizzQuestion((long)2431);
		q2.setQuestionText("You are on your way to enter your office area and see that 2 unknown persons are behind you. What do you do?");
		ArrayList<String> answers2 = new ArrayList<String>();
		answers2.add("I greet them politely and hold the door open so they can pass.");
		answers2.add("I ask them politely where they need to go and explain to them how to get there if they need help.");
		answers2.add("I stop them, ask them to state their names and their business and then I make a phone call to the colleague they claim they are here to see and check if they are really telling the truth.");
		answers2.add("I ask them whom they are here to see and accompany them to the respective colleague.");
		q2.setAnswers(answers2);
		q2.setResponse(3);

		q2.merge();
		
		QuizzQuestion q3 = QuizzQuestion.findQuizzQuestion((long)2441);
		q3.setQuestionText("You are working in an airport café while waiting for your flight. You want to get another coffee from the counter - what do you do with your laptop?");
		ArrayList<String> answers3 = new ArrayList<String>();
		answers3.add("I take it with me to the counter.");
		answers3.add("I ask the person on the table next to me to keep an eye on it.");
		answers3.add("I keep an eye on it while I wait.");
		answers3.add("I put it in my bag and store it under my chair.");
		q3.setAnswers(answers3);
		q3.setResponse(0);

		q3.merge();
		
		
		QuizzQuestion q4 = QuizzQuestion.findQuizzQuestion((long)2451);
		q4.setQuestionText("You are on a business trip and want to go out for dinner. How do you keep your laptop safe?");
		ArrayList<String> answers4 = new ArrayList<String>();
		answers4.add("I put it in the hotel safe.");
		answers4.add("I take it with me to dinner.");
		answers4.add("I attach it to something that cannot be removed from the room with a cable lock and put a “do not disturb” sign on the door.");
		answers4.add("I put it in the bottom of my suitcase and push the case under the bed or into another hidden spot.");
		q4.setAnswers(answers4);
		q4.setResponse(0);

		q4.merge();
		
		
		QuizzQuestion q5 = QuizzQuestion.findQuizzQuestion((long)2461);
		q5.setQuestionText("You have difficulties with processing some files and while browsing for some tips online you encounter a freeware from an unknown site that would make the task much easier. What do you do?");
		ArrayList<String> answers5 = new ArrayList<String>();
		answers5.add("I download the file and install it – if there is a problem I am sure my anti-virus software will stop the installation and then I’ll just erase the file.");
		answers5.add("I check if there are any safety certificates on the site that can ensure me that I won’t be downloading any malware. If I can locate such information I will download the freeware.");
		answers5.add("I will look for a seal of approval, do some research on the program I want to download, and make sure my security programs are up to date. If all is clear and there are no reports of the program being corrupted I will download it.");
		answers5.add("Nothing – it is never safe to download such programs.");
		q5.setAnswers(answers5);
		q5.setResponse(2);

		q5.merge();
		
		QuizzQuestion q6 = QuizzQuestion.findQuizzQuestion((long)2471);
		q6.setQuestionText("What is the safest way to store your work data?");
		ArrayList<String> answers6 = new ArrayList<String>();
		answers6.add("I store it on my computer and keep a safety copy of the most important files on an USB flash drive.");
		answers6.add("I store it on my computer and keep a safety copy within a cloud service such as Dropbox or GoogleDrive");
		answers6.add("I store my data on my company’s server.");
		answers6.add("I store my data on my company’s server and send the most important files to my personal email address.");
		q6.setAnswers(answers6);
		q6.setResponse(2);

		q6.merge();
		
		
		
		QuizzQuestion q7 = QuizzQuestion.findQuizzQuestion((long)2481);
		q7.setQuestionText("You receive an email at your work account that you suspect to be spam. What do you do with it?");
		ArrayList<String> answers7 = new ArrayList<String>();
		answers7.add("I click on any provided links to check if it really is spam.");
		answers7.add("I mark the email as spam and forward it to our IT support.");
		answers7.add("I reply and ask the sender to remove me from the mailing list.");
		answers7.add("I follow the instructions to remove my email from the mailing list.");
		q7.setAnswers(answers7);
		q7.setResponse(1);

		q7.merge();
		
		
		QuizzQuestion q8 = QuizzQuestion.findQuizzQuestion((long)2491);
		q8.setQuestionText("You receive an email from a colleague with an attachment that is not addressed in the content of the email. What do you do?");
		ArrayList<String> answers8 = new ArrayList<String>();
		answers8.add("I contact my colleague and ask him about it. If he intentionally attached the file and it is something I’m actually supposed to look at, I will do so.");
		answers8.add("I open it and check what it is.");
		answers8.add("I ignore it – if it was something I am supposed to look at I’m sure my colleague will contact me about it.");
		answers8.add("I forward the email to our IT-support and erase it from my computer.");
		q8.setAnswers(answers8);
		q8.setResponse(0);

		q8.merge();
		
		QuizzQuestion q9 = QuizzQuestion.findQuizzQuestion((long)2401);
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
        userDatas.setLabel("You");
        userDatas.set("Correct", correctAnswer);
        userDatas.set("Wrong", wrongAnswer);
        
        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("Others");
        averageDatas.set("Correct",averageCorrAnswer);
        averageDatas.set("Wrong", averageWrongAnswer);

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
        yAxis.setLabel("Moyenne");
        yAxis.setMin(0);
        yAxis.setMax(10);
    }
    
    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
        correctAnswer = resultQuizzChosen.getCorrectAnswers();
        wrongAnswer = resultQuizzChosen.getWrongAnswers();
        
        ChartSeries userDatas = new ChartSeries();
        userDatas.setLabel("You");
        userDatas.set("Correct", correctAnswer);
        userDatas.set("Wrong", wrongAnswer);

        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("Others");
        averageDatas.set("Correct", averageCorrAnswer);
        averageDatas.set("Wrong", averageWrongAnswer);

        horizontalBarModel.addSeries(userDatas);
        horizontalBarModel.addSeries(averageDatas);
        
        horizontalBarModel.setTitle("Horizontal and Stacked");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);
        
        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Moyenne");
        xAxis.setMin(0);
        xAxis.setMax(10);
        
        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quiz");        
    }

    private void createPieModel() {
        pieModel1 = new PieChartModel();
        
        correctAnswer = resultQuizzChosen.getCorrectAnswers();
        wrongAnswer = resultQuizzChosen.getWrongAnswers();
        
       

         
        pieModel1.set("Right", correctAnswer);
        pieModel1.set("False", wrongAnswer);
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
