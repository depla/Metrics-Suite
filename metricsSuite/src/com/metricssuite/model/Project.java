package com.metricssuite.model;

public class Project
{
    private String projectName;
    private String productName;
    private String creatorName;
    private String comments;
    private String language;

    //private FunctionPoint functionPoint;

    public Project(){}

    public Project(String projectName, String productName, String creatorName, String comments)
    {
        this.projectName = projectName;
        this.productName = productName;
        this.creatorName = creatorName;
        this.comments = comments;
    }

//    public Project(String projectName, String productName, String creatorName, String comments, String language)
//    {
//        this.projectName = projectName;
//        this.productName = productName;
//        this.creatorName = creatorName;
//        this.comments = comments;
//        this.language = language;
//    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName(String projectName)
    {
        this.projectName = projectName;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public String getCreatorName()
    {
        return creatorName;
    }

    public void setCreatorName(String creatorName)
    {
        this.creatorName = creatorName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }
}
