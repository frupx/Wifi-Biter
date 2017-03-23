import java.awt.*;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.event.*;


public class GUI extends JFrame{
	static JButton upButton, downButton, leftButton, rightButton, disconnect;
	static JButton connectButton;
	static JButton changeSpeedButton;
	static JLabel ipLabel, portLabel, robotMoveTimeLabel, changeRobotMoveTimeLabel, timeRange;
	static JTextField ipTF, portTF, robotMoveTimeTF;
	static JTextField speedTF;
	static Box connBox;
	JFrame frame= new JFrame();
	JFrame appFrame= new JFrame();
	
	static Client cl;
	
	String message="";
	
	public static void main(String[] args){

		new GUI();
	}
	public GUI(){
		
		frame.setTitle("Connect");
		frame.setLocationRelativeTo(null);
		ipLabel= new JLabel("IP adress: ");
		ipTF= new JTextField("192.168.4.1", 15);
		portLabel= new JLabel("Port: ");
		portTF= new JTextField("1315", 4);
		robotMoveTimeLabel= new JLabel("Robot move time: ");
		robotMoveTimeTF= new JTextField("500",4);
		connectButton = new JButton("Connect");
		ListenForButton lForButton= new ListenForButton();
		connectButton.addActionListener(lForButton);
		
		
		
		JPanel upperPanel= new JPanel();
		connBox= Box.createVerticalBox(); 
		connBox.add(ipLabel);
		connBox.add(ipTF);
		connBox.add(portLabel);
		connBox.add(portTF);
		connBox.add(robotMoveTimeLabel);
		connBox.add(robotMoveTimeTF);
		upperPanel.add(connectButton);
		
		frame.add(connBox, BorderLayout.CENTER);
		frame.add(upperPanel, BorderLayout.SOUTH);
		
		frame.setSize(300,200);
		frame.setVisible(true);
		
		
		
	} //END OF GUI CONSTRUCTOR
	
	private class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==connectButton){
				String ip, port;
				ip= ipTF.getText();
				port= portTF.getText();
				int speed;
				
				speed= Integer.parseInt(robotMoveTimeTF.getText());
				frame.setVisible(false);
				cl= new Client(ip, port, speed);
				
				
				controlApp();
			}
			
			
	}		
		
	}	//END OF LISTENFOR BUTTON CLASS
	
	private class ListenForArrows implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==upButton){
				
				message="1";
				cl.sendMessage(message);
				message="";
				appFrame.setFocusTraversalKeysEnabled(false);
				appFrame.requestFocus();
				
			
			}else if(e.getSource()==downButton){
				
				message="5";
				cl.sendMessage(message);
				message="";
				appFrame.setFocusTraversalKeysEnabled(false);
				appFrame.requestFocus();
				
			}else if(e.getSource()==leftButton){
				
				message="3";
				cl.sendMessage(message);
				message="";
				appFrame.setFocusTraversalKeysEnabled(false);
				appFrame.requestFocus();
				
			}else if(e.getSource()==rightButton){
				
				message="4";
				cl.sendMessage(message);
				message="";
				appFrame.setFocusTraversalKeysEnabled(false);
				appFrame.requestFocus();
				
			}else if(e.getSource()==disconnect){
				Client.closeSocket(); // disconnecting
				appFrame.setVisible(false);
				frame.setVisible(true);
			}else if(e.getSource()==changeSpeedButton){
					int speed=Integer.parseInt(speedTF.getText());
					cl.sendChangeSpeedMessage(speed);
					appFrame.setFocusTraversalKeysEnabled(false);
					appFrame.requestFocus();
					
			}
				

				
			}
			
		}
		
	

	
	public void controlApp(){

		appFrame.setSize(500, 500);
		appFrame.setVisible(true);
		appFrame.setLocationRelativeTo(null);
		appFrame.setTitle("Control Panel");
		//upButton, downButton, leftButton, rightButton
		
		upButton	= makeAButton("/up.png");
		downButton	= makeAButton("/down.png"); 
		leftButton	= makeAButton("/left.png");
		rightButton	= makeAButton("/right.png");
		appFrame.add(upButton, BorderLayout.NORTH);
		appFrame.add(downButton, BorderLayout.SOUTH);
		appFrame.add(leftButton, BorderLayout.WEST);
		appFrame.add(rightButton, BorderLayout.EAST);
		
		disconnect= new JButton("Disconnect");
		ListenForArrows lForArrows= new ListenForArrows();
		disconnect.addActionListener(lForArrows);
		disconnect.setBackground(Color.WHITE);
		Box appBox= Box.createVerticalBox();
		appBox.add(disconnect);
		
		//JPanel mainPanel= new JPanel();
		changeRobotMoveTimeLabel=new JLabel("Change robot move ");
		timeRange= new JLabel("time range 1-9999 ms");
		
		speedTF=new JTextField("",5);
		changeSpeedButton= new JButton("Change");
		changeSpeedButton.addActionListener(lForArrows);
		//mainPanel.add(speedTF);
		//mainPanel.add(changeSpeedButton);
		appBox.add(changeRobotMoveTimeLabel);
		appBox.add(timeRange);
		appBox.add(speedTF);
		appBox.add(changeSpeedButton);
		
		appFrame.add(appBox, BorderLayout.CENTER);
		
		//appFrame.add(disconnect, BorderLayout.CENTER);
		
		ListenForKeys lForKeys= new ListenForKeys();
		//ipTF.addKeyListener(lForKeys);
		appFrame.setFocusable(true);
		appFrame.setFocusTraversalKeysEnabled(false);
		appFrame.requestFocus();
		appFrame.addKeyListener(lForKeys);

		
		appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appFrame.setBackground(Color.WHITE);
		appFrame.setResizable(false);
		
	}
	
	private class ListenForKeys extends JPanel implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
		    int keyCode = e.getKeyCode();
		   
		} 

		@Override
		public void keyPressed(KeyEvent e) {
			    int keyCode = e.getKeyCode();
			    switch( keyCode ) { 
			        case KeyEvent.VK_UP:
			        	message="1";
						cl.sendMessage(message);
						message="";
			            break;
			        case KeyEvent.VK_DOWN:
			        	message="5";
						cl.sendMessage(message);
						message="";
			            break;
			        case KeyEvent.VK_LEFT:
			        	message="3";
						cl.sendMessage(message);
						message="";
			            break;
			        case KeyEvent.VK_RIGHT :
			        	message="4";
						cl.sendMessage(message);
						message="";
			            break;
			     }
			} 

			
		

		@Override
		public void keyReleased(KeyEvent e) {

			
		}
		
	}
	public JButton makeAButton(String iconFile){
		JButton tempBut= new JButton();
		URL url = GUI.class.getResource(iconFile);
		ImageIcon butIcon = new ImageIcon(url);
		tempBut.setIcon(butIcon);
		tempBut.setBackground(Color.white);
		ListenForArrows lForArrows= new ListenForArrows();
		tempBut.addActionListener(lForArrows);
		return tempBut;
	}
} //END OF GUI CLASS

