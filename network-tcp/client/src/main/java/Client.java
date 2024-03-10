

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            SimpleClient sc = new SimpleClient(i);
            sc.start();
        }
       }
    }

        class SimpleClient extends Thread {

    private static final String[] COMMAND = {"HELLO", "MORNING", "EVENING", "DAY"};

    private int cmtNumber;

    public SimpleClient(int cmtNumber){
        this.cmtNumber = cmtNumber;
    }
            public void run() {
                try {
                    System.out.println("Thread is starter " + LocalDateTime.now());
                    Socket socket = new Socket("127.0.0.1", 25225);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    String command = COMMAND[cmtNumber % COMMAND.length];

                    String sb = command + "   " + "Pavel";
                    bw.write(sb);
                    bw.newLine();
                    bw.flush();


                    String answer = bf.readLine();
                    System.out.println("Client got string:" + answer);

                    bf.close();
                    bw.close();
                } catch (IOException ex) {
                    ex.printStackTrace(System.out);
                }
            }
        }

