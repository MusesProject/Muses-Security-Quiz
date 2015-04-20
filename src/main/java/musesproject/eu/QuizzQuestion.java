package musesproject.eu;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class QuizzQuestion implements Serializable{
	
	private String questionText;
	
	private ArrayList<String> answers;
	
	private int response;
	
	private int yourresponse;
	
	@ManyToOne
    private Quizz quizOwner;

}
