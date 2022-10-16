package se.rtinnovation.poc.test1;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class CSVTest1 {
    public static void main(String[] args) {
        try {
            Reader in = new FileReader("/Users/rasse/Downloads/products_export_1-8.csv");
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(CSVTestEnum.class).parse(in);
            for (CSVRecord record : records) {
                String id = record.get(CSVTestEnum.HANDLE);
                String customerNo = record.get(CSVTestEnum.TITLE);
                String name = record.get(CSVTestEnum.BODY);
                System.out.println(String.format("Output is %s %s %s    ", id, customerNo, name));
                for (var enm : CSVTestEnum.values()){
                    System.out.println("label=" + enm.label + " name=" +  enm.name());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (CSVPrinter printer = new CSVPrinter(new FileWriter("/Users/rasse/Downloads/products_export_1-8_generated.csv"), CSVFormat.EXCEL)) {
                printer.printRecord(CSVTestEnum.values());

            printer.printRecord("id", "userName", "firstName");
            printer.printRecord(1, "john73", "John", "Doe");
            printer.println();
            printer.printRecord(2, "mary", "Mary", "Meyer");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
