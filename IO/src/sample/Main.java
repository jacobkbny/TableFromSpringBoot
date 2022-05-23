package sample;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//데이트를 다른 곳으로 전송할 목적으로 만드는 클래스는 반드시 Serializeable 인터페이스를 implements 해야 함.
// 이런 작업을 직렬화 라고 함.

class Data implements Serializable{
	public String name;
	public int num;
	
}


public class Main {

	public static void main(String[] args) {
		Data data = new Data();
		data.name = "pkb";
		data.num = 10;
		
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./sample.data"))){
			oos.writeObject(data);
			oos.flush();
		}catch(Exception e){
				System.out.println(e.getLocalizedMessage());
				e.printStackTrace();
		}
		
		
		
		
		
//		String name ="pkb";
//		int num = 10;
//		
//		//위에 내용을 파일에 기록 
//		try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("./file.data"))){
//			dos.write(num);
//			dos.writeChars(name);
//			
//		}catch(Exception e) {
//				System.out.println(e.getLocalizedMessage());
//			
//		}
	}

}
