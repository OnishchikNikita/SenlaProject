package application;

import entity.Data;

import java.io.IOException;
import java.util.Scanner;

public class Session {
    public static void runSession() throws IOException {
        Data data = new Data();
        Scanner in = new Scanner(System.in);
        Authorization.runAuthorization(data, in);
        OptionManager.processOption(data, in);
        in.close();
    }
}
