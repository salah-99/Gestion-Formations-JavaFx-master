package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.formations.config.ConnectDB;
import com.formations.models.Formation;
import com.formations.models.Session;
import com.sun.javafx.application.HostServicesDelegate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.HostServices;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



public class MapController  implements Initializable {
	
	@FXML
    private WebView mapit;
	
	@FXML
    private TableView<Formation> tableFrmmap;

    @FXML
    private TableColumn<Formation, Integer> colIdFrmmap;

    @FXML
    private TableColumn<Formation, Integer> colcodeFrmmap;

    @FXML
    private TableColumn<Formation, String> collibelleFrmmap;

    @FXML
    private TableColumn<Formation, String> coldescriptionFrmmap;

    // Affichage formation

    public ObservableList<Formation> getFormationmap(){
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
     void showFrmmap() {
        ObservableList<Formation> list = getFormationmap();
        colIdFrmmap.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id"));
        colcodeFrmmap.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("code"));
        collibelleFrmmap.setCellValueFactory(new PropertyValueFactory<Formation, String>("libelle"));
        coldescriptionFrmmap.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
        tableFrmmap.setItems(list);
        
    }

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//map webview //////
		final WebEngine web = mapit.getEngine();
        String urlWeb = getClass().getResource("map.html").toString();
        web.load(urlWeb);
        
        showFrmmap();
        
        
        tableFrmmap.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
				String form = tableFrmmap.getSelectionModel().getSelectedItem().getLibelle();
				
				Desktop d = Desktop.getDesktop();
		        try {
					d.browse(new URI("http://127.0.0.1:3000/"+form));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}

		});
        
        
        
	}
	
}

	
