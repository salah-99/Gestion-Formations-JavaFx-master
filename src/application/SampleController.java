package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

import javax.swing.JOptionPane;

import com.formations.config.ConnectDB;

public class SampleController {

    @FXML
    private TextField logintext;

    @FXML
    private Button loginbtn;

    @FXML
    private PasswordField mdptext;

    @FXML
    void login() throws Exception {
        String loginUser = logintext.getText();
        String mdpUser = mdptext.getText();
        if(loginUser.equals("admin") && mdpUser.equals("admin"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage window = (Stage) loginbtn.getScene().getWindow();
            Scene scene = new Scene(root,800,600);
            window.setScene(scene);
        }
        else {
                ConnectDB conn = new ConnectDB();
    		   	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
                String sql="SELECT * FROM employe where login='"+loginUser +"' and mdp='"+ mdpUser+"'";
                Statement st;
                ResultSet rs;
                try {
                st = conn.connection.createStatement();
                rs = st.executeQuery(sql);
                if(rs.next())
                {
                	Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
            		Stage window = (Stage) loginbtn.getScene().getWindow();
            		Scene scene = new Scene(root,800,600);
            		window.setScene(scene);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Login ou mot de passe incorrect !");
                }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }

    }

}
