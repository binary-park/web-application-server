package webserver;

import java.net.ServerSocket;
import java.net.Socket;

import controller.Controller;
import controller.ListUserController;
import db.DataBase;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServer {
	private static final Logger log = LoggerFactory.getLogger(WebServer.class);
	private static final int DEFAULT_PORT = 8080;
	
    public static void main(String argv[]) throws Exception {
        User user = new User("wyparks2", "1234", "박원영", "wyaprks2@gmail.com");
        DataBase.addUser(user );

        // 서버소켓을 생성한다. 웹서버는 기본적으로 8080번 포트를 사용한다.
    	
    	try (ServerSocket listenSocket = new ServerSocket(DEFAULT_PORT)) {
    		log.info("Web Application Server started {} port.", DEFAULT_PORT);
            // 클라이언트가 연결될때까지 대기한다.
            Socket connection;
            while ((connection = listenSocket.accept()) != null) {
            	RequestHandler requestHandler = new RequestHandler(connection);
                requestHandler.start();
            }
    	}
    }
}
