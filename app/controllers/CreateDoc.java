package controllers;

/**
 * Created by PRATYA on 11/23/2018.
 */

import play.mvc.Controller;

public class CreateDoc extends Controller {
/*
    public static List<Course> courseList = new ArrayList<Course>();
    public static Course course;
    public static Instructors instructors;
    public static List<Instructors> instructorsList = new ArrayList<Instructors>();
    public static List<CourseDestination> courseDestinationList = new ArrayList<CourseDestination>();
    public static CourseDestination courseDestination;
    public static List<CourseObjectives> courseObjectivesList = new ArrayList<CourseObjectives>();
    public static CourseObjectives courseObjectivess;
    public static Result main(Html Content) {
        return ok(main.render(Content));
    }
    public static Result mainDoc(Html Content, String nameC, String nameCat) {
        instructorsList = Instructors.finder.where().and(Expr.eq("teacher.id", session("userId")), Expr.eq("status", "1")).findList();
        return main(categoryAll.render(Content, instructorsList, nameC, nameCat));
    }
    public static Result createDoc() {
        instructorsList = Instructors.finder.where().and(Expr.eq("teacher.id", session("userId")), Expr.eq("status", "1")).findList();
        instructors = null;
        return main(categoryAll.render(category1.render(instructors), instructorsList, "", ""));
    }
    public static List<GeneralData> generalDataList = new ArrayList<GeneralData>();
    public static GeneralData generalData;
    public static Result showDoc3() {
        generalDataList = GeneralData.finder.where().eq("teacher.id",session("userId")).findList();
        return main(listDoc3.render(generalDataList));
    }


    /*
    public static Result loadData() {
        List<CourseDestination> courseDestinationList1 = new ArrayList<CourseDestination>();
        List<CourseObjectives> courseObjectivesList1 = new ArrayList<CourseObjectives>();
        DynamicForm data = Form.form().bindFromRequest();
        String nameCourse;
        if (data != null) {
            session("InsId", data.get("InsId"));
            session("nameCat", data.get("nameCat"));
            session("nameCourse",data.get("nameCourse"));
        }
        if (data.get("InsId") == null) {
            nameCourse =  session("nameCourse");
        } else {
            instructors = Instructors.finder.byId(data.get("InsId"));
            nameCourse = instructors.getCourse().getNameTH();
        }
        String nameCat = session("nameCat");
        instructorsList = Instructors.finder.where().eq("course.id", session("userId")).findList();
        if (instructorsList.equals(null)) {
            flash("data", nameCat);
            return mainDoc(category1.render(instructors), nameCourse, nameCat);
        } else {
            if (nameCat.equals("cat1")) {
                nameCat = data.get("nameCat");
                return mainDoc(category1.render(instructors), nameCourse, nameCat);
            } else if (nameCat.equals("cat2")) {
                generalData = GeneralData.finder.where().eq("instructors.id", session("InsId")).findUnique();
                courseDestinationList = CourseDestination.finder.where().eq("course.id",generalData.getCourse().getId()).findList();
                courseObjectivesList = CourseObjectives.finder.where().eq("course.id",generalData.getCourse().getId()).findList();
                if (generalData == null) {
                    generalData = GeneralData.finder.byId(session("idGen"));
                }
                nameCat = data.get("nameCat");
                List<BasketDestination> basketDestinationList = new ArrayList<BasketDestination>();
                if (Cache.get("basketDestinations") != null){
                    basketDestinationList = ((List<BasketDestination>) Cache.get("basketDestinations"));
                }
                List<BasketObjectives> basketObjectivesList = new ArrayList<BasketObjectives>();
                if (Cache.get("basketObjectives") != null){
                    basketObjectivesList = ((List<BasketObjectives>) Cache.get("basketObjectives"));
                }
                courseObjectivesList1=  CourseObjectives.finder.where().eq("generalData.id",generalData.getId()).findList();
                courseDestinationList1 =  CourseDestination.finder.where().eq("generalData.id",generalData.getId()).findList();
                return mainDoc(category2.render(generalData, courseDestinationList, courseObjectivesList,basketDestinationList,basketObjectivesList,courseDestinationList1,courseObjectivesList1), nameCourse, nameCat);
            } else if (nameCat.equals("cat3")) {
                nameCat = data.get("nameCat");
                return mainDoc(category3.render(), nameCourse, nameCat);
            } else if (nameCat.equals("cat4")) {
                nameCat = data.get("nameCat");
                return mainDoc(category4.render(), nameCourse, nameCat);
            } else if (nameCat.equals("cat5")) {
                nameCat = data.get("nameCat");
                return mainDoc(category5.render(), nameCourse, nameCat);
            } else if (nameCat.equals("cat6")) {
                nameCat = data.get("nameCat");
                return mainDoc(category6.render(), nameCourse, nameCat);
            } else {
                nameCat = data.get("nameCat");
                return mainDoc(category7.render(), nameCourse, nameCat);
            }
        }
    }

    public static Result saveCat1() {
        DynamicForm newDataGen = Form.form().bindFromRequest();
        instructors = Instructors.finder.byId(newDataGen.get("dataIns"));
        int numlist = generalDataList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (generalDataList.size() == 0) {
            sId = "Doc-000001";
        } else {
            lastNum = generalDataList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "Doc-00000" + id;
            } else if (id < 100) {
                sId = "Doc-0000" + id;
            } else if (id < 1000) {
                sId = "Doc-000" + id;
            } else if (id < 10000) {
                sId = "Doc-00" + id;
            } else if (id < 100000) {
                sId = "Doc-0" + id;
            } else {
                sId = "Doc-" + id;
            }
        }
        generalData = new GeneralData();
        generalData.setId(sId);
        generalData.setHour(5);
        generalData.setDetail("");
        generalData.setSuggestion("");
        generalData.setCourse(instructors.getCourse());
        generalData.setInstructors(instructors);
        GeneralData.insert(generalData);
        return redirect("/showDoc3");
    }


    public static Result addCourseDes() {
        DynamicForm dataDestination = Form.form().bindFromRequest();
        String detailDes = dataDestination.get("detailDes");
        generalData = GeneralData.finder.byId(dataDestination.get("genId"));
        course  = generalData.getCourse();
        courseDestinationList = CourseDestination.courseDestinationList();
        int numlist = courseDestinationList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseDestinationList.size() == 0) {
            sId = "Des-000001";
        } else {
            lastNum = courseDestinationList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "Des-00000" + id;
            } else if (id < 100) {
                sId = "Des-0000" + id;
            } else if (id < 1000) {
                sId = "Des-000" + id;
            } else if (id < 10000) {
                sId = "Des-00" + id;
            } else if (id < 100000) {
                sId = "Des-0" + id;
            } else {
                sId = "Des-" + id;
            }
        }
        courseDestination = new CourseDestination();
        courseDestination.setId(sId);
        courseDestination.setDetail(detailDes);
        courseDestination.setCourse(course);
        session("idGen", generalData.getId());
        session("InsIds", dataDestination.get("InsId"));
        CourseDestination.insert(courseDestination);
        return loadData();

    }

    public static Result selectDes() {
        List<BasketDestination> basketDestinations = new ArrayList<BasketDestination>();
        DynamicForm dataDes = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataDes.get("genId"));
        courseDestinationList = CourseDestination.courseDestinationList();
        course = generalData.getCourse();
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] valI = map.get("courseDes");
        int i=0;
        for(i=0; i<valI.length;i++){
            String value = valI[i];
            courseDestination = CourseDestination.finder.byId(value);
            basketDestinations.add(new BasketDestination(courseDestination.getId(), courseDestination.getDetail(), generalData,course));
            Cache.set("basketDestinations", basketDestinations);
        }
        session("idGen", generalData.getId());
        session("InsIds", dataDes.get("InsId"));
        return loadData();
    }

    public static Result deleteDes (String id){
            courseDestination = CourseDestination.finder.byId(id);
            generalData  = courseDestination.getGeneralData();
            instructors = courseDestination.getGeneralData().getInstructors();
            if (courseDestination != null){
             courseDestination.setGeneralData(null);
                CourseDestination.update(courseDestination);
            }
            session("idGen",generalData.getId());
            session("InsIds", instructors.getId());
            return loadData();
    }

       public static Result addCourseObjectives (){
        DynamicForm courseObjectives = Form.form().bindFromRequest();
        String detailOJT = courseObjectives.get("detailOJT");
        generalData = GeneralData.finder.byId(courseObjectives.get("genId"));
        course =  generalData.getCourse();
        courseObjectivesList = CourseObjectives.courseObjectivesList();
        int numlist = courseObjectivesList.size();
        int id, idNum;
        String lastNum;
        String sId;
        if (courseObjectivesList.size() == 0) {
            sId = "OJT-000001";
        } else {
            lastNum = courseObjectivesList.get(numlist - 1).getId();
            lastNum = lastNum.substring(4);
            idNum = Integer.parseInt(lastNum);
            id = idNum + 1;
            if (id < 10) {
                sId = "OJT-00000" + id;
            } else if (id < 100) {
                sId = "OJT-0000" + id;
            } else if (id < 1000) {
                sId = "OJT-000" + id;
            } else if (id < 10000) {
                sId = "OJT-00" + id;
            } else if (id < 100000) {
                sId = "OJT-0" + id;
            } else {
                sId = "OJT-" + id;
            }
        }
        courseObjectivess = new CourseObjectives();
        courseObjectivess.setId(sId);
        courseObjectivess.setDetail(detailOJT);
        courseObjectivess.setCourse(course);
        session("idGen", generalData.getId());
        session("InsIds", courseObjectives.get("InsId"));
        CourseObjectives.insert(courseObjectivess);
        return loadData();
    }


    public static Result selectCourseObjectives (){
        List<BasketObjectives> basketObjectives= new ArrayList<BasketObjectives>();
        DynamicForm dataOJT = Form.form().bindFromRequest();
        generalData = GeneralData.finder.byId(dataOJT.get("genId"));
        courseObjectivesList = CourseObjectives.courseObjectivesList();
        course = generalData.getCourse();
        Map<String, String[]> map = request().body().asFormUrlEncoded();
        String[] valI = map.get("courseObjectives");
        int i=0;
        for(i=0; i<valI.length;i++){
            String value = valI[i];
            courseObjectivess = CourseObjectives.finder.byId(value);
            basketObjectives.add(new BasketObjectives(courseObjectivess.getId(), courseObjectivess.getDetail(), generalData,course));
            Cache.set("basketObjectives", basketObjectives);
        }
        session("idGen", generalData.getId());
        session("InsIds", dataOJT.get("InsId"));
        return loadData();
    }


    public static Result saveCategory2 (){
        DynamicForm data = Form.form().bindFromRequest();
        List<BasketDestination> basketDestinationList = new ArrayList<BasketDestination>();
        if (Cache.get("basketDestinations") != null){
            basketDestinationList = ((List<BasketDestination>) Cache.get("basketDestinations"));
            for (int i = 0  ; i<basketDestinationList.size();i++){
              String id = basketDestinationList.get(i).getId();
                String detail = basketDestinationList.get(i).getDetail();
                generalData = GeneralData.finder.byId(session().get("idGen"));
                courseDestination = new CourseDestination();
                courseDestination.setId(id);
                courseDestination.setDetail(detail);
                courseDestination.setGeneralData(generalData);
                CourseDestination.update(courseDestination);
            }
    }
        Cache.remove("basketDestinations");
        basketDestinationList.clear();

        List<BasketObjectives> basketObjectivesList = new ArrayList<BasketObjectives>();
        if (Cache.get("basketObjectives") != null){
            basketObjectivesList = ((List<BasketObjectives>) Cache.get("basketObjectives"));
            for (int i = 0  ; i<basketObjectivesList.size();i++){
                String id = basketObjectivesList.get(i).getId();
                String detail = basketObjectivesList.get(i).getDetail();
                generalData = GeneralData.finder.byId(session().get("idGen"));
                courseObjectivess =new CourseObjectives();
                courseObjectivess.setId(id);
                courseObjectivess.setDetail(detail);
                courseObjectivess.setGeneralData(generalData);
                CourseObjectives.update(courseObjectivess);
            }
        }
        Cache.remove("basketObjectives");
        basketObjectivesList.clear();
        return ok();
    }
      */

}

