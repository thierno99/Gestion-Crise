/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.franckbarbier.BCMS;

/**
 *
 * @author tbah
 */
public class Client implements java.net.http.WebSocket.Listener {
    private String _log = "";

    public String get_log() {
        return _log;
    }

    @Override
    public void onOpen(java.net.http.WebSocket ws) {
        _log += Client.class.getSimpleName() + " CONNECTED..." + '\n';
        // This is equivalent to 'super.onOpen(ws)', but it is appplied to the *IMPLEMENTATION* of 'java.net.http.WebSocket.Listener':
        java.net.http.WebSocket.Listener.super.onOpen(ws);
    }

    @Override
    public void onError(java.net.http.WebSocket ws, Throwable error) {
        _log += "\tonError: " + error.getMessage() + '\n';
        java.net.http.WebSocket.Listener.super.onError(ws, error);
    }
// WebSockets server (non-blocking) callback:
    @Override
    public java.util.concurrent.CompletionStage<?> onText(java.net.http.WebSocket ws, CharSequence data, boolean last) {
        _log += "\tonText: " + data + '\n';
        return java.net.http.WebSocket.Listener.super.onText(ws, data, last);
    }
// WebSockets server (non-blocking) callback:
    @Override
    public java.util.concurrent.CompletionStage<?> onPong(java.net.http.WebSocket ws, java.nio.ByteBuffer message) {
        _log += "\tonPong: " + new String(message.array()) + "Yes, it does..." + '\n';
        return java.net.http.WebSocket.Listener.super.onPong(ws, message);
    }

    public Client(java.util.Optional<String> o) {
        java.net.http.HttpClient hc = java.net.http.HttpClient.newBuilder().build();
        java.net.http.WebSocket.Builder b = hc.newWebSocketBuilder();
        java.net.http.WebSocket ws = b.buildAsync(java.net.URI.create("ws://localhost:1963/FranckBarbier/Server"), this).join();
// Test it:
        ws.sendPing(java.nio.ByteBuffer.wrap("Does 'ping' succeed? ".getBytes()));
// Streaming:
        ws.sendText("Java 11 ", false); // 'false' means that the text is not yet finished...
        ws.sendText("client", true);
        try {
            Thread.sleep(5000); // Wait a bit before closing...
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        ws.sendClose(java.net.http.WebSocket.NORMAL_CLOSURE, Client.class.getSimpleName() + " DISCONNECTED..." + '\n').thenRun(() -> _log += Client.class.getSimpleName() + " DISCONNECTED..." + '\n');
    }
}
