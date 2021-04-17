package rolling_averages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** @author amyquinn */
public class RollingAverageConverter {

  /** @param averaging_period */
	
  private static final int AVERAGING_PERIOD = 600;
  
  //read data from CSV file
  //calculate rolling average using the data array list
  //create a line graph to display both rolling average and input values.
  
  public static void main(String[] args) {

    String file = "example.csv";
    
    ArrayList<InputData> dataFromFile = loadCSV(file);
    
    dataFromFile = UpdateDataPerSecond(dataFromFile);

    ArrayList<RollingAverage> rollingAverage = calculate_rolling_average(dataFromFile, AVERAGING_PERIOD);
    
    LineChart.draw(dataFromFile, rollingAverage);
    
  }

  // update the data to reflect the value per second and return an updated ArrayList 
  private static ArrayList<InputData> UpdateDataPerSecond(ArrayList<InputData> dataFromFile) {
 
    int time;
    int input;

    for (int i = 0; i < dataFromFile.size() - 1; i++) {
      if (dataFromFile.get(i + 1).getTime() != dataFromFile.get(i).getTime() + 1) {

        time = dataFromFile.get(i).getTime() + 1;
        input = dataFromFile.get(i).getInput();

        InputData data = new InputData(time, input);
        dataFromFile.add(i + 1, data);
      }
    }
    return dataFromFile;
  }

  // return ArrayList of rolling Averging data
  public static ArrayList<RollingAverage> calculate_rolling_average(
		  ArrayList<InputData> dataFromFile, int AVERAGING_PERIOD) {

    ArrayList<RollingAverage> rolling_averages = new ArrayList<RollingAverage>();

    int maxInput = calcMaxInput(dataFromFile);
    int start = 0;
    int end = AVERAGING_PERIOD;
    int total = 0;
    int average = 0;

    
    //create the parameter to calculate the 600 second moving average by splitting the ArrayList initially from 0 - 600;
    //iterate through the parameter to calculate the moving average for each second
    
    while (end < dataFromFile.size()) {
      List<InputData> parameter = dataFromFile.subList(start, end);
      for (int i = 0; i < parameter.size(); i++) {
        total += parameter.get(i).getInput();
        average = total / AVERAGING_PERIOD;
      }

      // create the new Rolling Average object showing the 600 second average for each second and add to the
      // ArrayList<RollingAverage>
      
      RollingAverage RA =
          new RollingAverage(maxInput, dataFromFile.get(end).getTime(), average);
      rolling_averages.add(RA);
      
      // increment the start and end values to get the next 600 second rolling average, resetting total to zero to get the correct total.
      start++;
      end++;
      total = 0;
    }
    return rolling_averages;
  }

  // calculate Max input from ArrayList<DataObject>
  private static int calcMaxInput(ArrayList<InputData> dataFromFile) {

    int inputValue;

    int maxInput = dataFromFile.get(0).getInput();

    for (int i = 0; i < dataFromFile.size(); i++) {
      inputValue = dataFromFile.get(i).getInput();

      if (inputValue > maxInput) {
        maxInput = inputValue;
      }
    }
    return maxInput;
  }
  
  // read CSV file and return data as ArrayList<InputData>
  
  public static ArrayList<InputData> loadCSV(String file) {

    ArrayList<InputData> dataFromFile = new ArrayList<InputData>();

    try {

      FileReader fileReader = new FileReader(file);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      String data = bufferedReader.readLine();

      String dataArray[];

      data = bufferedReader.readLine();

      while (data != null) {
        dataArray = data.split(",");
        int time = (int) Double.parseDouble(dataArray[1]);
        int input = (int) Double.parseDouble(dataArray[2]);

        InputData inputData = new InputData(time, input);

        dataFromFile.add(inputData);

        data = bufferedReader.readLine();
      }

      bufferedReader.close();
      fileReader.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {

      e.printStackTrace();
    }
    return dataFromFile;
  }
}
