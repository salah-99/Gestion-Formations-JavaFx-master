package com.formations.config;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ConnectDB {
	public Connection connection;
    public Statement statement ;
    public PreparedStatement preparedStatement ;
    public ResultSet resultSet ;

    public ConnectDB() {
        super();
        this.connection = null;
        this.statement = null;
        this.preparedStatement = null;
        this.resultSet = null;
    }

    public ConnectDB(Connection connection, Statement statement, PreparedStatement preparedStatement,
			ResultSet resultSet) {
		super();
		this.connection = connection;
		this.statement = statement;
		this.preparedStatement = preparedStatement;
		this.resultSet = resultSet;
	}

	public String Connect(String url, String user, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
            return "connected";

        } catch (Exception e) {
            // TODO: handle exception
            return "not connected";
        }
    }
	
	
	public void Affichage(String tableName) {

        try {
               String request = "SELECT * FROM "+tableName;
               statement = connection.createStatement();
               resultSet = statement.executeQuery(request);
               ResultSetMetaData resultMeta = (ResultSetMetaData) resultSet.getMetaData();

               for(int i = 1; i <= resultMeta.getColumnCount(); i++) {
                   System.out.print(resultMeta.getColumnName(i).toUpperCase() + " | ");
                }

               while(resultSet.next())
               {
                   System.out.println("\n");
                   for(int i = 1; i <=  resultMeta.getColumnCount(); i++)
                       System.out.print(resultSet.getObject(i).toString()+"\t"); 
                   
               }
               
               
            } 
        catch (SQLException e) {
               e.printStackTrace();
            }

    }
	
	public void AffichageAVG(String tableName) {
		try {
		String request = "SELECT avg(salaire) FROM "+tableName;
        statement = connection.createStatement();
        resultSet = statement.executeQuery(request);
        if(resultSet.next())
        System.out.println("\n\n moyen:" +resultSet.getFloat(1)+ " DH");
		}catch (SQLException e) {
            e.printStackTrace();
         }
	}
	
public void InsertReq(String requete) {
		
        try {
               statement = connection.createStatement();
               int resultSet = statement.executeUpdate(requete);
            } 
        catch (SQLException e) {
               e.printStackTrace();
            }

    }

public void UpdateReq(String requete) {
	
    try {
           statement = connection.createStatement();
           int resultSet = statement.executeUpdate(requete);
        } 
    catch (SQLException e) {
           e.printStackTrace();
        }

}

public void DeleteReq(String requete) {

    try {
           
           statement = connection.createStatement();
           int resultSet = statement.executeUpdate(requete);
        } 
    catch (SQLException e) {
           e.printStackTrace();
        }

}

}
