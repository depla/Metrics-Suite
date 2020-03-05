package com.metricssuite.model;

import java.io.*;
import java.util.ArrayList;

public class Project implements Serializable
{
    private String projectName;
    private String productName;
    private String creatorName;
    private String comments;

    private ArrayList<FunctionPoint> functionPointArrayList;

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
        this.functionPointArrayList = new ArrayList<FunctionPoint>();
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

    public ArrayList<FunctionPoint> getFunctionPointArrayList()
    {
        return functionPointArrayList;
    }

    public void setFunctionPointArrayList(ArrayList<FunctionPoint> functionPointArrayList)
    {
        this.functionPointArrayList = functionPointArrayList;
    }

    public void addFunctionPoint(FunctionPoint functionPointToAdd)
    {
        this.functionPointArrayList.add(functionPointToAdd);
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

    @Override
    public String toString()
    {
        return "Project{" +
                "projectName='" + projectName + '\'' +
                ", productName='" + productName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
