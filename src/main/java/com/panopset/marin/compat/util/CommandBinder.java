package com.panopset.marin.compat.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Event to command map.
 */
public abstract class CommandBinder {

    /**
     *
     * @return All registered commands.
     */
    public final List<Command> getCommands() {
        return cmds;
    }

    /**
     *
     * @param command
     *            Command to register.
     */
    protected final void registerCommand(final Command command) {
        cmds.add(command);
        reset();
    }

    /**
     * Commands.
     */
    public final List<Command> cmds = new ArrayList<Command>();

    /**
     * Clear any local memory cache's.
     */
    private void reset() {
        prompt = null;
    }

    /**
     * Prompt.
     */
    private String prompt;

    /**
     * @return User prompt for all commands, in order of registration.
     */
    @Override
    public final String toString() {
        if (prompt == null) {
            StringWriter sw = new StringWriter();
            for (Command c : cmds) {
                sw.append(c.toString());
                sw.append(" ");
            }
            prompt = sw.toString();
        }
        return prompt;
    }

    /**
     *
     * Command inner class.
     *
     */
    public static final class Command {

        /**
         * User prompt.
         */
        public final String up;

        /**
         * Action.
         */
        public final String a;

        /**
         * Default key.
         */
        public final int dk;

        /**
         * Key.
         */
        private int key;

        /**
         *
         * @return Key.
         */
        public int getKey() {
            return key;
        }

        /**
         * Set a new user key, usually from some config panel.
         *
         * @param newKey
         *            New key.
         */
        public void setKey(final int newKey) {
            key = newKey;
        }

        /**
         *
         * @param userPrompt
         *            User prompt.
         * @param action
         *            System action String.
         * @param defaultKey
         *            The default key.
         */
        public Command(final String userPrompt, final String action,
                final int defaultKey) {
            up = userPrompt;
            a = action;
            dk = defaultKey;
            key = dk;
        }

        /**
         * @return User prompt for this command.
         */
        @Override
        public String toString() {
            StringWriter sw = new StringWriter();
            sw.append(a.toUpperCase());
            sw.append("=");
            sw.append(up);
            return sw.toString();
        }

        /**
         * An action String is unchanging, the application knows it always. The
         * commander class is a layer that allows configuration of any user
         * interface event to correspond to that action.
         *
         * @return Action String.
         */
        public String getAction() {
            return a;
        }

    }

    /**
     *
     * @param keyCode
     *            Key code.
     * @return Command associated with the keyCode.
     */
    public Command findCommand(final int keyCode) {
        Command nullCommand = new Command("", "", -1);
        for (Command c : getCommands()) {
            if (c.getKey() == keyCode) {
                return c;
            }
        }
        return nullCommand;
    }
}
