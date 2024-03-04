package edu.javaLessons.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
       for(int i = 0; i<100; i++) {
           sendRequest();
       }
    }

    private static void sendRequest() throws IOException {
        Socket socket = new Socket("127.0.0.1", 25225);
        BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String sb = "Pavel";
        bw.write(sb);
        bw.newLine();
        bw.flush();

        String answer = bf.readLine();
        System.out.println(answer);
        System.out.println("Client got string: " + answer);

        bf.close();
        bw.close();
    }
}
