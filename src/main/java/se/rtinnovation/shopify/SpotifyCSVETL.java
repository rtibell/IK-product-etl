package se.rtinnovation.shopify;

import org.apache.commons.cli.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import se.rtinnovation.poc.test1.CSVTestEnum;
import se.rtinnovation.shopify.loader.domain.model.ShopifyHeaderEnum;
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
        options.addRequiredOption("i", "input-file", true, "Input file");
        List<ShopifyProductDTO> list = new ArrayList<ShopifyProductDTO>();

        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            var inputFileName = cmd.getOptionValue("input-file");
            list = ShopifyProductBuilder.build(inputFileName);
            list.stream().forEach(x -> System.out.println(x.getId()));

            if (cmd.hasOption("price-template")) {
                var templateFileName = cmd.getOptionValue("price-template");
                ShopifyProductBuilder.createTemplate(list, templateFileName);
            }

            if (cmd.hasOption("output-import")) {
                var outputFileName = cmd.getOptionValue("output-import");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }
}
