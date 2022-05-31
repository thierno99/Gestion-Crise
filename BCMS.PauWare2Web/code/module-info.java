module BCMS {
    requires java.sql;
    requires java.desktop;
    requires PauWareTwo;
    opens com.franckbarbier.BCMS to PauWareTwo;
    requires java.net.http;
    requires javax.websocket.api;
    requires tyrus.server;
    requires java.json;
}
