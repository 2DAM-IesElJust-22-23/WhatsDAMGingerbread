package com.ieseljust.psp.client.communications;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;
import com.ieseljust.psp.client.ViewModel;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;
import org.json.JSONArray;

public class serverListener implements Runnable {

    /*
     * Aquesta classe s'encarrega de gestionar els broadcasts que fa el servidor
     * cap als clients subscrits a les seues publicacions.
     * Implementarà doncs un servei que escoltarà en un port aleatori el que
     * li envia el servidor de missatgeria i ho processarà de forma adeqüada.
     * 
     */

    ViewModel vm;
    private Reader isr;

    public serverListener(ViewModel vm) {
        this.vm = vm;
    }

    int listenerPort = CurrentConfig.listenPort();

    @Override
    public void run() {
        // 1. Crear un socket de tipus servidor que escolte pel port de recepció de
        // missatges
        ServerSocket listener = null;
        try {
            // Creem el socket en un  port determinat pel sistema
            // i el guardem a listenPort.
            listener = new ServerSocket(0);
            CurrentConfig.setlistenPort(listener.getLocalPort());
            

        } catch (IOException e) {
            System.out.println("El port " + listenerPort + " està ocupat, és inaccessible.");
            return;
        }

        // TO-DO
        // 2. Iniciem un bucle infinit a l'espera de rebre connexions
        // Quan arribe una connexió la processrem de manera adeqüada
        // Les peticions que podme rebre seran de tipus:
        
        // {"type": "userlist", "content": [Llista d'usuaris]}, amb un JSONArray amb la llista d'usuaris.
        // {"type": "message", "user":"usuari", "content": "Contingut del missatge" }
        
        // És interessant implementar un mètode a banda per processat aquestes línies
        // però no cal que creem un fil a propòsit per atendre cada missatge, ja que
        // no som un servidor com a tal, i el que estem fent aci, és mantindre un 
        // canal de recepció només amb el servidor.

        
        while(true){
            
            try {
                Socket socket = listener.accept();
            } catch (IOException ex) {
                Logger.getLogger(serverListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            InputStream is = new InputStream() {
                @Override
                public int read() throws IOException {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            };
            
            BufferedReader bf = new BufferedReader(isr);
            
            String notification = null;
            try {
                notification = bf.readLine();
            } catch (IOException ex) {
                Logger.getLogger(serverListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JSONObject notificationJson = new JSONObject(notification);
            
            String messageType = notificationJson.getString("type");
            
            switch(messageType){
                case "userlist":
                    JSONArray userList = notificationJson.getJSONArray("content");
                    ArrayList<String> listUsers=new ArrayList<>();
                    for (int i =0;i<listUsers.lenght();i++)
                        String user=listUsers.get(i).toString();
                        listUsers.add(user);
                    break;
                case "message":
                    String user = notificationJson.getString("user");
                    String messageContent = notificationJson.getString("content");
                    String message = new Message (user,content);
                    vm.addMessage(message); 
                    break;
                default:
                    break;
                
                
            }

            OutputStream out= socket.GetOutInputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out);
            BufferedWriter bw = new BufferedWriter(osw);
            String not= new PrintWriter(bw);
            not.write("{'status':'ok'}")
            
            bf.close();

        }catch(Exception e){
            //TODO hadle exception
        }
        
        
        
    }

}
