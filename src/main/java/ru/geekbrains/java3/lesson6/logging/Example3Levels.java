package ru.geekbrains.java3.lesson6.logging;

import java.io.IOException;
import java.util.logging.*;

public class Example3Levels {
    // OFF
    // SEVERE
    // WARNING
    // INFO
    // CONFIG
    // FINE
    // FINER
    // FINEST
    // ALL

    private static final Logger logger = Logger.getLogger(Example3Levels.class.getName());

    public static void main(String[] args) throws IOException {
        Handler h = new FileHandler("log3.log");
        h.setFormatter(new SimpleFormatter());
        h.setLevel(Level.INFO);
        logger.setLevel(Level.FINEST);
        logger.addHandler(h);
        logger.log(Level.SEVERE, "SEVERE");
        logger.log(Level.INFO, "INFO");
        logger.log(Level.CONFIG, "CONFIG");
    }
}
