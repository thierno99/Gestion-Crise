package com.pauware.pauware2web;

final class Run_to_completion {

    /**
     * JSON
     */
    final static String Allowed_events = "allowed_events";
    final static String Exits = "exits";
    final static String Disactivated_states = "disactivated_states";
    final static String Activated_transitions = "activated_transitions";
    final static String Entries = "entries";
    final static String Dos = "dos";
    final static String Activated_states = "activated_states";
    final static String True_invariants = "true_invariants";
    final static String Untrue_invariants = "untrue_invariants";

    javax.json.JsonObject _jSON;
    final String _verbose;
    final java.util.HashMap<com.pauware.pauware_engine.Core.Transition, Object[]> _execution;

    @SuppressWarnings("unchecked")
    Run_to_completion(final String state_machine_name, final String verbose, final java.util.HashMap<com.pauware.pauware_engine.Core.Transition, Object[]> execution) {
        _verbose = verbose;
        // Shallow copy only, danger?:
        _execution = execution == null ? null : (java.util.HashMap<com.pauware.pauware_engine.Core.Transition, Object[]>) execution.clone();
        _toJSON(state_machine_name);
    }

    private void _toJSON(final String state_machine_name) {
        javax.json.JsonArrayBuilder allowed_events = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder exits = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder disactivated_states = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder activated_transitions = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder entries = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder dos = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder activated_states = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder true_invariants = javax.json.Json.createArrayBuilder();
        javax.json.JsonArrayBuilder untrue_invariants = javax.json.Json.createArrayBuilder();
        String verboses[] = _verbose.split("\n");
        for (int i = 0; i < verboses.length; i++) {
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Allowed_event_display_message)) {
                String allowed_event = verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Allowed_event_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Allowed_event_display_message).length());
                allowed_events.add(com.pauware.pauware_engine.Core.AbstractState.Allow_header_text + allowed_event.substring(0, allowed_event.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Exit_action_display_message)) {
                String exit = verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Exit_action_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Exit_action_display_message).length());
                exits.add(com.pauware.pauware_engine.Core.AbstractState.Exit_header_text + exit.substring(0, exit.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Disactivated_state_display_message)) {
                disactivated_states.add(com.pauware.pauware_engine.Core.AbstractState.Clean_up(verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Disactivated_state_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Disactivated_state_display_message).length())));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Entry_action_display_message)) {
                String entry = verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Entry_action_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Entry_action_display_message).length());
                entries.add(com.pauware.pauware_engine.Core.AbstractState.Entry_header_text + entry.substring(0, entry.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Do_activity_display_message)) {
                String a_do = verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Do_activity_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Do_activity_display_message).length());
                dos.add(com.pauware.pauware_engine.Core.AbstractState.Do_header_text + a_do.substring(0, a_do.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Activated_state_display_message)) {
                activated_states.add(com.pauware.pauware_engine.Core.AbstractState.Clean_up(verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Activated_state_display_message) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Activated_state_display_message).length())));
            }
            if (verboses[i].contains(com.pauware.pauware_engine.Core.AbstractState.Invariant_header_text)) {
                String invariant = verboses[i].substring(verboses[i].indexOf(com.pauware.pauware_engine.Core.AbstractState.Invariant_header_text) + String.valueOf(com.pauware.pauware_engine.Core.AbstractState.Invariant_header_text).length());
                if (invariant.substring(invariant.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator) + 1).equals("true")) {
                    true_invariants.add(com.pauware.pauware_engine.Core.AbstractState.Invariant_header_text + invariant.substring(0, invariant.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
                } else {
                    untrue_invariants.add(com.pauware.pauware_engine.Core.AbstractState.Invariant_header_text + invariant.substring(0, invariant.indexOf(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator)));
                }
            }
        }
        if (_execution != null) {
            for (java.util.Iterator<com.pauware.pauware_engine.Core.Transition> i = _execution.keySet().iterator(); i.hasNext();) {
                com.pauware.pauware_engine.Core.Transition transition = i.next();
                Object[] actions = _execution.get(transition);
                String transition_text = verboses[0].substring(verboses[0].lastIndexOf('.') + 1);
                for (int j = 0; j < actions.length; j++) {
                    transition_text += com.pauware.pauware_engine.Core.AbstractState.Neutral_character + ((com.pauware.pauware_engine.Core.AbstractAction) actions[j]).to_UML();
                }
                activated_transitions.add(com.pauware.pauware_engine.Core.AbstractState.Clean_up(transition_text + transition.hashCode()));
            }
        }
        javax.json.JsonObjectBuilder builder = javax.json.Json.createObjectBuilder();
        builder.add("state_machine_name", state_machine_name);
        builder.add("verbose", _verbose);
        builder.add(Allowed_events, allowed_events);
        builder.add(Exits, exits);
        builder.add(Disactivated_states, disactivated_states);
        builder.add(Activated_transitions, activated_transitions);
        builder.add(Entries, entries);
        builder.add(Activated_states, activated_states);
        builder.add(Dos, dos);
        builder.add(True_invariants, true_invariants);
        builder.add(Untrue_invariants, untrue_invariants);
        _jSON = builder.build();
//        System.out.println("verbose: " + _jSON.getString("verbose"));
    }
}

public final class PauWare2Web_client implements com.pauware.pauware_engine.Core.AbstractStateMachine_listener, java.net.http.WebSocket.Listener {

    /**
     * PauWare
     */
    volatile private long _animation_frequency = 3000L; // Adjust the slider range in 'index.html' accordingly...
    volatile private boolean _pause = false;

    private final java.util.concurrent.ConcurrentLinkedQueue<Run_to_completion> _run_to_completion = new java.util.concurrent.ConcurrentLinkedQueue<>();
    private final java.util.concurrent.ScheduledExecutorService _to_send_service = java.util.concurrent.Executors.newScheduledThreadPool(1);
    private java.util.concurrent.ScheduledFuture<?> _to_send = null;
    private com.pauware.pauware_engine.Core.AbstractStateMachine _state_machine = null;
    private String _state_machine_name = null;
    /**
     * PlantUML
     */
    private final static String _PlantUML_start = "@startuml\n";
    private final static String _PlantUML_end = "\n@enduml";
    /**
     * Web
     */
    private java.nio.file.Path _index_html_file = null;
    private final static String _PauWare2Web_HTML_section = "<!--PauWare2Web-->";
    /**
     * WebSockets
     */
    private String _log = "";

    protected String get_log() {
        return _log;
    }
    private java.net.http.WebSocket _ws = null;
    // private static Class<?> _Server = null;

    static {
        try {
            // Launch WebSockets server ('PauWare2Web_server' static initializer) by loading the class in charge:
            /* _Server = _Server == null ? null : */
            Class.forName("com.pauware.pauware2web.PauWare2Web_server");
            /**
             * For info. only, no bug if executed 2 times:
             */
            /* Class.forName("com.pauware.pauware2web.PauWare2Web_server"); */
        } catch (ClassNotFoundException cnfe) {
            java.util.logging.Logger.getLogger(PauWare2Web_client.class.getSimpleName()).log(java.util.logging.Level.SEVERE, "Fatal error -static- " + PauWare2Web_server.class.getSimpleName() + ":\t", cnfe);
        }
    }

//    public PauWare2Web_client() {
//    }
    @Override
    public void onOpen(java.net.http.WebSocket ws) {
        _log += "\t" + _state_machine_name + " CONNECTED..." + '\n';
        java.net.http.WebSocket.Listener.super.onOpen(ws);
    }

    @Override
    public void onError(java.net.http.WebSocket ws, Throwable t) {
        _log += "\t" + _state_machine_name + " (onError): " + t.getMessage() + '\n';
        java.net.http.WebSocket.Listener.super.onError(ws, t);
    }

    // WebSockets server (non-blocking) callback:
    @Override
    public java.util.concurrent.CompletionStage<?> onText(java.net.http.WebSocket ws, CharSequence data, boolean last) {
        // 'data.equals({"Handshaking": "Yes"})' is server response... It is redundant with 'onPong' below:
        _log += "\t" + _state_machine_name + " (onText): " + data + '\n';
        try {
            javax.json.stream.JsonParser parser = javax.json.Json.createParser(new java.io.StringReader(data.toString()));
            while (parser.hasNext()) {
                javax.json.stream.JsonParser.Event event = parser.next();
                if (event == javax.json.stream.JsonParser.Event.KEY_NAME && parser.getString().equals("animation_frequency")) {
                    while (parser.hasNext()) {
                        event = parser.next();
                        if (event == javax.json.stream.JsonParser.Event.VALUE_NUMBER) {
                            _animation_frequency = Math.round(parser.getBigDecimal().doubleValue() * 1000.D);
                            _reschedule();
                            break;
                        }
                    }
                }
                if (event == javax.json.stream.JsonParser.Event.KEY_NAME && parser.getString().equals("pause")) {
                    while (parser.hasNext()) {
                        event = parser.next();
                        if (event == javax.json.stream.JsonParser.Event.VALUE_TRUE) {
                            _pause = true;
                            _reschedule();
                            break;
                        } else {
                            if (event == javax.json.stream.JsonParser.Event.VALUE_FALSE) {
                                _pause = false;
                                _reschedule();
                                break;
                            }
                        }
                    }
                }
            }
        } catch (javax.json.JsonException je) {
            java.util.logging.Logger.getLogger(PauWare2Web_client.class.getSimpleName()).log(java.util.logging.Level.SEVERE, "Fatal error -onText- " + _state_machine_name, je);
        }
        return java.net.http.WebSocket.Listener.super.onText(ws, data, last);
    }

    /* WebSockets server (non-blocking) callback: */
    @Override
    public java.util.concurrent.CompletionStage<?> onPong(java.net.http.WebSocket ws, java.nio.ByteBuffer message) {
        _log += "\t" + _state_machine_name + " (onPong): " + new String(message.array()) + '\n';
        return java.net.http.WebSocket.Listener.super.onPong(ws, message);
    }

    @Override
    public void post_construct(final com.pauware.pauware_engine.Core.AbstractStateMachine state_machine) {
        _state_machine = state_machine;
        _state_machine_name = com.pauware.pauware_engine.Core.AbstractState.Clean_up(_state_machine.name());
        /**
         * WebSockets connection (+ ping):
         */
        _ws = java.net.http.HttpClient.newBuilder().build().newWebSocketBuilder().buildAsync(java.net.URI.create(PauWare2Web_server.URI() + _state_machine_name), this).join();
        assert (_ws != null && _ws.isOutputClosed() == false);
        _ws.sendPing(java.nio.ByteBuffer.wrap((_state_machine_name + "-post_construct-").getBytes()));
        /**
         * The 'web' directory is created by Maven at compilation time. All
         * execution stuff is inside:
         */
        try {
            // Get template file:
            _index_html_file = java.nio.file.FileSystems.getDefault().getPath("web" + java.io.File.separator + "index.html");
            // Duplicate template file while changing name:
            /* Caution: 'java.nio.file.StandardCopyOption.REPLACE_EXISTING' hides the fact that state machines may have the same name */
            _index_html_file = java.nio.file.Files.copy(_index_html_file, (new java.io.File("web" + java.io.File.separator + _state_machine_name + ".html")).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
            _browse();
        } catch (java.nio.file.InvalidPathException ipe) {
            java.util.logging.Logger.getLogger(PauWare2Web_client.class.getSimpleName()).log(java.util.logging.Level.SEVERE, "Fatal error -post_construct- " + _state_machine_name, ipe);
        } catch (java.io.IOException ioe) {
            java.util.logging.Logger.getLogger(PauWare2Web_client.class.getSimpleName()).log(java.util.logging.Level.SEVERE, "Fatal error -post_construct- " + _state_machine_name, ioe);
        }
        // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ScheduledExecutorService.html
        _to_send = _to_send_service.scheduleAtFixedRate(() -> {
            this._send();
        }, _animation_frequency, _animation_frequency, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    @Override
    public void start(final String verbose) {
        assert (_state_machine_name != null);
        _run_to_completion.add(new Run_to_completion(_state_machine_name, verbose, null));
    }

    @Override
    public void run_to_completion(final String verbose, final java.util.HashMap<com.pauware.pauware_engine.Core.Transition, Object[]> execution) {
        assert (_state_machine_name != null);
        _run_to_completion.add(new Run_to_completion(_state_machine_name, verbose, execution));
    }

    @Override
    public void stop(final String verbose) {
        assert (_state_machine_name != null);
        _run_to_completion.add(new Run_to_completion(_state_machine_name, verbose, null));
    }
    /**
     * 'volatile' is mandatory: it prevents threads to get a copy of
     * '_pre_destroy':
     */
    volatile private boolean _continue = true;

    @Override
    public void pre_destroy() throws Exception {
// Send (possible) remaining run-to-completion cycle data without blocking:
        java.util.concurrent.ExecutorService finalization = java.util.concurrent.Executors.newSingleThreadExecutor();
        finalization.submit(() -> {
            while (_continue); // '_send' changes this variable when run-to-completion cycle data are all sent...
            _to_send_service.shutdownNow(); // Hard stop (compared to 'shutdown'), but in-progress send tasks are supposed to have finished...
            /**
             * Stop WebSockets emission:
             */
            // assert (_ws != null && _ws.isOutputClosed() == false);
            _ws.sendClose(java.net.http.WebSocket.NORMAL_CLOSURE, _state_machine_name + " requests DISCONNECTION..." + '\n').thenRun(() -> _log += "\t" + _state_machine_name + " DISCONNECTED..." + '\n');
            System.out.println(this.get_log());
        });
        finalization.shutdown(); // Await 'finalization' to complete before "true" stop...
    }

    private void _browse() throws java.io.IOException {
        assert (_index_html_file != null);
        String PlantUML = _PlantUML_start + _state_machine.to_PlantUML() + _PlantUML_end;
        /**
         * Test
         */
        System.out.println(PlantUML);
        /**
         * The 'entry/ ^StateMachine.completion' label of UML output states is
         * removed.
         */
        PlantUML = PlantUML.replaceAll(".*"
                + com.pauware.pauware_engine.Core.AbstractState.Entry_header_text
                + "\\" // Because '^' is a special character
                + com.pauware.pauware_engine.Core.AbstractAction.SendSignalAction_symbol
                + com.pauware.pauware_engine.Core.StateMachine.class.getSimpleName()
                + '.'
                + com.pauware.pauware_engine.Core.AbstractState.Completion
                + "[\n\r]", // Remove end of line...
                "");
// Compute SVG:    
        net.sourceforge.plantuml.SourceStringReader reader = new net.sourceforge.plantuml.SourceStringReader(PlantUML);
        java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
        reader.generateImage(baos, new net.sourceforge.plantuml.FileFormatOption(net.sourceforge.plantuml.FileFormat.SVG));
        baos.close();
        String svg_section = baos.toString();
        // Alter SVG:        
        svg_section = svg_section.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>", "");
        svg_section = svg_section.replace("<svg", "<svg id=\"" + _state_machine_name + "\"");
// Remove comments:
        svg_section = svg_section.replaceAll("<!--(.*?)-->", ""); // Unfortunately, line breaks do not match '.'
// So, removing very last comment added by PlantUML SVG generator is also required:
        int lcb = svg_section.lastIndexOf("<!--");
        int lce = svg_section.lastIndexOf("-->");
        svg_section = lcb == -1 || lce == -1 ? svg_section : svg_section.replace(svg_section.substring(lcb, lce + String.valueOf("-->").length()), "");
        /**
         * For testing purposes only (SVG file is created for possible review)
         */
//        java.nio.file.Path svg = java.nio.file.FileSystems.getDefault().getPath("web" + java.io.File.separator + _state_machine_name + ".svg");
//        java.nio.file.Files.deleteIfExists(svg);
//        java.nio.file.Files.createFile(svg); // It cannot be created if already exists...
//        java.nio.file.Files.write(svg, svg_section.getBytes(), java.nio.file.StandardOpenOption.WRITE);
// Add 'id' for all transition 'text' sections:
        String[] svg_sections = svg_section.split(com.pauware.pauware_engine.Core.AbstractState.Textual_view_subject_separator);
        svg_section = "";
        for (int i = 0; i < svg_sections.length; i += 3) {
            if (i != svg_sections.length - 1) {
                int position_of_last_less_than = svg_sections[i].lastIndexOf("<text");
                if (position_of_last_less_than != -1) {
                    /**
                     * UML guards within square brackets are removed from
                     * transition text 'id'
                     */
                    int position_of_last_left_square_bracket = svg_sections[i + 1].indexOf('[');
                    int position_of_last_right_square_bracket = svg_sections[i + 1].indexOf(']');
                    String transition_text_id = null;
                    if (position_of_last_left_square_bracket != -1 && position_of_last_right_square_bracket != -1) {
                        transition_text_id = svg_sections[i + 1].substring(0, position_of_last_left_square_bracket) + svg_sections[i + 1].substring(position_of_last_right_square_bracket + 1, svg_sections[i + 1].length());
                    } else {
                        transition_text_id = svg_sections[i + 1];
                    }
                    /**
                     * End of UML guards
                     */
                    /**
                     * The 'completion' label of UML completion events is made
                     * hidden. Caution: this code also makes invisible any
                     * '[guard]/action(s)' or any '/action(s)' associated with
                     * the completion event.
                     */
                    if (svg_sections[i + 1].equals(com.pauware.pauware_engine.Core.AbstractState.Completion) || svg_sections[i + 1].equals(com.pauware.pauware_engine.Core.AbstractState.Completion + '[') || svg_sections[i + 1].equals(com.pauware.pauware_engine.Core.AbstractState.Completion + '/')) {
//                        svg_sections[i] = svg_sections[i].replaceFirst("<text ", "<text display=\"none\" ");
                        svg_sections[i + 1] = svg_sections[i + 1].replace(com.pauware.pauware_engine.Core.AbstractState.Completion, "");
                    }
                    /**
                     * End of UML completion events
                     */
                    svg_section += svg_sections[i].substring(0, position_of_last_less_than) + "<text id=\"" + com.pauware.pauware_engine.Core.AbstractState.Clean_up(transition_text_id + svg_sections[i + 2]) + "\"" + svg_sections[i].substring(position_of_last_less_than + String.valueOf("<text").length()) + svg_sections[i + 1];
                }
            } else {
                svg_section += svg_sections[i];
            }
        }
        // Insert SVG source in HTML file:
        java.util.List<String> lines = java.nio.file.Files.readAllLines(_index_html_file, java.nio.charset.Charset.defaultCharset());
        for (String line : lines) {
            if (line.contains("<title>PauWare2Web software</title>")) {
                lines.set(lines.indexOf(line), "<title>PauWare2Web software: " + _state_machine_name + "</title>");
            }
            if (line.contains(_PauWare2Web_HTML_section)) {
                lines.set(lines.indexOf(line), svg_section);
                break;
            }
        }
// Overwrite HTML file:
        java.nio.file.Files.write(_index_html_file, lines, java.nio.charset.Charset.defaultCharset());
// Launch browser:
        java.awt.Desktop.getDesktop().browse(_index_html_file.toUri());
    }

    private void _reschedule() { // value of '_animation_frequency' has changed...
        _to_send.cancel(false); // 'false' means that any in-progress task is not interrupted...
        // assert (_to_send.isDone());
        if (!_pause) {
            _to_send = _to_send_service.scheduleAtFixedRate(() -> {
                this._send();
            }, _animation_frequency, _animation_frequency, java.util.concurrent.TimeUnit.MILLISECONDS);
        }
    }

    private void _send() {
        assert (_ws != null && _ws.isOutputClosed() == false);
        Run_to_completion run_to_completion = _run_to_completion.poll();
        if (run_to_completion != null) {
            _continue = true;
            assert (run_to_completion._jSON != null);
            // 'run_to_completion._jSON' must not be modified until the 'CompletableFuture' object returned from this method has completed:
            _ws.sendText(run_to_completion._jSON.toString(), true); //  // 'true' means that the data *IS NOT* sent in parts...
//            System.out.println(run_to_completion._jSON.toString());
        } else {
            _continue = false;
        }
    }
}
