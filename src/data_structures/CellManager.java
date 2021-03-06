package src.data_structures;

import java.util.Random;
import java.util.Stack;

import com.threed.jpct.Matrix;
import com.threed.jpct.World;

public class CellManager {
	
	private Cell[][][] grid;
	private Stack<Cell> updates;
	private World world;
	private Random rand;
	private int size;
	
	public CellManager(int size, World w){
		grid = new Cell[size][size][size];
		updates = new Stack<Cell>();
		rand = new Random();
		this.size = size;
		this.world = w;
		
		populateGrid();
		populateWorld();
	}

	private void populateGrid(){
		for(int x = 0; x < size; x ++){
			for(int y = 0; y < size; y++){
				for(int z = 0; z < size-1; z++){
					Cell c = new Cell();
					System.out.println(x + " : " + y + " : " + z);
					this.grid[x][y][z] = c;
					c.setCoordinates(x, y, z);
				}
			}
		}
	}
		
	private void populateWorld(){
		int dist = 3;
		float newx = 0; float newy = 0; float newz = 0;
		
		for(int z = 01; z < size-1; z++){
			newz = (float) dist*z;
			for(int y = 0; y < size-1; y++){
				newy = (float) dist*y;
				for(int x = 0; x < size-1; x++){
					newx = (float) dist*x;
					
					Matrix m = new Matrix();
					m.translate(newx, newy, newz);
					
					Cell c = grid[x][y][z];
					c.setTranslationMatrix(m);
					world.addObject(c);
				} 
			}
		}
	}

	
	private void getNeighbors(Cell cell){
		int[] coor = cell.getCoordinates();
		for(int x = coor[0] - 1; x <= coor[0] + 1; x++){
			for(int y = coor[1] - 1; y <= coor[1] + 1; y++){
				for(int z = coor[2] - 1; z <= coor[2] + 1; z++){
					int[] curCoor = {x, y, z};
					if(inRange(curCoor) && !coorEquivalence(coor, curCoor)){
						updates.push(getCell(x, y, z));
					}
				}
			}
		}
	}
	
	
	private boolean inRange(int[] coordinates){
		for(int n : coordinates){
			if(n < 0 || n >= size){return false;}
		} return true;
	}
	
	public Cell getCell(int x, int y, int z){
		return grid[x][y][z];
	}
	
	private boolean coorEquivalence(int[] x, int[] y){
		for(int n = 0; n < x.length; n++){
			if(x[n] != y[n]){return false;}
		} return true;
	}
	
	private int generateIndex(){
		return (int) Math.floor(rand.nextDouble()*size);
	}
}
