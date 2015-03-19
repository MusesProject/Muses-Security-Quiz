package ch.unige;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuizzResult implements Comparable<QuizzResult>{
		
	@ManyToOne
	private Quizz quizz;
	
	@ManyToOne
	private Employee employee;
	
	@Column (length = 1000000)
	private ArrayList<Integer> responses = new ArrayList<Integer>();
	
	private int correctAnswers;
	
	private int wrongAnswers;
	
	private Date date;
	
	

	@Override
	public int compareTo(QuizzResult quizzresult) {
		// TODO Auto-generated method stub
		Date start = quizzresult.getDate();
		
		// TODO Auto-generated method stub
		return start.compareTo(date);	
		}

	

}
