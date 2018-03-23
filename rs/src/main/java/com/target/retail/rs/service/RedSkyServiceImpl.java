package com.target.retail.rs.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RedSkyServiceImpl implements RedSkyService{
    private Logger logger = LoggerFactory.getLogger(RedSkyService.class);

    @Autowired
    public RestTemplate restTemplate;

    public String getProductName(Long id){
        try {
            String result = restTemplate.getForObject("https://redsky.target.com/v2/pdp/tcin/{id}?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics", String.class, id);
            JSONObject jsonObject = new JSONObject(result);
            JSONObject productName = jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description");
            return productName.getString("title");
        }catch(Exception e){
            logger.error("Error connecting / fetching product name from red sky for {}",id,e);
        }
        return null;

    }
}
