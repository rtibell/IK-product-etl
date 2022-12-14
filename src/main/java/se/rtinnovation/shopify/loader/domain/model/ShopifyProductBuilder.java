package se.rtinnovation.shopify.loader.domain.model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShopifyProductBuilder {
    public static List<ShopifyProductDTO> build(String fileName) {
        var prodList = new ArrayList<ShopifyProductDTO>();
        try {
            Reader in = new FileReader(fileName);
            var enumLength = ShopifyHeaderEnum.values().length;
            var vct = new String[enumLength];
            int cnt = 0;
            for (var item : ShopifyHeaderEnum.values()) {
                vct[cnt++] =item.label;
            }
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader(vct).withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                var dto = new ShopifyProductDTO(record);
                prodList.add(dto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prodList;
    }


    public static boolean createImportFile(List<ShopifyProductDTO> productDTOList, String fileName) {
        boolean retState = false;

        var numFields = ShopifyHeaderEnum.values().length;
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(fileName), CSVFormat.EXCEL)) {
            var headList = new String[numFields];
            int cnt = 0;
            for (var item : ShopifyHeaderEnum.values()) {
                headList[cnt++] =item.label;
            }
            printer.printRecord(headList);
            for (var recod : productDTOList) {
                cnt = 0;
                var fieldList = new Object[numFields];
                for (var item : ShopifyHeaderEnum.values()) {
                    fieldList[cnt++] = recod.get(item.name());
                }
                printer.printRecord(fieldList);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        retState = true;
        return retState;
    }

    public static Map<String, ShopifyProductDTO> createProductMap(List<ShopifyProductDTO> list) {
        var map = list.stream().collect(Collectors.toMap(ShopifyProductDTO::getId, Function.identity()));
        return map;
    }
}
