package com.roncoo.eshop.price.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.roncoo.eshop.price.mapper.ProductPriceMapper;
import com.roncoo.eshop.price.model.ProductPrice;
import com.roncoo.eshop.price.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Autowired
    private ProductPriceMapper productPriceMapper;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void add(ProductPrice productPrice) {
        productPriceMapper.add(productPrice);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price:" + productPrice.getProductId()+":", JSONObject.toJSONString(productPrice));

    }

    @Override
    public void update(ProductPrice productPrice) {
        productPriceMapper.update(productPrice);
        Jedis jedis = jedisPool.getResource();
        jedis.set("product_price:" + productPrice.getProductId()+":", JSONObject.toJSONString(productPrice));

    }

    @Override
    public void delete(Long id) {
        ProductPrice productPrice = findById(id);
        productPriceMapper.delete(id);
        Jedis jedis = jedisPool.getResource();
        jedis.del("product_price:" + productPrice.getProductId()+":");

    }

    @Override
    public ProductPrice findById(Long id) {
        return productPriceMapper.findById(id);
    }

    @Override
    public ProductPrice findByProductId(Long productId) {

        Jedis jedis = jedisPool.getResource();
        String dataJSON = jedis.get("product_price:" + productId + ":");

        if (dataJSON != null && !"".equals(dataJSON)) {
            ProductPrice productPrice = JSONObject.parseObject(dataJSON,ProductPrice.class);
            productPrice.setId(-1L);
            return productPrice;
        }

        return productPriceMapper.findByProductId(productId);
    }
}
