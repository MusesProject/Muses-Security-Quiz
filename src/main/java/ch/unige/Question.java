package ch.unige;

import java.util.List;

public class Question {
	
	private int id;
	/**
	 * 
	 */
	public Question() {
		super();
	}

	private String questionText;
	
	private List<QuestionOption> questionOptions;
	
	private QuestionOption selectedOption;
	
	

	@Override
	public String toString() {
		return "Question [id=" + id + ", questionText=" + questionText
				+ ", questionOptions=" + questionOptions + ", selectedOption="
				+ selectedOption + "]";
	}

	public Question(int id, String questionText,
			List<QuestionOption> questionOptions, QuestionOption selectedOption) {
		super();
		this.id = id;
		this.questionText = questionText;
		this.questionOptions = questionOptions;
		this.selectedOption = selectedOption;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<QuestionOption> getQuestionOptions() {
		return questionOptions;
	}

	public void setQuestionOptions(List<QuestionOption> questionOptions) {
		this.questionOptions = questionOptions;
	}

	public QuestionOption getSelectedOption() {
		return selectedOption;
	}

	public void setSelectedOption(QuestionOption selectedOption) {
		this.selectedOption = selectedOption;
	}


}
