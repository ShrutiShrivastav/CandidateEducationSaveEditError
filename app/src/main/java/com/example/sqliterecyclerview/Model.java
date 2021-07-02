package com.example.sqliterecyclerview;

public class Model {

    String  title, name, dateFrom,dateTo;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Model(String id, String title, String name, String dateFrom, String dateTo)
    {
        this.id=id;
      this.title=title;
      this.name=name;
      this.dateFrom=dateFrom;
      this.dateTo=dateTo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }
}
