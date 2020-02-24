package com.metricssuite.model;

import java.io.*;

public class Project implements Serializable
{
    private String projectName;
    private String productName;
    private String creatorName;
    private String comments;
    private String language;

    private FunctionPoint functionPoint;

    /**
     * Default constructor
     */
    public Project()
    {

    }

    /**
     * Constructor with parameters
     *
     * @param projectName
     * @param productName
     * @param creatorName
     * @param comments
     * @param language
     */
    public Project(String projectName, String productName, String creatorName, String comments, String language)
    {
        this.projectName = projectName;
        this.productName = productName;
        this.creatorName = creatorName;
        this.comments = comments;
        this.language = language;
        this.functionPoint = null;
    }

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

    public FunctionPoint getFunctionPoint()
    {
        return functionPoint;
    }

    public void setFunctionPoint(FunctionPoint functionPoint)
    {
        this.functionPoint = functionPoint;
    }

    /**
     * Method to write to a file
     * @param fileName the file name to write to
     */
    public void writeProject(String fileName)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(this);

            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Method to read a project from given file name
     *
     * @param fileName the file name to read from
     * @return the project from the file
     */
    public static Project readProject(String fileName)
    {
        Project object = null;

        try
        {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            object = (Project) objectInputStream.readObject();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return object;
    }
}
