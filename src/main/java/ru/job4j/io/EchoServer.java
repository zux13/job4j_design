package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                boolean exitMsg;
                try (BufferedWriter output = new BufferedWriter(
                            new OutputStreamWriter(socket.getOutputStream()));
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {

                    String startingLine = input.readLine();
                    String message = extractMessage(startingLine);
                    exitMsg = "Exit".equals(message);

                    output.write("HTTP/1.1 200 OK\r\n\r\n");
                    output.write(exitMsg ? "Shutting down... Bye bye" : message);
                    output.flush();

                    System.out.println(startingLine);
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        System.out.println(string);
                    }
                }
                if (exitMsg) {
                    server.close();
                }
            }
        }
    }

    private static String extractMessage(String startingLine) {

        Pattern pattern = Pattern.compile("GET /\\?msg=([^ ]+) HTTP/1\\.1");
        Matcher matcher = pattern.matcher(startingLine);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }
}