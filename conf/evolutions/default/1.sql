# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbCategoryCourse (
  id                        varchar(255) not null,
  name                      varchar(255),
  amount                    integer,
  syllabus_course_id        varchar(255),
  constraint pk_tbCategoryCourse primary key (id))
;

create table tbCourse (
  id                        varchar(255) not null,
  id_course                 varchar(255),
  name_th                   varchar(255),
  name_eng                  varchar(255),
  detial_th                 longtext,
  detail_eng                longtext,
  required_courses          varchar(255),
  amount                    integer,
  group_course_id           varchar(255),
  constraint pk_tbCourse primary key (id))
;

create table tbCourseAssessmentStrategies (
  id                        varchar(255) not null,
  detail                    varchar(255),
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbCourseAssessmentStrategies primary key (id))
;

create table tbCourseDestination (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbCourseDestination primary key (id))
;

create table CourseID (
  id                        varchar(255) not null,
  course_id                 varchar(255),
  constraint pk_CourseID primary key (id))
;

create table tbCourseObjectives (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbCourseObjectives primary key (id))
;

create table tbCurriculum (
  id                        varchar(255) not null,
  importance                varchar(255),
  developmental_learning_list_id varchar(255),
  developmental_learning_id varchar(255),
  course_id                 varchar(255),
  constraint pk_tbCurriculum primary key (id))
;

create table tbDataHours (
  id                        varchar(255) not null,
  h_describe                varchar(255),
  h_supplementary           varchar(255),
  h_work                    varchar(255),
  h_study                   varchar(255),
  general_data_id           varchar(255),
  constraint pk_tbDataHours primary key (id))
;

create table Data_ListDevelopment (
  id                        varchar(255) not null,
  num_domain                varchar(255),
  curriculum_id             varchar(255),
  general_data_id           varchar(255),
  developmental_learning_id varchar(255),
  constraint pk_Data_ListDevelopment primary key (id))
;

create table Data_MethodEvaluation (
  id                        varchar(255) not null,
  num_domain                varchar(255),
  evaluation_strategy_id    varchar(255),
  general_data_id           varchar(255),
  developmental_learning_id varchar(255),
  constraint pk_Data_MethodEvaluation primary key (id))
;

create table Data_MethodTeaching (
  id                        varchar(255) not null,
  num_domain                varchar(255),
  teaching_strategies_id    varchar(255),
  general_data_id           varchar(255),
  developmental_learning_id varchar(255),
  constraint pk_Data_MethodTeaching primary key (id))
;

create table tbDepartment (
  id                        varchar(255) not null,
  name_th                   varchar(255),
  name_eng                  varchar(255),
  shotname                  varchar(255),
  faculty_id                varchar(255),
  constraint pk_tbDepartment primary key (id))
;

create table tbDetailPlanEvaluation (
  id                        varchar(255) not null,
  evaluation_week           varchar(255),
  rating_scale              varchar(255),
  evaluation_strategy_id    varchar(255),
  evaluation_plan_data_id   varchar(255),
  constraint pk_tbDetailPlanEvaluation primary key (id))
;

create table tbDevelopmentalLearning (
  id                        varchar(255) not null,
  num_domain                varchar(255),
  name                      longtext,
  syllabus_course_id        varchar(255),
  constraint pk_tbDevelopmentalLearning primary key (id))
;

create table tbDevelopmentalLearningList (
  id                        varchar(255) not null,
  list_developmental_learning longtext,
  developmental_learning_id varchar(255),
  constraint pk_tbDevelopmentalLearningList primary key (id))
;

create table tbEvaluationMethodData (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  constraint pk_tbEvaluationMethodData primary key (id))
;

create table tbEvaluationPlanData (
  id                        varchar(255) not null,
  learning_outcome          varchar(255),
  activity                  varchar(255),
  detail                    varchar(255),
  general_data_id           varchar(255),
  constraint pk_tbEvaluationPlanData primary key (id))
;

create table tbEvaluationStrategy (
  id                        varchar(255) not null,
  evaluation_method         longtext,
  developmental_learning_id varchar(255),
  constraint pk_tbEvaluationStrategy primary key (id))
;

create table tbfacultys (
  id                        varchar(255) not null,
  name                      varchar(255),
  shotname                  varchar(255),
  constraint pk_tbfacultys primary key (id))
;

create table tbGeneralData (
  id                        varchar(255) not null,
  suggestion                varchar(255),
  detail                    varchar(255),
  year                      varchar(255),
  term                      varchar(255),
  course_id                 varchar(255),
  instructors_id            varchar(255),
  teacher_id                varchar(255),
  constraint pk_tbGeneralData primary key (id))
;

create table tbGroupCourse (
  id                        varchar(255) not null,
  name                      varchar(255),
  amount                    integer,
  category_course_id        varchar(255),
  constraint pk_tbGroupCourse primary key (id))
;

create table tbImportantDocument (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbImportantDocument primary key (id))
;

create table tbImproveTeachingData (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbImproveTeachingData primary key (id))
;

create table tbInstructors (
  id                        varchar(255) not null,
  term                      varchar(255),
  year                      varchar(255),
  status                    varchar(255),
  room                      varchar(255),
  status_doc                varchar(255),
  syllabus_course_id        varchar(255),
  teacher_id                varchar(255),
  course_id                 varchar(255),
  constraint pk_tbInstructors primary key (id))
;

create table tbLearningResourcesData (
  id                        varchar(255) not null,
  important_information     longtext,
  document                  longtext,
  recommended               longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbLearningResourcesData primary key (id))
;

create table tbMainDocument (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbMainDocument primary key (id))
;

create table tbNotifications (
  id                        varchar(255) not null,
  date                      varchar(255),
  term                      varchar(255),
  year                      varchar(255),
  detail                    varchar(255),
  constraint pk_tbNotifications primary key (id))
;

create table tbPlanEvaluationLearning (
  id                        varchar(255) not null,
  developmental_learning_id varchar(255),
  general_data_id           varchar(255),
  constraint pk_tbPlanEvaluationLearning primary key (id))
;

create table tbPlanTeachingData (
  id                        varchar(255) not null,
  week                      varchar(255),
  hours                     varchar(255),
  media                     varchar(255),
  instructor                varchar(255),
  detail                    longtext,
  general_data_id           varchar(255),
  constraint pk_tbPlanTeachingData primary key (id))
;

create table tbReviewAndPlan (
  id                        varchar(255) not null,
  detail                    varchar(255),
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbReviewAndPlan primary key (id))
;

create table tbSchedule (
  id                        varchar(255) not null,
  year                      varchar(255),
  term                      varchar(255),
  date                      datetime,
  teacher_id                varchar(255),
  constraint pk_tbSchedule primary key (id))
;

create table tbStandardReview (
  id                        varchar(255) not null,
  detail                    varchar(255),
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbStandardReview primary key (id))
;

create table tbSuggustionDocument (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbSuggustionDocument primary key (id))
;

create table tbSyllabusCourse (
  id                        varchar(255) not null,
  name_th                   varchar(255),
  name_eng                  varchar(255),
  constraint pk_tbSyllabusCourse primary key (id))
;

create table TandY (
  id                        varchar(255) not null,
  t                         varchar(255),
  y                         varchar(255),
  syllabus_course_id        varchar(255),
  constraint pk_TandY primary key (id))
;

create table tbTeacher (
  id                        varchar(255) not null,
  name                      varchar(255),
  sername                   varchar(255),
  status                    varchar(255),
  position                  varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  department_id             varchar(255),
  constraint pk_tbTeacher primary key (id))
;

create table tbTeachingMethodsData (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  constraint pk_tbTeachingMethodsData primary key (id))
;

create table tbTeachingResultsData (
  id                        varchar(255) not null,
  activity                  varchar(255),
  week_learning_outcome     varchar(255),
  evaluation_method         varchar(255),
  rating_scale              varchar(255),
  detail                    longtext,
  general_data_id           varchar(255),
  constraint pk_tbTeachingResultsData primary key (id))
;

create table tbTeachingStrategies (
  id                        varchar(255) not null,
  teaching_methods          longtext,
  developmental_learning_id varchar(255),
  constraint pk_tbTeachingStrategies primary key (id))
;

create table tbTeachingStrategiesData (
  id                        varchar(255) not null,
  detail                    longtext,
  general_data_id           varchar(255),
  course_id                 varchar(255),
  constraint pk_tbTeachingStrategiesData primary key (id))
;

create table tbTokenLine (
  id                        varchar(255) not null,
  token_line                varchar(255),
  name                      varchar(255),
  status                    varchar(255),
  constraint pk_tbTokenLine primary key (id))
;

create table tbUpdatedAssessmentData (
  imv_id                    varchar(255) not null,
  student_assessment        longtext,
  teaching_strategies       longtext,
  teaching_improvement      longtext,
  repeat                    longtext,
  result                    longtext,
  general_data_id           varchar(255),
  constraint pk_tbUpdatedAssessmentData primary key (imv_id))
;

alter table tbCategoryCourse add constraint fk_tbCategoryCourse_syllabusCourse_1 foreign key (syllabus_course_id) references tbSyllabusCourse (id) on delete restrict on update restrict;
create index ix_tbCategoryCourse_syllabusCourse_1 on tbCategoryCourse (syllabus_course_id);
alter table tbCourse add constraint fk_tbCourse_groupCourse_2 foreign key (group_course_id) references tbGroupCourse (id) on delete restrict on update restrict;
create index ix_tbCourse_groupCourse_2 on tbCourse (group_course_id);
alter table tbCourseAssessmentStrategies add constraint fk_tbCourseAssessmentStrategies_generalData_3 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbCourseAssessmentStrategies_generalData_3 on tbCourseAssessmentStrategies (general_data_id);
alter table tbCourseAssessmentStrategies add constraint fk_tbCourseAssessmentStrategies_course_4 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbCourseAssessmentStrategies_course_4 on tbCourseAssessmentStrategies (course_id);
alter table tbCourseDestination add constraint fk_tbCourseDestination_generalData_5 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbCourseDestination_generalData_5 on tbCourseDestination (general_data_id);
alter table tbCourseDestination add constraint fk_tbCourseDestination_course_6 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbCourseDestination_course_6 on tbCourseDestination (course_id);
alter table CourseID add constraint fk_CourseID_course_7 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_CourseID_course_7 on CourseID (course_id);
alter table tbCourseObjectives add constraint fk_tbCourseObjectives_generalData_8 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbCourseObjectives_generalData_8 on tbCourseObjectives (general_data_id);
alter table tbCourseObjectives add constraint fk_tbCourseObjectives_course_9 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbCourseObjectives_course_9 on tbCourseObjectives (course_id);
alter table tbCurriculum add constraint fk_tbCurriculum_developmentalLearningList_10 foreign key (developmental_learning_list_id) references tbDevelopmentalLearningList (id) on delete restrict on update restrict;
create index ix_tbCurriculum_developmentalLearningList_10 on tbCurriculum (developmental_learning_list_id);
alter table tbCurriculum add constraint fk_tbCurriculum_developmentalLearning_11 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_tbCurriculum_developmentalLearning_11 on tbCurriculum (developmental_learning_id);
alter table tbCurriculum add constraint fk_tbCurriculum_course_12 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbCurriculum_course_12 on tbCurriculum (course_id);
alter table tbDataHours add constraint fk_tbDataHours_generalData_13 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbDataHours_generalData_13 on tbDataHours (general_data_id);
alter table Data_ListDevelopment add constraint fk_Data_ListDevelopment_curriculum_14 foreign key (curriculum_id) references tbCurriculum (id) on delete restrict on update restrict;
create index ix_Data_ListDevelopment_curriculum_14 on Data_ListDevelopment (curriculum_id);
alter table Data_ListDevelopment add constraint fk_Data_ListDevelopment_generalData_15 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_Data_ListDevelopment_generalData_15 on Data_ListDevelopment (general_data_id);
alter table Data_ListDevelopment add constraint fk_Data_ListDevelopment_developmentalLearning_16 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_Data_ListDevelopment_developmentalLearning_16 on Data_ListDevelopment (developmental_learning_id);
alter table Data_MethodEvaluation add constraint fk_Data_MethodEvaluation_evaluationStrategy_17 foreign key (evaluation_strategy_id) references tbEvaluationStrategy (id) on delete restrict on update restrict;
create index ix_Data_MethodEvaluation_evaluationStrategy_17 on Data_MethodEvaluation (evaluation_strategy_id);
alter table Data_MethodEvaluation add constraint fk_Data_MethodEvaluation_generalData_18 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_Data_MethodEvaluation_generalData_18 on Data_MethodEvaluation (general_data_id);
alter table Data_MethodEvaluation add constraint fk_Data_MethodEvaluation_developmentalLearning_19 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_Data_MethodEvaluation_developmentalLearning_19 on Data_MethodEvaluation (developmental_learning_id);
alter table Data_MethodTeaching add constraint fk_Data_MethodTeaching_teachingStrategies_20 foreign key (teaching_strategies_id) references tbTeachingStrategies (id) on delete restrict on update restrict;
create index ix_Data_MethodTeaching_teachingStrategies_20 on Data_MethodTeaching (teaching_strategies_id);
alter table Data_MethodTeaching add constraint fk_Data_MethodTeaching_generalData_21 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_Data_MethodTeaching_generalData_21 on Data_MethodTeaching (general_data_id);
alter table Data_MethodTeaching add constraint fk_Data_MethodTeaching_developmentalLearning_22 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_Data_MethodTeaching_developmentalLearning_22 on Data_MethodTeaching (developmental_learning_id);
alter table tbDepartment add constraint fk_tbDepartment_faculty_23 foreign key (faculty_id) references tbfacultys (id) on delete restrict on update restrict;
create index ix_tbDepartment_faculty_23 on tbDepartment (faculty_id);
alter table tbDetailPlanEvaluation add constraint fk_tbDetailPlanEvaluation_evaluationStrategy_24 foreign key (evaluation_strategy_id) references tbEvaluationStrategy (id) on delete restrict on update restrict;
create index ix_tbDetailPlanEvaluation_evaluationStrategy_24 on tbDetailPlanEvaluation (evaluation_strategy_id);
alter table tbDetailPlanEvaluation add constraint fk_tbDetailPlanEvaluation_evaluationPlanData_25 foreign key (evaluation_plan_data_id) references tbEvaluationPlanData (id) on delete restrict on update restrict;
create index ix_tbDetailPlanEvaluation_evaluationPlanData_25 on tbDetailPlanEvaluation (evaluation_plan_data_id);
alter table tbDevelopmentalLearning add constraint fk_tbDevelopmentalLearning_syllabusCourse_26 foreign key (syllabus_course_id) references tbSyllabusCourse (id) on delete restrict on update restrict;
create index ix_tbDevelopmentalLearning_syllabusCourse_26 on tbDevelopmentalLearning (syllabus_course_id);
alter table tbDevelopmentalLearningList add constraint fk_tbDevelopmentalLearningList_developmentalLearning_27 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_tbDevelopmentalLearningList_developmentalLearning_27 on tbDevelopmentalLearningList (developmental_learning_id);
alter table tbEvaluationMethodData add constraint fk_tbEvaluationMethodData_generalData_28 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbEvaluationMethodData_generalData_28 on tbEvaluationMethodData (general_data_id);
alter table tbEvaluationPlanData add constraint fk_tbEvaluationPlanData_generalData_29 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbEvaluationPlanData_generalData_29 on tbEvaluationPlanData (general_data_id);
alter table tbEvaluationStrategy add constraint fk_tbEvaluationStrategy_developmentalLearning_30 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_tbEvaluationStrategy_developmentalLearning_30 on tbEvaluationStrategy (developmental_learning_id);
alter table tbGeneralData add constraint fk_tbGeneralData_course_31 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbGeneralData_course_31 on tbGeneralData (course_id);
alter table tbGeneralData add constraint fk_tbGeneralData_instructors_32 foreign key (instructors_id) references tbInstructors (id) on delete restrict on update restrict;
create index ix_tbGeneralData_instructors_32 on tbGeneralData (instructors_id);
alter table tbGeneralData add constraint fk_tbGeneralData_teacher_33 foreign key (teacher_id) references tbTeacher (id) on delete restrict on update restrict;
create index ix_tbGeneralData_teacher_33 on tbGeneralData (teacher_id);
alter table tbGroupCourse add constraint fk_tbGroupCourse_categoryCourse_34 foreign key (category_course_id) references tbCategoryCourse (id) on delete restrict on update restrict;
create index ix_tbGroupCourse_categoryCourse_34 on tbGroupCourse (category_course_id);
alter table tbImportantDocument add constraint fk_tbImportantDocument_generalData_35 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbImportantDocument_generalData_35 on tbImportantDocument (general_data_id);
alter table tbImportantDocument add constraint fk_tbImportantDocument_course_36 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbImportantDocument_course_36 on tbImportantDocument (course_id);
alter table tbImproveTeachingData add constraint fk_tbImproveTeachingData_generalData_37 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbImproveTeachingData_generalData_37 on tbImproveTeachingData (general_data_id);
alter table tbImproveTeachingData add constraint fk_tbImproveTeachingData_course_38 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbImproveTeachingData_course_38 on tbImproveTeachingData (course_id);
alter table tbInstructors add constraint fk_tbInstructors_syllabusCourse_39 foreign key (syllabus_course_id) references tbSyllabusCourse (id) on delete restrict on update restrict;
create index ix_tbInstructors_syllabusCourse_39 on tbInstructors (syllabus_course_id);
alter table tbInstructors add constraint fk_tbInstructors_teacher_40 foreign key (teacher_id) references tbTeacher (id) on delete restrict on update restrict;
create index ix_tbInstructors_teacher_40 on tbInstructors (teacher_id);
alter table tbInstructors add constraint fk_tbInstructors_course_41 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbInstructors_course_41 on tbInstructors (course_id);
alter table tbLearningResourcesData add constraint fk_tbLearningResourcesData_generalData_42 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbLearningResourcesData_generalData_42 on tbLearningResourcesData (general_data_id);
alter table tbLearningResourcesData add constraint fk_tbLearningResourcesData_course_43 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbLearningResourcesData_course_43 on tbLearningResourcesData (course_id);
alter table tbMainDocument add constraint fk_tbMainDocument_generalData_44 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbMainDocument_generalData_44 on tbMainDocument (general_data_id);
alter table tbMainDocument add constraint fk_tbMainDocument_course_45 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbMainDocument_course_45 on tbMainDocument (course_id);
alter table tbPlanEvaluationLearning add constraint fk_tbPlanEvaluationLearning_developmentalLearning_46 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_tbPlanEvaluationLearning_developmentalLearning_46 on tbPlanEvaluationLearning (developmental_learning_id);
alter table tbPlanEvaluationLearning add constraint fk_tbPlanEvaluationLearning_generalData_47 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbPlanEvaluationLearning_generalData_47 on tbPlanEvaluationLearning (general_data_id);
alter table tbPlanTeachingData add constraint fk_tbPlanTeachingData_generalData_48 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbPlanTeachingData_generalData_48 on tbPlanTeachingData (general_data_id);
alter table tbReviewAndPlan add constraint fk_tbReviewAndPlan_generalData_49 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbReviewAndPlan_generalData_49 on tbReviewAndPlan (general_data_id);
alter table tbReviewAndPlan add constraint fk_tbReviewAndPlan_course_50 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbReviewAndPlan_course_50 on tbReviewAndPlan (course_id);
alter table tbSchedule add constraint fk_tbSchedule_teacher_51 foreign key (teacher_id) references tbTeacher (id) on delete restrict on update restrict;
create index ix_tbSchedule_teacher_51 on tbSchedule (teacher_id);
alter table tbStandardReview add constraint fk_tbStandardReview_generalData_52 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbStandardReview_generalData_52 on tbStandardReview (general_data_id);
alter table tbStandardReview add constraint fk_tbStandardReview_course_53 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbStandardReview_course_53 on tbStandardReview (course_id);
alter table tbSuggustionDocument add constraint fk_tbSuggustionDocument_generalData_54 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbSuggustionDocument_generalData_54 on tbSuggustionDocument (general_data_id);
alter table tbSuggustionDocument add constraint fk_tbSuggustionDocument_course_55 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbSuggustionDocument_course_55 on tbSuggustionDocument (course_id);
alter table TandY add constraint fk_TandY_syllabusCourse_56 foreign key (syllabus_course_id) references tbSyllabusCourse (id) on delete restrict on update restrict;
create index ix_TandY_syllabusCourse_56 on TandY (syllabus_course_id);
alter table tbTeacher add constraint fk_tbTeacher_department_57 foreign key (department_id) references tbDepartment (id) on delete restrict on update restrict;
create index ix_tbTeacher_department_57 on tbTeacher (department_id);
alter table tbTeachingMethodsData add constraint fk_tbTeachingMethodsData_generalData_58 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbTeachingMethodsData_generalData_58 on tbTeachingMethodsData (general_data_id);
alter table tbTeachingResultsData add constraint fk_tbTeachingResultsData_generalData_59 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbTeachingResultsData_generalData_59 on tbTeachingResultsData (general_data_id);
alter table tbTeachingStrategies add constraint fk_tbTeachingStrategies_developmentalLearning_60 foreign key (developmental_learning_id) references tbDevelopmentalLearning (id) on delete restrict on update restrict;
create index ix_tbTeachingStrategies_developmentalLearning_60 on tbTeachingStrategies (developmental_learning_id);
alter table tbTeachingStrategiesData add constraint fk_tbTeachingStrategiesData_generalData_61 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbTeachingStrategiesData_generalData_61 on tbTeachingStrategiesData (general_data_id);
alter table tbTeachingStrategiesData add constraint fk_tbTeachingStrategiesData_course_62 foreign key (course_id) references tbCourse (id) on delete restrict on update restrict;
create index ix_tbTeachingStrategiesData_course_62 on tbTeachingStrategiesData (course_id);
alter table tbUpdatedAssessmentData add constraint fk_tbUpdatedAssessmentData_generalData_63 foreign key (general_data_id) references tbGeneralData (id) on delete restrict on update restrict;
create index ix_tbUpdatedAssessmentData_generalData_63 on tbUpdatedAssessmentData (general_data_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbCategoryCourse;

drop table tbCourse;

drop table tbCourseAssessmentStrategies;

drop table tbCourseDestination;

drop table CourseID;

drop table tbCourseObjectives;

drop table tbCurriculum;

drop table tbDataHours;

drop table Data_ListDevelopment;

drop table Data_MethodEvaluation;

drop table Data_MethodTeaching;

drop table tbDepartment;

drop table tbDetailPlanEvaluation;

drop table tbDevelopmentalLearning;

drop table tbDevelopmentalLearningList;

drop table tbEvaluationMethodData;

drop table tbEvaluationPlanData;

drop table tbEvaluationStrategy;

drop table tbfacultys;

drop table tbGeneralData;

drop table tbGroupCourse;

drop table tbImportantDocument;

drop table tbImproveTeachingData;

drop table tbInstructors;

drop table tbLearningResourcesData;

drop table tbMainDocument;

drop table tbNotifications;

drop table tbPlanEvaluationLearning;

drop table tbPlanTeachingData;

drop table tbReviewAndPlan;

drop table tbSchedule;

drop table tbStandardReview;

drop table tbSuggustionDocument;

drop table tbSyllabusCourse;

drop table TandY;

drop table tbTeacher;

drop table tbTeachingMethodsData;

drop table tbTeachingResultsData;

drop table tbTeachingStrategies;

drop table tbTeachingStrategiesData;

drop table tbTokenLine;

drop table tbUpdatedAssessmentData;

SET FOREIGN_KEY_CHECKS=1;

