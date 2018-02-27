/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Fos_User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author BSS
 */
public class ReserverTicketController implements Initializable {

    @FXML
    private JFXButton idreservation;

    @FXML
    private JFXTextField utlisateur;

    @FXML
    private JFXTextArea Message;
        Login_viewController loginCQONTROLLER = new Login_viewController();
  Fos_User x;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       x=loginCQONTROLLER.u;
       
       
       utlisateur.setText(x.getUsername());
    }    
    
    @FXML
    void idreservation(ActionEvent event) {
  try {
			// Construct data
                        String user = "username=" + "sarra.boukercha1@gmail.com";
			String apiKey = "apikey=" + "qMoZ9QsZja8-IOLDc1r6EVSWLoqUvgR8Ts7uuAvWmu";
			String message = "Bonjourr," + Message.getText();
			String sender = "sarra";
			String numbers = "0021624174430";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
			String data = user +apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
                             JOptionPane.showMessageDialog(null,"message"+line);
			}
			rd.close();
			
			//return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
                        JOptionPane.showMessageDialog(null,e);
			//return "Error "+e;
		}
	}
    
}
