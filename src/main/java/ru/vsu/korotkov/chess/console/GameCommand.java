package ru.vsu.korotkov.chess.console;

public enum GameCommand {
    FIELD("get_field"),
    GCOORD("get_coord"),
    GMOVERESULT("set_coord");
    private final String command;
    GameCommand(String command) {
        this.command = command;
    }
    public String getCommand(){
        return command;
    }
}
