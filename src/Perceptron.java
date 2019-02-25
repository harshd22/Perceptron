//took some help from a friend
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class Perceptron {

	Set<Image> images;
	Set<Feature> features;

	public Perceptron(File file) {
		features = new HashSet<>();

		features.add(new Feature(true));
		for (int index = 0; index < 100; index++) {
			features.add(new Feature());
		}

		loadImage(file);
		trainPerceptron();
		display();
	}

	private void display() {
		for (Feature feature : features) {
			if(feature.isDummy()) {
				continue;
			}
			System.out.println("Feature :");
			for(int index = 0; index < 4 ; index++) {
			System.out.print("(" + feature.getRow()[index] + " , " + feature.getCol()[index] + ") = " + feature.getSgn()[index]);
			System.out.print("|");
			}
			System.out.printf("Weight : %.5f\n" ,feature.getWeight());
		}

	}

	private void trainPerceptron() {

		for (int i = 1; i < 1000; i++) {
			int correct = 0;
			for (Image image : images) {
				double outcome = 0;

				for (Feature feature : features) {
					outcome = outcome + feature.evaluateFeature(image.getImage()) * feature.getWeight();
				}
				int actualOutcome = image.getVal();

				if (outcome > 0) {
					if (actualOutcome == 1) {
						correct++;
					} else {
						for (Feature feature : features) {
							feature.setWeight(feature.getWeight() - feature.evaluateFeature(image.getImage()) * 0.2);
						}

					}
				} else {
					if (actualOutcome == 0) {
						
						correct++;
					} else {
						for (Feature feature : features) {
							feature.setWeight(feature.getWeight() + feature.evaluateFeature(image.getImage()) * 0.2);
						}
					}
				}

			}

			if (correct >= images.size()) {
				System.out.println("converged in " + i  + " cycles");
				return;
			}
		}

		System.out.println("No convergence");

	}

	private void loadImage(File file) {
		images = new HashSet<>();
		Scanner scan;
		int Val = 0;
		int row;
		int col;
		boolean[][] img;
		try {
			scan = new Scanner(file);
			while (scan.hasNext()) {
				row = 0;
				col = 0;
				Val = 0;
				if (!scan.next().equals("P1"))
					System.out.println("File not Valid");
				Val = scan.next().substring(1).equals("O") ? 0 : 1;
				row = scan.nextInt();
				col = scan.nextInt();
				img = new boolean[row][col];

				for (int r = 0; r < row; r++) {
					for (int c = 0; c < col; c++) {
						img[r][c] = scan.findWithinHorizon(Pattern.compile("[01]"), 0).equals("1");
					}
				}

				images.add(new Image(img, Val));

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {

		new Perceptron(new File(args[0]));

	}

}
