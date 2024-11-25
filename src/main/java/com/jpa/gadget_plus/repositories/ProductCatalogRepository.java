package com.jpa.gadget_plus.repositories;

import com.jpa.gadget_plus.dtos.ReportProduct;
import com.jpa.gadget_plus.entities.ProductCatalogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductCatalogRepository extends JpaRepository<ProductCatalogEntity, UUID> {

    //select * from product_catalog where name = ''
    Optional<ProductCatalogEntity> findByName(String name);
    List<ProductCatalogEntity> findByNameLike(String key);
    List<ProductCatalogEntity> findByPriceBetween(BigDecimal min, BigDecimal max);
    @Query("from productCatalog p where p.price between :min and :max")
    List<ProductCatalogEntity> findBetweenByQuery(BigDecimal min, BigDecimal max);

    /**
     * select * from products_catalog pc
     * join product_join_category pjc on pc.id = pjc.id_product
     * join categories c on c.id  = pjc.id_category where pjc.id_category = 1;
     */
    @Query("from productCatalog p left join fetch p.categories c where c.id = :categoryId")
    List<ProductCatalogEntity> getByCategory(Long categoryId);

    /**
     * select * from products_catalog where launching_date < '2017-01-01';
     */
    List<ProductCatalogEntity> findByLaunchingDateBefore(LocalDate date);
    List<ProductCatalogEntity> findByLaunchingDateAfter(LocalDate date);

    /*
     * select * from products_catalog where brand_name = 'Apple' and rating > 3;
     */
    List<ProductCatalogEntity> findByBrandAndRatingGreaterThan(String brand,short rating);

    /*
     * select * from products_catalog where brand_name = 'Apple' and rating > 3;
     */
    List<ProductCatalogEntity> findByBrandOrRatingGreaterThan(String brand,short rating);

    /*
     * select pc.brand_name, avg(pc.price) as average, sum(pc.price) as totalPrice
     * from products_catalog pc group by pc.brand_name;
     */
    @Query("select new com.jpa.gadget_plus.dtos.ReportProduct("
            + "pc.brand,"
            + "avg(pc.price),"
            + "sum(pc.price))"
            + "from productCatalog  pc "
            + "group by pc.brand")
    List<ReportProduct> findAndMakeReport();

    Page<ProductCatalogEntity> findAllByBrand(String brand, Pageable pageable);

    @Procedure(procedureName = "count_total_products_by_brand", outputParameterName = "response")
    Integer countTotalProductsByBrandStoreProcedure(@Param(value = "brand") String brand);
}
