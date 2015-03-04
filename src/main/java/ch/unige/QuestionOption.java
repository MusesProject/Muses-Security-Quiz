package ch.unige;

public class QuestionOption {
	
	@Override
	public String toString() {
		return "QuestionOption [optionText=" + optionText + ", isAnswer="
				+ isAnswer + "]";
	}

	private String optionText;
	private boolean isAnswer;

	public QuestionOption(String optionText, boolean isAnswer) {
		super();
		this.optionText = optionText;
		this.isAnswer = isAnswer;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}

	public boolean isAnswer() {
		return isAnswer;
	}

	public void setAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}

}
