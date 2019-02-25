
public class Image {

	private boolean [][] Image;
	private int Val; 
	
	public int getVal() {
		return Val;
	}

	public void setVal(int val) {
		Val = val;
	}

	public boolean[][] getImage() {
		return Image;
	}

	public void setImage(boolean[][] image) {
		Image = image;
	}

	Image(boolean[][] Image ,int Val){
		this.Image = Image;
		this.Val = Val;
	}
	
}
