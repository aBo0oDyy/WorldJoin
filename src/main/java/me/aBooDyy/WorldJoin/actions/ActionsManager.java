package me.aBooDyy.WorldJoin.actions;

public class ActionsManager {

    private ConsoleAction consoleAction;

    private MessageAction messageAction;

    private PlayerAction playerAction;

    public ActionsManager() {
        consoleAction = new ConsoleAction();
        messageAction = new MessageAction();
        playerAction = new PlayerAction();
    }

    public ConsoleAction getConsoleAction() {
        return consoleAction;
    }

    public MessageAction getMessageAction() {
        return messageAction;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }
}
