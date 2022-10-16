package se.rtinnovation.shopify;

import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import se.rtinnovation.poc.test1.CSVTestEnum;
import se.rtinnovation.shopify.loader.domain.model.ShopifyHeaderEnum;
import se.rtinnovation.shopify.loader.domain.model.ShopifyPatchBuilder;
import se.rtinnovation.shopify.loader.domain.model.ShopifyProductBuilder;
import se.rtinnovation.shopify.loader.domain.model.ShopifyProductDTO;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class SpotifyCSVETL {
    public static void main(String[] args) {
        Options options = new Options();
        options.addOption("T", "price-template", true, "Create price file template");
        options.addOption("o", "output-import", true, "Create import csv file");
        options.addOption("p", "patch-file", true, "Input patch file");
        options.addRequiredOption("i", "input-file", true, "Input file");
        List<ShopifyProductDTO> list = new ArrayList<ShopifyProductDTO>();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            var inputFileName = cmd.getOptionValue("input-file");
            System.out.println("Loading Shopify pricelist from " + inputFileName);
            list = ShopifyProductBuilder.build(inputFileName);
            list.stream().forEach(x -> System.out.println(x.getId()));

            if (cmd.hasOption("price-template")) {
                var templateFileName = cmd.getOptionValue("price-template");
                System.out.println("Creating template for patch file " + templateFileName);
                ShopifyPatchBuilder.createTemplate(list, templateFileName);
            }

            if (cmd.hasOption("patch-file")) {
                var patchFileName = cmd.getOptionValue("patch-file");
                System.out.println("Loading patch file from " + patchFileName);
                var patchList = ShopifyPatchBuilder.build(patchFileName);
                patchPriceList(list, patchList);
            }

            if (cmd.hasOption("output-import")) {
                var outputFileName = cmd.getOptionValue("output-import");
                System.out.println("Writing Shopify import file to " + outputFileName);
                ShopifyProductBuilder.createImportFile(list, outputFileName);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    private static void patchPriceList(List<ShopifyProductDTO> list, List<ShopifyProductDTO> patchList) {
        var productMap = ShopifyProductBuilder.createProductMap(list);
        patchList.stream().filter(x -> productMap.containsKey(x.getId())).forEach(x -> productMap.get(x.getId()).updatRecord(x));
    }
}
