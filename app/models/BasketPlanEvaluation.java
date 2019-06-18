package models;

/**
 * Created by coffee on 5/12/2019.
 */
public class BasketPlanEvaluation {
    private  String earningOutcome, activity, evaluationMethod, evaluationWeek, ratingScale;

    private GeneralData generalData;

    public BasketPlanEvaluation() {
    }

    public BasketPlanEvaluation(String earningOutcome, String activity, String evaluationMethod, String evaluationWeek, String ratingScale, GeneralData generalData) {
        this.earningOutcome = earningOutcome;
        this.activity = activity;
        this.evaluationMethod = evaluationMethod;
        this.evaluationWeek = evaluationWeek;
        this.ratingScale = ratingScale;
        this.generalData = generalData;
    }

    public String getEarningOutcome() {
        return earningOutcome;
    }

    public void setEarningOutcome(String earningOutcome) {
        this.earningOutcome = earningOutcome;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getEvaluationMethod() {
        return evaluationMethod;
    }

    public void setEvaluationMethod(String evaluationMethod) {
        this.evaluationMethod = evaluationMethod;
    }

    public String getEvaluationWeek() {
        return evaluationWeek;
    }

    public void setEvaluationWeek(String evaluationWeek) {
        this.evaluationWeek = evaluationWeek;
    }

    public String getRatingScale() {
        return ratingScale;
    }

    public void setRatingScale(String ratingScale) {
        this.ratingScale = ratingScale;
    }

    public GeneralData getGeneralData() {
        return generalData;
    }

    public void setGeneralData(GeneralData generalData) {
        this.generalData = generalData;
    }
}
