package se.rtinnovation.shopify.loader.domain.model;

public enum ShopifyHeaderEnum {
    HANDLE("Handle"),                                                        //Patch
    TITLE("Title"),                                                          //Patch
    BODY("Body (HTML)"),
    VENDOR("Vendor"),                                                       //Patch
    PRODUCT_CATEGORY("Product Category"),                                   //Patch
    TYPE("Type"),                                                           //Patch
    TAGS("Tags"),                                                           //Patch
    PUBLISHED("Published"),                                                 //Patch
    OPTION1_NAME("Option1 Name"),                                           //Patch
    OPTION1_VALUE("Option1 Value"),                                         //Patch
    OPTION2_NAME("Option2 Name"),                                           //Patch
    OPTION2_VALUE("Option2 Value"),                                         //Patch
    OPTION3_NAME("Option3 Name"),                                           //Patch
    OPTION3_VALUE("Option3 Value"),                                         //Patch
    VARIANT_SKU("Variant SKU"),                                             //Patch
    VARIANT_GRAMS("Variant Grams"),                                         //Patch
    VARIANT_INVENTORY_TRACKER("Variant Inventory Tracker"),
    VARIANT_INVENTORY_POLICY("Variant Inventory Policy"),
    VARIANT_FULFILLMENT_SERVICE("Variant Fulfillment Service"),
    VARIANT_PRICE("Variant Price"),                                         //Patch
    VARIANT_COMPARE_AT_PRICE("Variant Compare At Price"),                   //Patch
    VARIANT_REQUIRES_SHIPPING("Variant Requires Shipping"),
    VARIANT_TAXABLE("Variant Taxable"),                                     //Patch
    VARIANT_BARCODE("Variant Barcode"),                                     //Patch
    IMAGE_SRC("Image Src"),                                                 //Patch
    IMAGE_POSITION("Image Position"),                                       //Patch
    IMAGE_ALT_TEXT("Image Alt Text"),
    GIFT_CARD("Gift Card"),
    SEOTitle("SEO Title"),
    SEODescription("SEO Description"),
    GOOGLE_SHOPPING_PRODUCT_CATEGORY("Google Shopping / Google Product Category"),
    GOOGLE_SHOPPING_GENDER("Google Shopping / Gender"),
    GOOGLE_SHOPPING_AGE_GROUP("Google Shopping / Age Group"),
    GOOGLE_SHOPPING_MPN("Google Shopping / MPN"),
    GOOGLE_SHOPPING_ADWORDS_GROUPING("Google Shopping / AdWords Grouping"),
    GOOGLE_SHOPPING_ADWORDS_LABELS("Google Shopping / AdWords Labels"),
    GOOGLE_SHOPPING_CONDITIONS("Google Shopping / Condition"),
    GOOGLE_SHOPPING_CUSTOM_PRODUCT("Google Shopping / Custom Product"),
    GOOGLE_SHOPPING_Custom_Label_0("Google Shopping / Custom Label 0"),
    GOOGLE_SHOPPING_Custom_Label_1("Google Shopping / Custom Label 1"),
    GOOGLE_SHOPPING_Custom_Label_2("Google Shopping / Custom Label 2"),
    GOOGLE_SHOPPING_Custom_Label_3("Google Shopping / Custom Label 3"),
    GOOGLE_SHOPPING_Custom_Label_4("Google Shopping / Custom Label 4"),
    VARIANT_IMAGE("Variant Image"),
    VARIANT_WEIGHT_UNIT("Variant Weight Unit"),                             //Patch
    VARIANT_TAX_CODE("Variant Tax Code"),
    CostPerItem("Cost per item"),                                           //Patch
    PRICE_INTERNATIONAL("Price / International"),
    COMPARE_AT_PRICE_INTERNATIONAL("Compare At Price / International"),
    STATUS("Status");                                                       //Patch

    public final String label;


    private ShopifyHeaderEnum(String label) {
        this.label = label;
    }


}
