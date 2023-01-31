package catalog;

import java.util.Collection;
import java.util.HashSet;

public class CatalogEntity {

    private Collection<ProductEntity> catalogProducts;

    public CatalogEntity() {
        catalogProducts = new HashSet<>();
    }

    public Collection<ProductEntity> getCatalogProducts() {
        return catalogProducts;
    }

    public void setCatalogProducts(Collection<ProductEntity> catalogProducts) {
        this.catalogProducts = catalogProducts;
    }

    public void addProduct(ProductEntity product) {
        catalogProducts.add(product);
    }

    public void removeProduct(ProductEntity product) {
        catalogProducts.remove(product);
    }
}
