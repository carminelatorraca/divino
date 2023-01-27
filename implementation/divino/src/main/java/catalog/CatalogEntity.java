package catalog;

import java.util.HashSet;

public class CatalogEntity {

    private HashSet<ProductEntity> catalogProducts;

    public CatalogEntity() {
        catalogProducts = new HashSet<>();
    }

    public void addProduct(ProductEntity product) {
        catalogProducts.add(product);
    }

    public void removeProduct(ProductEntity product) {
        catalogProducts.remove(product);
    }
}
