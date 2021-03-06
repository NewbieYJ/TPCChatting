package kr.chatting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChatClient extends JFrame implements Runnable {

	JTextArea area; //메세지 출력 공간
	JTextField input;
	JButton send_bt; //메세지 전송
	JPanel south_p;
	
	//서버접속을 위한 객체
	Socket s;
	BufferedReader in;
	PrintWriter out;
	Thread t;
	
	public ChatClient() {
		
		area=new JTextArea();
		this.add(area);
	
		//BorderLayout : 지정된 JPanel생성
		south_p = new JPanel(new BorderLayout());
		south_p.add(input = new JTextField()); //패널객체에 가운데 추가
		south_p.add(send_bt = new JButton("보내기"), BorderLayout.EAST);

		this.add(south_p, BorderLayout.SOUTH);
		
		//이벤트 감지
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				out.println("xx:~~X"); //서버로 전달될경우 break
			}
		});
		
		send_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendData(); //서버로 메세지 전달
			}
			
		});
		
		setBounds(100, 100, 400, 500);
		setVisible(true);
		
		connected();
		
		t=new Thread(this); //서버가 주는 메세지를 감지하여 받음
		t.start();
		
	}//생성자
	
	//===========================================================
	//연결
	private void connected() {
		
		try {
			s=new Socket("172.16.10.15", 3500);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=new PrintWriter(s.getOutputStream(), true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new ChatClient();
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			try {
				String msg=in.readLine(); //대기상태
				if(msg.equals("xx:~~X"))
					break;
				if(msg != null) {
					area.append(msg+"\r\n"); //라인대행
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			closed();
			System.exit(0);
		}
		
	}
	
	private void sendData() {
		String msg = input.getText().trim();
		if(msg.length() > 0){
			out.println(msg); 
		}
		input.setText(" "); //비우기
	}
	
	private void closed() {
		
		try {
			if(out != null) {
				out.close();
			}
			
			if(in != null) {
				in.close();
			}
			
			if(out != null) {
				s.close();
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}