/*
    WorldJoin - A plugin to run commands and send messages on world join.
    Copyright (C) 2019-2020 aBooDyy

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.aboodyy.worldjoin.actions;

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
