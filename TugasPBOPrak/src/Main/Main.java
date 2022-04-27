package Main;

import Connection.Connector;
import GUI.GUI;

public class Main {
    public static void main(String[] args) {
        Connector connection = new Connector();
        GUI gui = new GUI();
        gui.initialize();
    }
}