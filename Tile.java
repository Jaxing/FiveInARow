package tictactoe;

import java.awt.Point;
import javax.swing.JButton;

public class Tile extends JButton {
	public enum TileState {
		EMPTY, CIRCLE, CROSS
		
	}
	private Point point;
	
	private TileState state = TileState.EMPTY;
	
	public Tile(int x, int y){
		super();
		setIcon(null);
		point=new Point(x,y);
	}
	
	public void setTileState(TileState t){
		state=t;
	}
	
	public TileState getTileState(){
		return this.state;
	}
	public Point getPoint(){
		return this.point;
	}
}
