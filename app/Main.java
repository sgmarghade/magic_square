import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by swapnil.marghade on 04/08/15.
 * Logic to solve magic square can be found in https://www.math.hmc.edu/funfacts/ffiles/10001.4-8.shtml
 */
public class Main {
    String filePath = "/Users/swapnil.marghade/tmp.csv";
    Integer squareSize = 5001;
    public static void main(String [] args){

        new Main().run();

    }

    public void run(){
        int[][] square = new int[squareSize][squareSize];
        // Initialize all squares to -1 for check.

        for(int i = 0; i<squareSize;i++){
            for(int j = 0; j<squareSize ; j++){
                square[i][j] = -1;
            }
        }

        Integer currentRow = 0;
        Integer currentColumn = (int)Math.ceil(squareSize/2);
        Integer currentValue = 1;
        addToLocation(square,currentRow,currentColumn,currentValue);

        while(currentValue < (squareSize * squareSize)){
            currentValue++;
            List<Integer> list = getNextCurrentValueAndRow(square,currentRow,currentColumn);
            currentRow = list.get(0);
            currentColumn = list.get(1);
//            System.out.println(" + "+currentRow+" + "+currentColumn);
            addToLocation(square,currentRow,currentColumn,currentValue);
           // printSquare(square);
        }


        //printSquare(square);
        try {
            writeToFile(square);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToFile(int[][] square) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for(int i = 0; i<squareSize;i++){
            for(int j = 0; j<squareSize ; j++){
                writer.append(""+square[i][j]+",");
            }
            writer.append("\n");
        }
        writer.flush();
        writer.close();
    }

    private List<Integer> getNextCurrentValueAndRow(int[][] square, Integer currentRow, Integer currentColumn) {
        Integer nextRow = currentRow - 1 < 0 ? squareSize - 1 : currentRow - 1;
        Integer nextColumn = currentColumn + 1 == squareSize ? 0 : currentColumn + 1;
        if(square[nextRow][nextColumn] == -1){
            currentRow = nextRow;
            currentColumn = nextColumn;
        }else{
            currentRow = currentRow + 1 == squareSize ? 0 : currentRow + 1;
        }
        List<Integer> list  = new ArrayList<Integer>(2);
//        System.out.println(" - "+currentRow+" - "+currentColumn);
        list.add(currentRow);
        list.add(currentColumn);
        return list;
    }

    private void printSquare(int[][] square) {
        for(int i = 0; i<squareSize;i++){
            for(int j = 0; j<squareSize ; j++){
                System.out.print("" + square[i][j] + ",");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void addToLocation(int[][] square, Integer row, Integer column,int value) {
        square[row][column] = value;
    }
}
