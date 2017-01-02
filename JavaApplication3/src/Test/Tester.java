/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Sim.SimCommunicator;

import javax.smartcardio.*;

/**
 * @author arikzil
 */
public class Tester {

    public static void main(String[] args) throws Exception {
        SimCommunicator sim = new SimCommunicator();
        CardTerminal terminal = sim.getTerminal(0);
        Card card = terminal.connect("*");
        System.out.println("card: " + card);
        CardChannel channel = card.getBasicChannel();


        // Send Select Applet command
        System.out.println("{select EF}");
        byte[] fid = {0x3a, (byte) 0xff};
        //ResponseAPDU answers = channel.transmit(new CommandAPDU(0x00, 0xa4, 0x00, 0x00, fid));
        ResponseAPDU answers = channel.transmit(new CommandAPDU(sim.getAPDU("SELECT_MF")));
        //ResponseAPDU answers = channel.transmit(new CommandAPDU(0x00, 0xCA, 0x33, 0x00));
        //ResponseAPDU answers = channel.transmit(new CommandAPDU(0x00, 0xa4, 0x00, 0x00, fid));
        System.out.println("answer2: " + answers.toString());

        // Disconnect the card
        card.disconnect(false);


    }
}
