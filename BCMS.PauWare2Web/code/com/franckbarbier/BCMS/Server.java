/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franckbarbier.BCMS;

import java.io.StringReader;

/**
 *
 * @author tbah
 */
public class Server {
     /* Danger: 'My_ServerEndpoint' constructor must be accessed by the WebSockets server. Don't forget 'static'! */
    
    
    
    @javax.websocket.server.ServerEndpoint(value = "/Server")
    public static class My_ServerEndpoint { // It *MUST* be 'public'!
        public static java.util.HashMap<String, String> msg=new java.util.HashMap<String, String>();
        @javax.websocket.OnClose
        public void onClose(javax.websocket.Session session, javax.websocket.CloseReason close_reason) {
            System.out.println("onClose: " + close_reason.getReasonPhrase());
        }

        @javax.websocket.OnError
        public void onError(javax.websocket.Session session, Throwable throwable) {
            System.out.println("onError: " + throwable.getMessage());
        }
        
        @javax.websocket.OnMessage
        public void onMessage(javax.websocket.Session session, String message) {
            System.out.println("Message from client: " + message);
           
            javax.json.stream.JsonParserFactory factory = javax.json.Json.createParserFactory(null);
            
            javax.json.stream.JsonParser parser = factory.createParser(new StringReader(message));
            String thekey="",thevalue="";
            while(parser.hasNext()){
                javax.json.stream.JsonParser.Event event = parser.next();
                
                switch(event){
                    case KEY_NAME:
                        thekey=parser.getString();
                        break;
                    case VALUE_STRING:
                        thevalue=parser.getString();
                        break;
                }
                if(thekey!="" && thevalue!=""){
                    msg.put(thekey, thevalue);
                    
                }
                
            }
        }

        @javax.websocket.OnOpen
        public void onOpen(javax.websocket.Session session, javax.websocket.EndpointConfig ec) throws java.io.IOException {
            System.out.println("OnOpen... " + ec.getUserProperties().get("Author"));
            session.getBasicRemote().sendText("{\"Handshaking\": \"Yes\"}");
            session.getBasicRemote().sendText("{\"Handshaking\": \"Yes1\"}");
            
        }
    }
    
    
}
