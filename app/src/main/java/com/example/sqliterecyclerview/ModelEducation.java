package com.example.sqliterecyclerview;

public class ModelEducation {

    String  skill, study, instName,dateEnd;
    String eduId;

    public String getEduId() {
        return eduId;
    }

    public void setEduId(String eduId) {
        this.eduId = eduId;
    }

    public ModelEducation(String eduId,  String skill,String study,String instName,String dateEnd){
        this.eduId=eduId;
        this.skill = skill;
        this.study = study;
        this.instName = instName;
        this.dateEnd = dateEnd;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
