package catalog;

import java.util.HashSet;

public class CatalogEntity {

    private HashSet<ProductEntity> catalogProducts;

    public CatalogEntity() {
        catalogProducts = new HashSet<>();
    }

    public HashSet<ProductEntity> getCatalogProducts() {
        return catalogProducts;
    }

    public void setCatalogProducts(HashSet<ProductEntity> catalogProducts) {
        this.catalogProducts = catalogProducts;
    }

    public void addProduct(ProductEntity product) {
        catalogProducts.add(product);
    }

    public void removeProduct(ProductEntity product) {
        catalogProducts.remove(product);
    }
}
