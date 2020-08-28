package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        boolean startServer = true;
        String result = "";
        String result2 = "";
        String[] resultAny = null;
        boolean flagHello = false;
        boolean flagAny = false;
     //   boolean flag = true;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (startServer) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;

                    while (!(str = in.readLine()).isEmpty())  {
                        if (str.contains("/?msg=Exit")) {
                            startServer = false;
                        }
                       // if (str.contains("/?msg=Hello")) {
                         //   result = "Hello";
                        //    flagHello = true;
                       // }
                        if (!str.contains("/?msg=Hello") && !flagAny) {
                          result2 = str.split("=")[1].split(" ")[0];
                          flagAny = true;
                                                 }


                      //  System.out.println(str);

                    }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                       // if (flagHello) {
                           // out.write(result.getBytes());
                           // flagHello = false;
                      //  }
                        if (flagAny) {
                            out.write(result2.getBytes());
                            flagAny = false;
                        }
                    }



                }
            }
        }
    }
