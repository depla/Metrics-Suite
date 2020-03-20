package com.metricssuite.model;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Vector;

public class Project implements Serializable
{
    private String projectName;
    private String productName;
    private String creatorName;
    private String comments;

    private ArrayList<FunctionPoint> functionPointArrayList;
    private  Vector<Vector<String>> SMI;

    /**
     * Default constructor
     */
    public Project()
    {
        projectName = "";
        productName = "";
        creatorName = "";
        comments = "";
        functionPointArrayList = new ArrayList<>();
        //SMI is null for a new project
        SMI = null;
    }

    /**
     * Constructor with parameters
     *
     * @param projectName
     * @param productName
     * @param creatorName
     * @param comments
     */

public Project(String projectName, String productName, String creatorName, String comments)
    {
        this.projectName = projectName;
        this.productName = productName;
        this.creatorName = creatorName;
        this.comments = comments;
        this.functionPointArrayList = new ArrayList<FunctionPoint>();
        this.SMI = null;
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

    public FunctionPoint getFunctionPoint(int index)
    {
        return functionPointArrayList.get(index);
    }

    public void setFunctionPointArrayList(ArrayList<FunctionPoint> functionPointArrayList)
    {
        this.functionPointArrayList = functionPointArrayList;
    }

    public void addFunctionPoint(FunctionPoint functionPointToAdd)
    {
        this.functionPointArrayList.add(functionPointToAdd);
    }

    public void createSMI()
    {
        this.SMI = new Vector<>();
    }

    public void setSMI(Vector<Vector<String>> defaultTableModel)
    {
        this.SMI = defaultTableModel;
    }

    public Vector<Vector<String>> getSMI()
    {
        return this.SMI;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;

        if(this.SMI == null && project.SMI != null)
        {
            return false;
        }

        if(this.SMI != null && project.SMI != null)
        {
            return projectName.equals(project.projectName) &&
                    productName.equals(project.productName) &&
                    creatorName.equals(project.creatorName) &&
                    comments.equals(project.comments) &&
                    functionPointArrayList.equals(project.functionPointArrayList) &&
                    SMI.equals(project.SMI);
        }
        else
        {
            return projectName.equals(project.projectName) &&
                    productName.equals(project.productName) &&
                    creatorName.equals(project.creatorName) &&
                    comments.equals(project.comments) &&
                    functionPointArrayList.equals(project.functionPointArrayList);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectName, productName, creatorName, comments, functionPointArrayList, SMI);
    }
}
