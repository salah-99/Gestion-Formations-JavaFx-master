package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.formations.config.ConnectDB;
import com.formations.models.Formation;

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

public class FormationController implements Initializable {

	@FXML
    private TableView<Formation> tableFrm;

    @FXML
    private TableColumn<Formation, Integer> colIdFrm;

    @FXML
    private TableColumn<Formation, Integer> colcodeFrm;

    @FXML
    private TableColumn<Formation, String> collibelleFrm;

    @FXML
    private TableColumn<Formation, String> coldescriptionFrm;

    @FXML
    private Button btnemp;

    @FXML
    private Button btnsess;

    @FXML
    private Button btnfrm;

    @FXML
    private TextField acodeFrm;

    @FXML
    private TextField alibelleFrm;

    @FXML
    private TextField adescriptionFrm;

    @FXML
    private TextField ucodeFrm;

    @FXML
    private TextField ulibelleFrm;

    @FXML
    private TextField udescriptionFrm;

    @FXML
    private TextField uidFrm;

    @FXML
    private TextField dlidFrm;

    @FXML
    private Button addFrm;

    @FXML
    private Button updateFrm;

    @FXML
    private Button deleteFrm;

    @FXML
    private Button showFrm;

    @FXML
    void addFormation(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
    	
		String sql="INSERT INTO formation (code,libelle,description) VALUES ("+acodeFrm.getText()+",'"+alibelleFrm.getText()+"','"+adescriptionFrm.getText()+"')";

	   	conn.InsertReq(sql);
	   	acodeFrm.clear();
	   	alibelleFrm.clear();
	   	adescriptionFrm.clear();
	   	
	   	showFrm();
    }

    @FXML
    void deleteFormation(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
	   	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
	   	conn.InsertReq("Delete FROM formation WHERE id="+dlidFrm.getText());
	   	dlidFrm.clear();
	   	showFrm();
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

 // Affichage formation

    public ObservableList<Formation> getFormation(){
        ObservableList<Formation> formList = FXCollections.observableArrayList();
        ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
        String sql="SELECT * FROM formation";
        Statement st;
        ResultSet rs;
        try {
        st = conn.connection.createStatement();
        rs = st.executeQuery(sql);
        Formation formation;
        while(rs.next()) {
            formation = new Formation(rs.getInt("id"), rs.getInt("code"), rs.getString("libelle"), rs.getString("description"));
            formList.add(formation);
        }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return formList;
    }


     @FXML
     void showFrm() {
        ObservableList<Formation> list = getFormation();
        colIdFrm.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id"));
        colcodeFrm.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("code"));
        collibelleFrm.setCellValueFactory(new PropertyValueFactory<Formation, String>("libelle"));
        coldescriptionFrm.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        tableFrm.setItems(list);
    }

    @FXML
    void updateFormation(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
		String sql="UPDATE formation SET code="+ucodeFrm.getText()+",libelle='"+ulibelleFrm.getText()+"', description='"+udescriptionFrm.getText()+"' WHERE id="+uidFrm.getText()+"";

	   	conn.InsertReq(sql);
	   	uidFrm.clear();
	   	ucodeFrm.clear();
	   	ulibelleFrm.clear();
	   	udescriptionFrm.clear();
	   	
	   	showFrm();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		showFrm();
	}

}
