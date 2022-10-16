package se.rtinnovation.shopify.loader.domain.model;

import org.apache.commons.csv.CSVRecord;
import se.rtinnovation.poc.test1.CSVTestEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShopifyProductDTO {
    public static final List<ShopifyHeaderEnum> KEY_ENUMS = Arrays.asList(
            ShopifyHeaderEnum.HANDLE,
            ShopifyHeaderEnum.OPTION1_VALUE,
            ShopifyHeaderEnum.OPTION2_VALUE,
            ShopifyHeaderEnum.OPTION3_VALUE);

    public static final List<ShopifyHeaderEnum> PATCH_FIELDS_ENUMS = Arrays.asList(
            ShopifyHeaderEnum.HANDLE,
            ShopifyHeaderEnum.TITLE,
            ShopifyHeaderEnum.VENDOR,
            ShopifyHeaderEnum.PRODUCT_CATEGORY,
            ShopifyHeaderEnum.TYPE,
            ShopifyHeaderEnum.TAGS,
            ShopifyHeaderEnum.PUBLISHED,
            ShopifyHeaderEnum. OPTION1_NAME,
            ShopifyHeaderEnum.OPTION1_VALUE,
            ShopifyHeaderEnum.OPTION2_NAME,
            ShopifyHeaderEnum.OPTION2_VALUE,
            ShopifyHeaderEnum.OPTION3_NAME,
            ShopifyHeaderEnum.OPTION3_VALUE,
            ShopifyHeaderEnum.VARIANT_SKU,
            ShopifyHeaderEnum.VARIANT_GRAMS,
            ShopifyHeaderEnum.VARIANT_PRICE,
            ShopifyHeaderEnum.VARIANT_COMPARE_AT_PRICE,
            ShopifyHeaderEnum.VARIANT_TAXABLE,
            ShopifyHeaderEnum.VARIANT_BARCODE,
            ShopifyHeaderEnum.IMAGE_SRC,
            ShopifyHeaderEnum.IMAGE_POSITION,
            ShopifyHeaderEnum.VARIANT_WEIGHT_UNIT,
            ShopifyHeaderEnum.CostPerItem,
            ShopifyHeaderEnum.STATUS);

    private final HashMap attributeMap;

    public ShopifyProductDTO(CSVRecord record) {
        attributeMap = new HashMap<String, Object>();
        for (var enm : ShopifyHeaderEnum.values()) {
            System.out.println(String.format("Name=%s label=%s value=%s", enm.name(), enm.label,  record.get(enm.label)));
            if (enm.label != null && record.get(enm.label) != null) attributeMap.put(enm.name(), record.get(enm.label));
        }
    }

    public String getId() {
        var sb = new StringBuffer();
        for (var iter : KEY_ENUMS) {
            sb.append(attributeMap.get(iter.name()));
            sb.append("_");
        }
        return sb.toString();
    }

    public Object get(String id) {
        var item = attributeMap.get(id);
        return item;
    }
}
