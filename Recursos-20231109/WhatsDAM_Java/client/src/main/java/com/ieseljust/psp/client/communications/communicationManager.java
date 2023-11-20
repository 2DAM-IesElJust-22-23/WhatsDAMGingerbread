package com.ieseljust.psp.client.communications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.ieseljust.psp.client.CurrentConfig;
import com.ieseljust.psp.client.Message;

import org.json.JSONException;
import org.json.JSONObject;

public class communicationManager {

    /* Aquesta classe s'encarrega de la gestió de la
     comunicació amb el servidor.
     */
    
    public static JSONObject sendServer(String msg) {

    Socket socket=new Socket();
        InetSocketAddress socketAddr=new InetSocketAddress(CurrentConfig.server(), CurrentConfig.port());

        try {
            socket.connect(socketAddr);
            // Connexió realitzada amb èxit
            // Obtenció dels streams d'entrada i eixida
            InputStream is=socket.getInputStream();
            OutputStream os=socket.getOutputStream();


            // Creem fluxos per a la lectura i escriptura de caràcters
            InputStreamReader isr=new InputStreamReader(is);
            OutputStreamWriter osw=new OutputStreamWriter(os);

            // Creem fluxos per a la lectura i escriptura de línies
            BufferedReader bReader=new BufferedReader(isr);
            PrintWriter pWriter=new PrintWriter(osw);

            // Escrivim al socket el missatge
            pWriter.println(msg);
            pWriter.flush();


           String linia=bReader.readLine();
           /*  while ((linia=bReader.readLine()) != null ){
                System.out.println(linia);
            }*/
            pWriter.close();
            bReader.close();
            isr.close();
            osw.close();
            is.close();
            os.close();

            socket.close();

            return (new JSONObject(linia));

        } catch (IOException e) {
            System.out.println("Excepció en la connexió: "+e.getMessage());
            return null;
        }  
    }

    public static void connect() throws JSONException, communicationManagerException {

        // TO-DO:

        // Creem un misstge pe al servidor amb l'ordre (command) register, 
        // el nom d'usuari (user) i el port (listenPost) pel qual el client escoltarà 
        // les notificacions (el tenim a CurrentConfig.listenPort())

        // Enviarà el missatge al servidor a través de sendServer.

        // Si es produeix un error, llançarà una excepció i aturarà
        // l'aplicaió (per exemple, si l'usuari ja existeix al servidor)
        // Teniu per a això l'excepció communicationManagerException 
        // com a excepció personalitzada al projecte. 
        
        String username = CurrentConfig.username(); 
        int listenPort = CurrentConfig.listenPort(); 

        System.out.println("Listen Port:" +listenPort);
        
        
        JSONObject message = new JSONObject();
        try {
            message.put("command", "register");
            message.put("user", username);
            message.put("listenPort", listenPort);
            System.out.println(message.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            throw new communicationManagerException("Error en la creació del missatge");
        }

        System.out.println("Esperant resposta");
        JSONObject response = sendServer(message.toString());
        System.out.println("Resposta: "+response.toString());

        
        if (response != null) {
            System.out.println(response);
            
        } else {
            throw new communicationManagerException("No s'ha rebut una resposta vàlida del servidor");
        }

    }

    public static void sendMessage(Message m){
        // Envia un misstge al servidor (es fa amb una línia!)
        sendServer(m.toString());
    }    
}