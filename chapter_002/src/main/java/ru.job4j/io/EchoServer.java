package ru.job4j.io;

public class EchoServer {
/*
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());
    @SuppressWarnings("checkstyle:main")
    public static void main(String[] args) {

        boolean startServer = true;
        String result = "";
        boolean flagAny = false;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (startServer) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        if (str.contains("/?msg=Exit")) {
                            startServer = false;
                        }
                        if (!flagAny) {
                          result = str.split("=")[1].split(" ")[0];
                          flagAny = true;
                        }
                    }
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (flagAny) {
                            out.write(result.getBytes());
                            flagAny = false;
                        }
                    }
                }
            } catch (IOException e) {
            LOG.error("Exception in log", e);
        }
        }
 */
    }
