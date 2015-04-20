package musesproject.eu;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;


public class QuizAppMbean1 {
	
	private static final List<Question> sampleQuestions = new ArrayList<Question>();

	private QuestionOption selectedOption;


	private boolean showRes;
	public static int totalCorrAnswer;
	public static int totalWrongAnswer;

	int counter = 0;

	public void init() {

		sampleQuestions.clear();
		totalCorrAnswer = 0;
	}
	
	public void moveToNextQuestion() {
		
		
		if (selectedOption.isAnswer()) {
			totalCorrAnswer++;
		}
		
		if (counter < sampleQuestions.size()) {
			counter++;
		}

		if (counter >= sampleQuestions.size()) {
			System.out.println("show dialog");
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
			try {
				context.redirect("qqResult1.jsf");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			RequestContext.getCurrentInstance().execute(
					"PF('resultButton').jq.click();");
			counter = 0;
			totalWrongAnswer = sampleQuestions.size() - totalCorrAnswer;

			return;

		}
		RequestContext
				.getCurrentInstance()
				.execute(
						"PF('dataGrid').getPaginator().setPage(PF('dataList').getPaginator().cfg.page+1)");

	}

	static {
		sampleQuestions.clear();


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

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
}