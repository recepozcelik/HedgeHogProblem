import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HedgeHogProblemSolution {

    public static void main(String[] args) throws Exception{

        final String inputFileName = "./src/main/java/input.txt";
        final String outputFileName = "./src/main/java/output.txt";

        try(BufferedReader br = new BufferedReader(new FileReader(inputFileName))) {

            String line = br.readLine();

            if(line != null) {
                final String[] matrixDimensions = line.trim().split(" ");

                if(matrixDimensions.length != 2){
                    throw new Exception("Column count problem at line: 0");
                }

                final int i = Integer.parseInt(matrixDimensions[0]);
                final int j = Integer.parseInt(matrixDimensions[1]);
                final int[][] apples = new int[i][j];

                for(int a = 0; a < i; a++) {
                    line = br.readLine();
                    if(line != null) {
                        final String[] values = line.trim().split(" ");

                        if(values.length < j){
                            throw new Exception("Column count problem at line: " + (a+2));
                        }

                        for(int b = 0; b < j; b++){
                            apples[a][b] = Integer.parseInt(values[b]);
                        }
                    } else {
                        throw new Exception("Not found enough line to read!");
                    }
                }

                //find max starting with endpoint
                final int maxValue = maxAmountOfApples(apples, i-1, j-1);

                try(FileWriter writer = new FileWriter(outputFileName)){
                    writer.write(String.valueOf(maxValue));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static int maxAmountOfApples(final int apples[][], final int i, final int j) {
        if (i < 0 || j < 0) {
            return -1;
        }

        if (i == 0 && j == 0) { //start point
            return apples[i][j];
        }

        //find max with previous left point
        int totalLeftValue = apples[i][j] + maxAmountOfApples(apples, i - 1, j);
        //find max with previous up point
        int totalUpValue = apples[i][j] + maxAmountOfApples(apples, i, j - 1);

        return Math.max(totalLeftValue, totalUpValue);
    }
}
