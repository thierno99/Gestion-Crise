package com.pauware.pauware2web;

public final class PauWare2Web_server {

    @javax.websocket.server.ServerEndpoint(value = "/{state_machine_name}")
    public static class _PauWare2Web_end_point { // It *MUST* be 'public'!

        @javax.websocket.OnClose
        public void onClose(javax.websocket.Session session, javax.websocket.CloseReason close_reason) {
            /**
             * Test
             */
            assert (!session.getPathParameters().values().isEmpty());
            /**
             * End of test
             */
            java.util.Optional<String> closing_peer_name = session.getPathParameters().values().stream().findFirst();
            /**
             * Test
             */
            assert (closing_peer_name.isPresent());
            if (_Trace) {
                System.out.println("(SERVER) 'onClose': " + closing_peer_name.get() + ", reason: " + close_reason.getReasonPhrase());
            }
            if (session.getOpenSessions().isEmpty()) {
                if (_Trace) {
                    System.out.println("\n*** WebSockets server STOP... ***\n");
                }
                _Server.stop();
            }
        }

        @javax.websocket.OnError
        public void onError(javax.websocket.Session session, Throwable throwable) {
            if (_Trace) {
                System.err.println("(SERVER) 'onError': " + throwable.getMessage());
            }
        }

        @javax.websocket.OnMessage
        public void onMessage(@javax.websocket.server.PathParam("state_machine_name") String state_machine_name, javax.websocket.Session sender, String message) throws java.io.IOException {
            if (_Trace) {
                System.out.println("(SERVER) 'OnMessage' from " + state_machine_name + " (id. " + sender.getId() + ") " + message);
            }
// Get the sender session among the opened sessions (check that it obligatorily has PauWare2 id. as first parameter as well):
            java.util.Optional<javax.websocket.Session> sender_session = sender.getOpenSessions().stream().filter(session -> sender.getId() == session.getId() && !session.getPathParameters().values().isEmpty()).findAny();
            /**
             * Test
             */
            assert (sender_session.isPresent());
            /**
             * End of test
             */
            /**
             * sender_session_.ifPresent((sender_session) -> { // Burdensome:
             * dealing with 'java.io.IOException' in lambda is tedious!
             */
            // Not empty by construction, see filter above:
            String sender_name = sender_session.get().getPathParameters().values().iterator().next();
            // Get peers and select the (only) one (Java <-> JavaScript) that has to receive this in-progress message:
            for (java.util.Iterator<javax.websocket.Session> i = sender.getOpenSessions().iterator(); i.hasNext();) {
                javax.websocket.Session session = i.next();
                if (sender.getId() != session.getId()) { // This prevents some echo...
                    /**
                     * Test
                     */
                    assert (!session.getPathParameters().values().isEmpty());
                    /**
                     * End of test
                     */
                    java.util.Optional<String> receiver_name = session.getPathParameters().values().stream().findFirst();
                    /**
                     * Test
                     */
                    assert (receiver_name.isPresent());
                    /**
                     * End of test
                     */
                    if (sender_name.equals(receiver_name.get())) {
                        session.getBasicRemote().sendText(message);
                        if (_Trace) {
                            System.out.println("\tSent to: (id. " + session.getId() + ") ");
                        }
                        break;
                    }
                }
            }
            /**
             * });
             */
        }

        @javax.websocket.OnOpen
        public void onOpen(javax.websocket.Session session, javax.websocket.EndpointConfig ec) throws java.io.IOException {
            /**
             * Test
             */
            assert (!session.getPathParameters().values().isEmpty());
            /**
             * End of test
             */
            java.util.Optional<String> opening_peer_name = session.getPathParameters().values().stream().findFirst();
            /**
             * Test
             */
            assert (opening_peer_name.isPresent());
            if (_Trace) {
                System.out.println("(SERVER) 'OnOpen': " + opening_peer_name.get() + " (id. -" + session.getId() + "- " + session.getQueryString() + ")");
            }
            session.getBasicRemote().sendText("{\"Handshaking\": \"Yes\"}");
        }
    }

    private static org.glassfish.tyrus.server.Server _Server = null;
    private static boolean _Trace = false;

    static {
        if (_Trace) {
            System.out.println("\n*** WebSockets server START... ***\n");
        }
        java.util.Map<String, Object> user_properties = new java.util.HashMap<>();
        user_properties.put("Author", "FranckBarbier");
        _Server = new org.glassfish.tyrus.server.Server("localhost", 1963, "/PauWare2Web", user_properties /* or 'null' */, _PauWare2Web_end_point.class);
        try {
            _Server.start();
        } catch (javax.websocket.DeploymentException de) {
            java.util.logging.Logger.getLogger(PauWare2Web_server.class.getSimpleName()).log(java.util.logging.Level.SEVERE, "Fatal error -static- " + PauWare2Web_server.class.getSimpleName(), de);
            _Server.stop();
        }
    }

    static String URI() {
        return "ws://localhost:1963/PauWare2Web/";
    }
}
