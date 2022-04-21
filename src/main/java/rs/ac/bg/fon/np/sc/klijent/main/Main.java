/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.np.sc.klijent.main;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.np.sc.klijent.forme.PrijaviSeForma;
import rs.ac.bg.fon.np.sc.klijent.komunikacija.Komunikacija;

/**
 *
 * @author UrosVesic
 */
public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        try (InputStreamReader in = new InputStreamReader(classloader.getResourceAsStream("konfiguracija.json"))) {
            JsonObject obj = gson.fromJson(in, JsonObject.class);
            String ip = obj.get("ip").getAsString();
            int port = obj.get("port").getAsInt();
            Socket socket = new Socket(ip, port);
            Komunikacija.getInstanca().setSocket(socket);
            PrijaviSeForma f = new PrijaviSeForma();
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
