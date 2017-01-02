/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sim;


import java.util.List;
import javax.smartcardio.*;


/**
 * this class is used for handling the  APDU calls and actions
 *
 * @author arikzil
 */
public class SimCommunicator {


    public SimCommunicator() {
    }

    /**
     * Used for getting all the available  terminals
     *
     * @return All the avaliable terminals
     */
    public CardTerminal[] getAllTerminals() throws javax.smartcardio.CardException {
        TerminalFactory factory = TerminalFactory.getDefault();
        List<CardTerminal> terminals = factory.terminals().list();

        return terminals.toArray(new CardTerminal[terminals.size()]);
    }

    /**
     * Used for getting a specified terminal
     *
     * @param index The index of the terminal
     * @return Specific terminal
     */
    public CardTerminal getTerminal(int index) throws javax.smartcardio.CardException {
        CardTerminal[] terminals = getAllTerminals();
        return terminals[index];
    }

    /**
     * @param command
     * @return
     */
    public byte[] getAPDU(String command) {
        if (command.equals("SELECT_MF")) {
            return (new byte[]{0x00, (byte) 0xa4, 0x00, 0x00, 0x00});
        } else if (command.equals("CAT_DF")) {
            return (new byte[]{0x00, (byte) 0xb0, 0x00, 0x00, 0x00});
        } else if (command.equals("SELECT_DF")) {
            return (new byte[]{0x00, (byte) 0xa4, 0x00, 0x00, 0x00,0x07,0x00});
        } else {
            return (new byte[]{0x00});
        }

    }


}
