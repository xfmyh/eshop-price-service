package com.roncoo.eshop.price.mapper;

import com.roncoo.eshop.price.model.ProductPrice;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProductPriceMapper {

    @Insert("insert into product_price(value,product_id) values(#{value},#{productId})")
    void add(ProductPrice productPrice);

    @Update("update product_price set value = #{value},product_id = #{productId} where id = #{id}")
    void update(ProductPrice productPrice);

    @Delete("delete from product_price where id = #{id}")
    void delete(Long id);

    @Select("select * from product_price where id = #{id}")
    @Results({
            @Result(column = "product_id",property = "productId"),
    })
    ProductPrice findById(Long id);

}
