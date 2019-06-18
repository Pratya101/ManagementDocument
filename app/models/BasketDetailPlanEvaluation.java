package models;

/**
 * Created by coffee on 5/23/2019.
 */
public class BasketDetailPlanEvaluation {
    private String week,rate,outcome;
    private EvaluationStrategy evaluationStrategy;


    public BasketDetailPlanEvaluation() {
    }


    public BasketDetailPlanEvaluation(String week, String rate, String outcome, EvaluationStrategy evaluationStrategy) {
        this.week = week;
        this.rate = rate;
        this.outcome = outcome;
        this.evaluationStrategy = evaluationStrategy;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public EvaluationStrategy getEvaluationStrategy() {
        return evaluationStrategy;
    }

    public void setEvaluationStrategy(EvaluationStrategy evaluationStrategy) {
        this.evaluationStrategy = evaluationStrategy;
    }
}



