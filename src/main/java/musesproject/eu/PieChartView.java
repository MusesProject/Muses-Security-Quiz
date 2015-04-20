package musesproject.eu;

import javax.annotation.PostConstruct;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
public class PieChartView implements Serializable {
	
	private PieChartModel pieModel1;
	private int wrongAnswer = 0;
	private int correctAnswer = 0;

 
    @PostConstruct
    public void init() {
        createPieModels();

    }
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        
        correctAnswer = QuizAppMbean.totalfinalCorrAnswer;
        wrongAnswer = QuizAppMbean.totalFinalWrongAnswer;
        
        System.out.println("test: "+ QuizAppMbean.totalfinalCorrAnswer);
        
        System.out.println("test: "+ QuizAppMbean.totalFinalWrongAnswer);

         
        pieModel1.set("Right", correctAnswer);
        pieModel1.set("False", wrongAnswer);
        pieModel1.setFill(false);
        pieModel1.setShowDataLabels(true);
         
        pieModel1.setTitle("Pie Chart");
        pieModel1.setLegendPosition("w");
    }
    
    
 
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    private void createPieModels() {
        createPieModel1();
    }


	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public int getWrongAnswer() {
		return wrongAnswer;
	}

	public void setWrongAnswer(int wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}
     
     
}


