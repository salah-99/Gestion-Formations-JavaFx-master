package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.formations.config.ConnectDB;
import com.formations.models.Employe;
import com.formations.models.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EmployeController implements Initializable {

	

	
	@FXML
    private TableView<Employe> tableEmp;

    @FXML
    private TableColumn<Employe, Integer> colIdEmp;

    @FXML
    private TableColumn<Employe, Integer> colMatriculeEmp;

    @FXML
    private TableColumn<Employe, String> colNomPrenomEmp;

    @FXML
    private TableColumn<Employe, String> colLoginEmp;

    @FXML
    private TableColumn<Employe, String> colVilleEmp;

    @FXML
    private Button btnemp;

    @FXML
    private Button btnsess;

    @FXML
    private Button btnfrm;

    @FXML
    private TextField amatriculeEmp;

    @FXML
    private TextField anomprenomEmp;

    @FXML
    private TextField aloginemp;

    @FXML
    private TextField apasswordEmp;

    @FXML
    private TextField avilleEmp;

    @FXML
    private TextField umatriculeEmp;

    @FXML
    private TextField unomprenomEmp;

    @FXML
    private TextField uloginemp;

    @FXML
    private TextField upasswordEmp;

    @FXML
    private TextField uvilleEmp;

    @FXML
    private TextField uidEmp;

    @FXML
    private TextField dlidEmp;

    @FXML
    private Button addEmp;

    @FXML
    private Button updateEmp;

    @FXML
    private Button deleteEmp;

    @FXML
    private Button showEmp;
    
    

    @FXML
    void addEmploye(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
    	
		String sql="INSERT INTO employe (matricule,nomprenom,login,mdp,ville) VALUES ("+amatriculeEmp.getText()+",'"+anomprenomEmp.getText()+"','"+aloginemp.getText()+"','"+apasswordEmp.getText()+"','"+avilleEmp.getText()+"')";

	   	conn.InsertReq(sql);
	   	amatriculeEmp.clear();
	   	anomprenomEmp.clear();
	   	aloginemp.clear();
	   	apasswordEmp.clear();
	   	avilleEmp.clear();
	   	showEmp();
	 
    }

    @FXML
    void deleteEmploye(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
	   	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
	   	conn.InsertReq("Delete FROM employe WHERE id="+dlidEmp.getText());
	   	dlidEmp.clear();
	   	showEmp();
    }

    @FXML
    void handleBtnemp(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("employe.fxml"));
		Stage window = (Stage) btnemp.getScene().getWindow();
		Scene scene = new Scene(root,800,600);
		window.setScene(scene);
    }

    @FXML
    void handleBtnfrm(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Formation.fxml"));
		Stage window = (Stage) btnfrm.getScene().getWindow();
		Scene scene = new Scene(root,800,600);
		window.setScene(scene);
    }
    

    @FXML
    void handleBtnsess(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("Session.fxml"));
		Stage window = (Stage) btnsess.getScene().getWindow();
		Scene scene = new Scene(root,800,600);
		window.setScene(scene);
    	
    }

 // Affichage employee

    public ObservableList<Employe> getEmploye(){
        ObservableList<Employe> empList = FXCollections.observableArrayList();
        ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
        String sql="SELECT * FROM employe";
        Statement st;
        ResultSet rs;
        try {
        st = conn.connection.createStatement();
        rs = st.executeQuery(sql);
        Employe emp;
        while(rs.next()) {
            emp = new Employe(rs.getInt("id"),rs.getInt("matricule"), rs.getString("nomprenom"), rs.getString("login"), rs.getString("mdp"),  rs.getString("ville"));
            empList.add(emp);
        }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return empList;

    }

    @FXML
    public void showEmp() {
        ObservableList<Employe> list = getEmploye();
        colIdEmp.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("id"));
        colMatriculeEmp.setCellValueFactory(new PropertyValueFactory<Employe, Integer>("matricule"));
        colNomPrenomEmp.setCellValueFactory(new PropertyValueFactory<Employe, String>("nomprenom"));
        colLoginEmp.setCellValueFactory(new PropertyValueFactory<Employe, String>("login"));
        colVilleEmp.setCellValueFactory(new PropertyValueFactory<Employe, String>("ville"));
        tableEmp.setItems(list);
    }

    @FXML
    void updateEmploye(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
		String sql="UPDATE employe SET matricule="+umatriculeEmp.getText()+",nomprenom='"+unomprenomEmp.getText()+"', login='"+uloginemp.getText()+"', mdp='"+upasswordEmp.getText()+"', ville='"+uvilleEmp.getText()+"' WHERE id="+uidEmp.getText()+"";

	   	conn.InsertReq(sql);
	   	uidEmp.clear();
	   	umatriculeEmp.clear();
	   	unomprenomEmp.clear();
	   	uloginemp.clear();
	   	upasswordEmp.clear();
	   	uvilleEmp.clear();
	   	
	   	showEmp();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showEmp();
	}

}
