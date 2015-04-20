package musesproject.eu;

import javax.annotation.PostConstruct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
public class ChartView implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
	private int wrongAnswer = 0;
	private int correctAnswer = 0;
    
    
    @PostConstruct
	public void init() {
        createBarModels();
	}

    public BarChartModel getBarModel() {
        return barModel;
    }
    
    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    
    public double averageWrongAnswers(){
    	List<QuizzResult> listQuizzResult = QuizzResult.findAllQuizzResults();
    	int wrongAnswers = 0;
    	
    	for (int i = 0; i < listQuizzResult.size(); i++) {
    		wrongAnswers = wrongAnswers + listQuizzResult.get(i).getWrongAnswers();
		}
    	System.out.println("sum: "+(wrongAnswers/listQuizzResult.size()));
    	
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
    	System.out.println("sum: "+correctAnswers/listQuizzResult.size());
    	
    	double val = (double)correctAnswers/(double)listQuizzResult.size();
		val = val*10;
		val = Math.round(val);
		val = val /10;
    	return val;
    	
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();

        correctAnswer = QuizAppMbean.totalfinalCorrAnswer;
        wrongAnswer = QuizAppMbean.totalFinalWrongAnswer;
        
        ChartSeries userDatas = new ChartSeries();
        userDatas.setLabel("You");
        userDatas.set("Correct", correctAnswer);
        userDatas.set("Wrong", wrongAnswer);

        
        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("World");
        averageDatas.set("Correct",averageCorrectAnswers());
        averageDatas.set("Wrong", averageWrongAnswers());

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
        correctAnswer = QuizAppMbean.totalfinalCorrAnswer;
        wrongAnswer = QuizAppMbean.totalFinalWrongAnswer;
        
        ChartSeries userDatas = new ChartSeries();
        userDatas.setLabel("You");
        userDatas.set("Correct", correctAnswer);
        userDatas.set("Wrong", wrongAnswer);

        ChartSeries averageDatas = new ChartSeries();
        averageDatas.setLabel("World");
        averageDatas.set("Correct", averageCorrectAnswers());
        averageDatas.set("Wrong", averageWrongAnswers());

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