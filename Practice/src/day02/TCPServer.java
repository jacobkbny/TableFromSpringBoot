package day02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer {
	
	public static void main(String[] args) {
		//클라이언트의 접속을 처리하기 위한 소켓
		ServerSocket ss = null;
		//클라이언트 와 통신을 하기 위한 소켓 
		Socket socket = null;
	try {         
		// 서버 소켓을 생성
        ss = new ServerSocket(9999);
        while(true){
        	System.out.println("server is listening");
        	// 클라이언트의 접속 대기
        	socket = ss.accept();
        	
        	System.out.println("접속자 정보 :"+socket.toString()); // 모든 네트워크 프로그래밍에서는 접속자의 정보를 알수 있음
        	BufferedReader in = new BufferedReader(
        				new InputStreamReader(socket.getInputStream()));
        				// 한 줄 읽기
        				String str = in.readLine();
        				System.out.println("전송된 내용 :"+str);
        				in.close();
        				socket.close();
       		}//while 
        }// try 
     catch(Exception e) {
        System.out.println("소켓 통신 에러");
        System.out.println(e.getLocalizedMessage());
     }
  }

}
