package edu.javaLessons.net;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.SimpleTimeZone;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            SimpleClient sc = new SimpleClient();
            sc.start();
        }
       }
    }

        class SimpleClient extends Thread {
            public void run() {
                try {
                    System.out.println("Thread is starter " + LocalDateTime.now());
                    Socket socket = new Socket("127.0.0.1", 25225);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    String sb = "Pavel";
                    bw.write(sb);
                    bw.newLine();
                    bw.flush();

                    String answer = bf.readLine();
                    System.out.println(answer);

                    System.out.print("Thread is finished:  " + LocalDateTime.now());
                    System.out.println();

                    bf.close();
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }

