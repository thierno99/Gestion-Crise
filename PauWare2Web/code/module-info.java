module PauWare2Web {
    exports com.pauware.pauware2web;
    /**
     * Outside Java 11 modules
     */
    requires java.desktop;
    requires java.logging;
    requires java.net.http;
    /**
     * This is required by PlantUML on the '--module-path' execution mode:
     */
    requires java.scripting;
    /**
     * Downloaded modules
     */
    // (WARNING): automatic modules can see classes from the classpath. They also export all its packages so there is no encapsulation:
    requires java.json;
    requires net.sourceforge.plantuml;
    /* 'https://nipafx.dev/java-modules-implied-readability' */
    requires javax.websocket.api;
    requires tyrus.server;
// Runtime dependency only ('<scope>runtime</scope>'):
// requires tyrus.container.grizzly.server;
    requires PauWareTwo;
}
