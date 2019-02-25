import java.util.Random;

public class Feature {

	private int row[];
	private int col[];
	private boolean sgn[];
	private Random random;
	private int dimension = 10;
	private boolean isDummy = false;
	private double weight;

	Feature(boolean isDummy) {
		this.isDummy = isDummy;
		weight = -1;
	}

	Feature() {
		row = new int[4];
		col = new int[4];
		sgn = new boolean[4];
		random = new Random();
		weight = random.nextDouble();
		for (int index = 0; index < 4; index++) {
			
			row[index] = random.nextInt(dimension);
			col[index] = random.nextInt(dimension);
			sgn[index] = random.nextBoolean();
		}

	}
	
	public int evaluateFeature(boolean[][] image) {
		
		if(isDummy) {
			return 1;
		}
		
		int sum = 0;
		for(int index = 0; index <4 ; index++) {
			if(image[row[index]][col[index]] == sgn[index]) sum++ ; 
		}
		
		return (sum >= 3)? 1 : 0;
		
		
		
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int[] getRow() {
		return row;
	}

	public int[] getCol() {
		return col;
	}

	public boolean isDummy() {
		return isDummy;
	}

	public void setRow(int[] row) {
		this.row = row;
	}

	public void setCol(int[] col) {
		this.col = col;
	}

	public boolean[] getSgn() {
		return sgn;
	}

	public void setSgn(boolean[] sgn) {
		this.sgn = sgn;
	}

	public void setDummy(boolean isDummy) {
		this.isDummy = isDummy;
	}
	
	
}
