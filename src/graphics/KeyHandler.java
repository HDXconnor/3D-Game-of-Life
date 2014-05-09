package src.graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import src.graphics.Perspective;
import cameras.GameCamera;

public class KeyHandler implements KeyListener, ActionListener {
	private int W, A, S, D, Q, E, C, SPACE, ESC, SHIFT, UP, DOWN, LEFT, RIGHT, CONTROL;
	private boolean strafeLeft = false, strafeRight = false, forward = false, back = false,
			tiltR = false, tiltL = false, tiltU = false, tiltD = false, shift = false, space = false, esc = false,
			left = false, right = false, control = false;

	private Timer timer;
	private Perspective perspective;
	private GameCamera camera;
	
	public KeyHandler(Perspective perspective, GameCamera camera){
		this.timer = new Timer(10, this);
		this.timer.start();
		
		this.perspective = perspective;
		this.camera = camera;
		initKeyCodes();
	}
	
	public void initKeyCodes(){
		W = KeyEvent.VK_W;
		A = KeyEvent.VK_A;
		S = KeyEvent.VK_S;
		D = KeyEvent.VK_D;
		Q = KeyEvent.VK_Q;
		C = KeyEvent.VK_C;
		E = KeyEvent.VK_E;
		ESC = KeyEvent.VK_ESCAPE;
		SPACE = KeyEvent.VK_SPACE;
		SHIFT = KeyEvent.VK_SHIFT;
		UP = KeyEvent.VK_UP;
		DOWN = KeyEvent.VK_DOWN;
		LEFT = KeyEvent.VK_LEFT;
		RIGHT = KeyEvent.VK_RIGHT;
		CONTROL = KeyEvent.VK_CONTROL;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(strafeLeft == true){
			camera.updateStatus("SL");
		} if(strafeRight == true){
			camera.updateStatus("SR");
		} if(forward == true){
			camera.updateStatus("F");
		} if(back == true){
			camera.updateStatus("B");
		} if(space == true){
			camera.updateStatus("U");
		} if(shift == true){
			camera.updateStatus("D");
		} if(tiltR == true){
			camera.updateStatus("TR");
		} if(tiltL == true){
			camera.updateStatus("TL");
		} if(tiltU == true){
			camera.updateStatus("TU");
		} if(tiltD == true){
			camera.updateStatus("TD");
		} if(left == true){
			camera.updateStatus("L");
		} if(right == true){
			camera.updateStatus("R");
		} if(control == true){
			camera.updateStatus("CTRL");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == A) {strafeLeft = true; }
		if (key == D) {strafeRight = true; }
		if (key == W) {forward = true; }
		if (key == S) {back = true; }
		if (key == Q) {tiltR = true; }
		if (key == E) {tiltL = true; }
		if (key == C) {perspective.toggleCamera(); System.out.println("pressed c");}
		if (key == SPACE) {space = true; }
		if (key == SHIFT) {shift = true; }
		if (key == LEFT) {left = true;}
		if (key == RIGHT) {right = true;}
		if (key == UP) {tiltU = true;}
		if (key == DOWN) {tiltD = true;}
		if (key == ESC) { perspective.togglePause();}
		if (key == CONTROL) {perspective.selectPointedObject();}
		if (KeyPressTool.isDoublePress(e) && KeyPressTool.lastKeyPressedCode == SPACE) {
		    System.out.println("double pressed " + "space");
		}
	}
	@Override
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		if (key == A) {strafeLeft = false; }
		if (key == D) {strafeRight = false; }
		if (key == W) {forward = false; }
		if (key == S) {back = false; }
		if (key == Q) {tiltR = false; }
		if (key == E) {tiltL = false; }
		if (key == SPACE) {space = false; }
		if (key == SHIFT) {shift = false; }
		if (key == LEFT) {left = false;}
		if (key == RIGHT) {right = false;}
		if (key == UP) {tiltU = false;}
		if (key == DOWN) {tiltD = false;}
	}
	@Override
	public void keyTyped(KeyEvent e){ }
	
}