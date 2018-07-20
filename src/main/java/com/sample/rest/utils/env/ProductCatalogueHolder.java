package com.sample.rest.utils.env;

import rest.bundlemanager.dto.productcatalogue.Product;
import rest.bundlemanager.dto.productcatalogue.ProductCatalogueResponse;
import rest.bundlemanager.service.productcatalogue.ProductCatalogueService;

import java.util.concurrent.ConcurrentHashMap;

public class ProductCatalogueHolder {
    private static ProductCatalogueHolder productCatalogueHolder;
    private static ConcurrentHashMap<String, ProductCatalogueResponse> productCatalogueMap;

    private ProductCatalogueHolder() {
    }

    public static ProductCatalogueHolder getInstance() throws Exception {
        if (productCatalogueHolder == null) {
            productCatalogueHolder = new ProductCatalogueHolder();
            productCatalogueMap = new ConcurrentHashMap<>();
        }
        return productCatalogueHolder;
    }

    public void setProductCatalogue(String country, ProductCatalogueResponse productCatalogueResponse) {
        productCatalogueMap.put(country.toLowerCase(), productCatalogueResponse);
    }

    public ProductCatalogueResponse getProductCatalogue(String country) {
        ProductCatalogueResponse productCatalogueResponse;
        productCatalogueResponse = productCatalogueMap.get(country.toLowerCase());
        if (productCatalogueResponse == null) {
            throw new RuntimeException("Unable to get product catalog for not supported country " + country);
        }
        return productCatalogueResponse;
    }


    public static void initProductCatalogue(String country) throws Exception {
        ProductCatalogueResponse pcr = ProductCatalogueService.getInstance().productCatalogueCall(country);
        ProductCatalogueHolder.getInstance().setProductCatalogue(country, pcr);
    }

    public Product getAnyProductByProductType(String country, String productType) throws Exception {
        Product foundProduct;
        ProductCatalogueResponse pc = ProductCatalogueHolder.getInstance().getProductCatalogue(country);
        // We also need take a care to select product with proce more then 0 as Adyen failed in such case and for complex check we don't need products with 0 price at all
        foundProduct = pc.getProducts().stream().filter(product -> product.getProductType().equalsIgnoreCase(productType) && product.getCost() > 0).findAny()
                .orElseThrow(() -> new RuntimeException("Cannot find any product in [" + country + "] product catalogue with ProductType [" + productType + "]"));
        return foundProduct;
    }


    public Product getProductByProductId(String country, String productId) throws Exception {
        Product foundProduct;
        ProductCatalogueResponse pc = ProductCatalogueHolder.getInstance().getProductCatalogue(country);
        foundProduct = pc.getProducts().stream().filter(product -> product.getProductID().equalsIgnoreCase(productId)).findFirst().orElseThrow(
                () -> new RuntimeException("Cannot find any product in [" + country + "] product catalogue with productId [" + productId + "]"));
        return foundProduct;
    }

}
