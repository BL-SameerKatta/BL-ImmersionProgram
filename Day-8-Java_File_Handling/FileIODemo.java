
import java.io.*;

public class FileIODemo {

    public static void main(String[] args) {

        String fileName = "students.txt";

        try {

            FileWriter writer = new FileWriter(fileName);
            writer.write("Sameer,85\n");
            writer.write("Rahul,90\n");
            writer.write("Priya,95\n");
            writer.close();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line;
            int total = 0;
            int count = 0;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                total += Integer.parseInt(data[1]);
                count++;
            }

            reader.close();

            double average = (double) total / count;

            FileWriter report = new FileWriter("report.txt");
            report.write("Average Marks: " + average);
            report.close();

            System.out.println("Report generated successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("I/O Error occurred.");
        }
    }
}
