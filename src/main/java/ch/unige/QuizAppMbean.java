package ch.unige;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;

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
		
		
		/*Employee emp = new Employee();
		emp.setFirstname("xavier");emp.setLastname("xavier");
		emp.setPassword("titi");
		emp.setEmail("xavier");
		emp.setJob("testseur");
		emp.setExperience(2);
		emp.setAge(30);
		emp.setSex("1");		
		emp.persist();
				
		QuizzQuestion question1 = new QuizzQuestion();
		
		ArrayList<QuizzQuestion> list = new ArrayList<QuizzQuestion>();
		
		Quizz quizz = new Quizz();
		
		quizz.setDescription("Muses Security quiz");

		Quizz quizz1 = new Quizz();
		quizz1.setDescription("web");
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getUserPrincipal().getName();
		
		employee = findEmployeebyEmail(facesContext.getExternalContext().getUserPrincipal().getName());	
		
		question1.setQuestionText("This is a document that states in writing how a company plans to protect the company's physical and IT assets.");
		ArrayList<String> option1 = new ArrayList<String>();
		option1.add("Data Encryption Standard");
		option1.add("security policy");
		option1.add("public key certificate");
		option1.add("access control list");
		option1.add("copyright");
		question1.setAnswers(option1);
		question1.setResponse(1);
		question1.persist();

		
		QuizzQuestion question2 = new QuizzQuestion();
		
		question2.setQuestionText("This is a program or file that is specifically developed for the purpose of doing harm.");
		ArrayList<String> option2 = new ArrayList<String>();
		option2.add("Buffer overflow");
		option2.add("Bastion host");
		option2.add("malware");
		option2.add("Ping Sweep");
		option2.add("Passphrase");
		question2.setAnswers(option2);
		question2.setResponse(2);
		question2.persist();

		QuizzQuestion question3 = new QuizzQuestion();
		
		question3.setQuestionText("This is a set of related programs, usually located at a network gateway server, that protects the resources of a private network from other networks.");
		ArrayList<String> option3 = new ArrayList<String>();
		option3.add("Firewall");
		option3.add("Sandbox");
		option3.add("Rootkit");
		option3.add("Password cracker");
		option3.add("General protection fault");
		question3.setAnswers(option3);
		question3.setResponse(0);
		question3.persist();

		QuizzQuestion question4 = new QuizzQuestion();
		
		question4.setQuestionText("This is a class of programs that searches your hard drive and floppy disks for any known or potential viruses.");
		ArrayList<String> option4 = new ArrayList<String>();
		option4.add("Instrusion detection");
		option4.add("Security Identifier");
		option4.add("Antigen");
		option4.add("Probe");
		option4.add("Antivirus software");
		question4.setAnswers(option4);
		question4.setResponse(3);
		question4.persist();

		
		QuizzQuestion question5 = new QuizzQuestion();
		
		question5.setQuestionText("In computer security, this describes a non-technical kind of intrusion that relies heavily on human interaction. It often involves tricking people into breaking their own security procedures. ");
		ArrayList<String> option5 = new ArrayList<String>();
		option5.add("Cyberterrorism");
		option5.add("Debugging");
		option5.add("Hijacking");
		option5.add("Nonrepudiation");
		option5.add("Social engineering");
		question5.setAnswers(option5);
		question5.setResponse(4);
		question5.persist();

		
		
		QuizzQuestion question6 = new QuizzQuestion();
		
		question6.setQuestionText("This is a program in which malicious or harmful code is contained inside apparently harmless programming or data.");
		ArrayList<String> option6 = new ArrayList<String>();
		option6.add("War dialer");
		option6.add("Spam trap");
		option6.add("Smurf");
		option6.add("Trojan horse");
		option6.add("Walled garden");
		question6.setAnswers(option6);
		question6.setResponse(3);
		question6.persist();

		QuizzQuestion question7 = new QuizzQuestion();
		
		question7.setQuestionText("This is the process of determining whether someone or something is, in fact, who or what it is declared to be.");
		ArrayList<String> option7 = new ArrayList<String>();
		option7.add("Conditional access");
		option7.add("Anonymizer");
		option7.add("Bypass");
		option7.add("User profile");
		option7.add("Authentification");
		question7.setAnswers(option7);
		question7.setResponse(4);
		question7.persist();

		
		
		QuizzQuestion question8 = new QuizzQuestion();
		
		question8.setQuestionText("This is the conversion of data into a ciphertext that cannot be easily understood by unauthorized people.");
		ArrayList<String> option8 = new ArrayList<String>();
		option8.add("Brute force cracking");
		option8.add("Tunneling");
		option8.add("Encryption");
		option8.add("Cipertext feedback");
		option8.add("Cloaking");
		question8.setAnswers(option8);
		question8.setResponse(2);
		question8.persist();

		
		QuizzQuestion question9 = new QuizzQuestion();
		
		question9.setQuestionText("To be effective, this should ideally contain at least one digit and not match a natural language word.");
		ArrayList<String> option9 = new ArrayList<String>();
		option9.add("Digital Signature");
		option9.add("Smart Card");
		option9.add("Public Key");
		option9.add("Password");
		option9.add("Signature File");
		question9.setAnswers(option9);
		question9.setResponse(3);
		question9.persist();

		
		QuizzQuestion question10 = new QuizzQuestion();
		
		question10.setQuestionText("This is an agreement a company may ask an employee to sign that specifies what is considered to be appropriate (or inappropriate) use of e-mail or Web browsing.");
		ArrayList<String> option10 = new ArrayList<String>();
		option10.add("RSA");
		option10.add("AUP");
		option10.add("SET");
		option10.add("VPN");
		option10.add("PKI");
		question10.setAnswers(option10);
		question10.setResponse(1);
		question10.persist();
		
		
		list.add(question1);
		list.add(question2);
		list.add(question3);
		list.add(question4);
		list.add(question5);
		list.add(question6);
		list.add(question7);
		list.add(question8);
		list.add(question9);
		list.add(question10);
		quizz.setListQuestions(list);
		//quizz1.setListQuestions(list);
		
		
		
		quizz.persist();
		question1.setQuizOwner(quizz);
		question2.setQuizOwner(quizz);
		question3.setQuizOwner(quizz);
		question4.setQuizOwner(quizz);
		question5.setQuizOwner(quizz);
		question6.setQuizOwner(quizz);
		question7.setQuizOwner(quizz);
		question8.setQuizOwner(quizz);
		question9.setQuizOwner(quizz);
		question10.setQuizOwner(quizz);
		
		question1.merge();
		question2.merge();
		question3.merge();
		question4.merge();
		question5.merge();
		question6.merge();
		question7.merge();
		question8.merge();
		question9.merge();
		question10.merge();*/



		//newQuizz.add(quizz);
		//newQuizz.add(quizz1);
		
		newQuizz = Quizz.findAllQuizzes();
		//newQuizz.add(quizz1);
	
		
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
	
	public String moveToNextQuestion() {
		
		System.out.println("size: "+sampleQuestions.size());
		
		System.out.println("response: "+response);
		
		System.out.println("step: "+step);
		
		//results.setDescription(quizzChosen.getDescription());
		
		
		totalfinalCorrAnswer = 0;
		
		totalFinalWrongAnswer = 0;
		
		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.getExternalContext().getUserPrincipal().getName();
		
		employee = findEmployeebyEmail(facesContext.getExternalContext().getUserPrincipal().getName());	
		
		

		if(step.equalsIgnoreCase("1")){
			
			
			
			questionslist.add(quizzChosen.getListQuestions().get(0).getQuestionText());
			quizzChosen.getListQuestions().get(0).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(0).getResponse()) {
				totalCorrAnswer++;
			}
			return "step2";
		}
		if(step.equalsIgnoreCase("2")){
			
			System.out.println("okk");

			
			questionslist.add(quizzChosen.getListQuestions().get(1).getQuestionText());
			quizzChosen.getListQuestions().get(1).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(1).getResponse()) {
				totalCorrAnswer++;
			}
			return "step3";
		}
		if(step.equalsIgnoreCase("3")){
			
			questionslist.add(quizzChosen.getListQuestions().get(2).getQuestionText());
			quizzChosen.getListQuestions().get(2).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(2).getResponse()) {
				totalCorrAnswer++;
			}
			return "step4";
		}
		if(step.equalsIgnoreCase("4")){
			
			questionslist.add(quizzChosen.getListQuestions().get(3).getQuestionText());
			quizzChosen.getListQuestions().get(3).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(3).getResponse()) {
				totalCorrAnswer++;
			}
			return "step5";
		}
		if(step.equalsIgnoreCase("5")){
			
			questionslist.add(quizzChosen.getListQuestions().get(4).getQuestionText());
			quizzChosen.getListQuestions().get(4).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(4).getResponse()) {
				totalCorrAnswer++;
			}
			
			return "step6";
		}
		if(step.equalsIgnoreCase("6")){
			
			questionslist.add(quizzChosen.getListQuestions().get(5).getQuestionText());
			quizzChosen.getListQuestions().get(5).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(5).getResponse()) {
				totalCorrAnswer++;
			}
			return "step7";
		}
		if(step.equalsIgnoreCase("7")){
			
			questionslist.add(quizzChosen.getListQuestions().get(6).getQuestionText());
			quizzChosen.getListQuestions().get(6).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(6).getResponse()) {
				totalCorrAnswer++;
			}
			return "step8";
		}
		if(step.equalsIgnoreCase("8")){
			
			questionslist.add(quizzChosen.getListQuestions().get(7).getQuestionText());
			quizzChosen.getListQuestions().get(7).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(7).getResponse()) {
				totalCorrAnswer++;
			}
			return "step9";
		}
		if(step.equalsIgnoreCase("9")){
			
			questionslist.add(quizzChosen.getListQuestions().get(8).getQuestionText());
			quizzChosen.getListQuestions().get(8).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(8).getResponse()) {
				totalCorrAnswer++;
			}
			
			return "step10";
		}
		if(step.equalsIgnoreCase("10")){
			
			questionslist.add(quizzChosen.getListQuestions().get(9).getQuestionText());
			quizzChosen.getListQuestions().get(9).setYourresponse(response);
			responseslist.add(response);
			if (response == quizzChosen.getListQuestions().get(9).getResponse()) {
				totalCorrAnswer++;
			}
			
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
			
			
			
			return "result";
		}
		
		if(step.equalsIgnoreCase("final")){
			
			employee.getMyQuizzes().get(0).getDescription();
			
			
			
		}
		
		
		/*if (selectedOption.isAnswer()) {
			totalCorrAnswer++;
		}
		
		if (counter < sampleQuestions.size()) {
			counter++;
		}

		if (counter >= sampleQuestions.size()) {
			
			System.out.println("show dialog");

			RequestContext.getCurrentInstance().execute(
					"PF('resultButton').jq.click();");
			counter = 0;
			totalWrongAnswer = sampleQuestions.size() - totalCorrAnswer;
			
			return "";


		}*/
		
		return " ";
		/*RequestContext
				.getCurrentInstance()
				.execute(
						"PF('dataList').getPaginator().setPage(PF('dataList').getPaginator().cfg.page+1)");*/

	}

	static {
		

		/*List<QuestionOption> q1Options = new ArrayList<QuestionOption>();

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
		sampleQuestions.add(q10);*/

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
        averageDatas.setLabel("World");
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
        averageDatas.setLabel("World");
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
