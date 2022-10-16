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

public class ShopifyPatchBuilder {
    public static List<ShopifyProductDTO> build(String fileName) {
        var prodList = new ArrayList<ShopifyProductDTO>();
        try {
            Reader in = new FileReader(fileName);
            var enumLength = ShopifyProductDTO.PATCH_FIELDS_ENUMS.size();
            var vct = new String[enumLength];
            int cnt = 0;
            for (var item : ShopifyProductDTO.PATCH_FIELDS_ENUMS) {
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


    public static boolean createTemplate(List<ShopifyProductDTO> productDTOList, String fileName) {
        boolean retState = false;

        var numFields = ShopifyProductDTO.PATCH_FIELDS_ENUMS.size();
//        var vct = new String[numFields];
//        int cnt = 0;
//        for (var item : ShopifyProductDTO.PATCH_FIELDS_ENUMS) {
//            vct[cnt++] =item.label;
//        }
        try (CSVPrinter printer = new CSVPrinter(new FileWriter(fileName), CSVFormat.EXCEL)) {
            var headList = new String[numFields];
            int cnt = 0;
            for (var item : ShopifyProductDTO.PATCH_FIELDS_ENUMS) {
                headList[cnt++] =item.label;
            }
            printer.printRecord(headList);
            for (var recod : productDTOList) {
                cnt = 0;
                var fieldList = new Object[numFields];
                for (var item : ShopifyProductDTO.PATCH_FIELDS_ENUMS) {
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
}
