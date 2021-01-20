package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.formations.config.ConnectDB;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SessionController implements Initializable {

	@FXML
    private TableView<Session> tableSess;

    @FXML
    private TableColumn<Session, Integer> colIdSess;

    @FXML
    private TableColumn<Session, Integer> colcodeSess;

    @FXML
    private TableColumn<Session, String> collibelleSess;

    @FXML
    private TableColumn<Session, String> colformationSess;
    @FXML
    private Button btnemp;

    @FXML
    private Button btnsess;

    @FXML
    private Button btnfrm;

    @FXML
    private TextField acodeSess;

    @FXML
    private TextField alibelleSess;

    @FXML
    private TextField ucodeSess;

    @FXML
    private TextField ulibelleSess;

    @FXML
    private TextField uidSess;

    @FXML
    private TextField dlidSess;

    @FXML
    private Button addSess;

    @FXML
    private Button updateSess;

    @FXML
    private Button deleteSess;

    @FXML
    private Button showSess;

    @FXML
    private ComboBox<String> acomboEmp;

    @FXML
    private ComboBox<String> ucomboEmp;

    @FXML
    void addSession(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
    	
		String sql="INSERT INTO session (code,libelle,formation) VALUES ("+acodeSess.getText()+",'"+alibelleSess.getText()+"','"+acomboEmp.getValue()+"')";

	   	conn.InsertReq(sql);
	   	acodeSess.clear();
	   	alibelleSess.clear();
	   	showSess();
	   	
    }

    @FXML
    void combograb(ActionEvent event) {

    }

    @FXML
    void deleteSession(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
	   	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
	   	conn.InsertReq("Delete FROM session WHERE id="+dlidSess.getText());
	   	dlidSess.clear();
	   	showSess();
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

 // Affichage session

    public ObservableList<Session> getSession(){
        ObservableList<Session> sessionList = FXCollections.observableArrayList();
        ConnectDB conn = new ConnectDB();
		conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
        String sql="SELECT * FROM session";
        Statement st;
        ResultSet rs;
        try {
        st = conn.connection.createStatement();
        rs = st.executeQuery(sql);
        Session session;
        while(rs.next()) {
            session = new Session(rs.getInt("id"), rs.getInt("code"), rs.getString("libelle"), rs.getString("formation"));
            sessionList.add(session);
        }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return sessionList;
    }


     @FXML
     void showSess() {
        ObservableList<Session> list = getSession();
        colIdSess.setCellValueFactory(new PropertyValueFactory<Session, Integer>("id"));
        colcodeSess.setCellValueFactory(new PropertyValueFactory<Session, Integer>("code"));
        collibelleSess.setCellValueFactory(new PropertyValueFactory<Session, String>("libelle"));
        colformationSess.setCellValueFactory(new PropertyValueFactory<Session, String>("formation"));
        tableSess.setItems(list);
    }

    @FXML
    void updateSession(ActionEvent event) {
    	ConnectDB conn = new ConnectDB();
    	conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
		String sql="UPDATE session SET code="+ucodeSess.getText()+",libelle='"+ulibelleSess.getText()+"', formation='"+ucomboEmp.getValue()+"' WHERE id="+uidSess.getText()+"";

	   	conn.InsertReq(sql);
	   	ucodeSess.clear();
	   	ulibelleSess.clear();
	   	uidSess.clear();
	   	showSess();
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<String> typeList = FXCollections.observableArrayList();

    	
    	
    	ConnectDB conn = new ConnectDB();
		conn.Connect("jdbc:mysql://localhost/bdgestionformations","root", "phpmysami");
		String sql="SELECT libelle FROM formation";
		Statement st;
		ResultSet rs;
		try {
		st = conn.connection.createStatement();
        rs = st.executeQuery(sql);
        
        while(rs.next()) {
        	typeList.add(rs.getString("libelle"));
        }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		
		acomboEmp.setItems(typeList);
    	ucomboEmp.setItems(typeList);
    	
    	showSess();
	}

}
