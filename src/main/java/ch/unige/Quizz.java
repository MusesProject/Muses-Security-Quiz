package ch.unige;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Quizz  implements Serializable{
	
	private String description;
	
	 @ManyToMany(cascade = CascadeType.ALL)
	 private List<QuizzQuestion> listQuestions = new ArrayList<QuizzQuestion>();
}
