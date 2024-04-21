package SupportClasses;

import SupportClasses.InfoList;

import java.io.*;
import java.util.ArrayList;

public class CSVReaderWriter {
    private BufferedReader reader;

    public CSVReaderWriter() {}

    public void assignCSVFile(String filePath) throws FileNotFoundException {
        this.reader = new BufferedReader(new FileReader(filePath));
    }

//    public void printFileContent() throws IOException {
//        String line;
//        while((line = this.reader.readLine()) != null) {
//            System.out.println(this.reader.readLine());
//        }
//    }

    public InfoList getInfoArrays() throws IOException {

        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Integer> sizes = new ArrayList<>();
        ArrayList<Double> times = new ArrayList<>();

        String line;
        while((line = this.reader.readLine()) != null) {

            int start = 0;
            int count = 0;
            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) == ';') {
                    count+=1;
                    String subs = line.substring(start, i);
                    if (count == 1) {
                        ids.add(Integer.parseInt(subs));
                    }
                    if (count == 2) {
                        sizes.add(Integer.parseInt(subs));
                    }
                    start = i+1;
                }

                if (i == line.length()-1) {
                    String subs = line.substring(start, i+1);
                    times.add(Double.parseDouble(subs));
                    break;
                }
            }
        }
        return new InfoList(ids, sizes, times);
    }

    public void writeToFile(ArrayList<Double> measured, ArrayList<Integer> sizes, String filepath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

        for (int i = 0; i < measured.size(); i++) {
            writer.write("0;" + sizes.get(i) + ";" + measured.get(i) + "\n");
        }

        writer.close();
    }
}
