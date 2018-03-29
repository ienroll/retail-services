package com.target.retail.rs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.target.retail.rs.model.Price;
import com.target.retail.rs.model.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * Product
 */

public class Product   {
  private Long id = null;

  private String name = null;

  private Price currentPrice = null;

  private Response response = null;

  public Product id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Product currentPrice(Price currentPrice) {
    this.currentPrice = currentPrice;
    return this;
  }

   /**
   * Get currentPrice
   * @return currentPrice
  **/
  @ApiModelProperty(value = "")
  public Price getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(Price currentPrice) {
    this.currentPrice = currentPrice;
  }

  public Product response(Response response) {
    this.response = response;
    return this;
  }

   /**
   * Get response
   * @return response
  **/
  @ApiModelProperty(value = "")
  public Response getResponse() {
    return response;
  }

  public void setResponse(Response response) {
    this.response = response;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(this.id, product.id) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.currentPrice, product.currentPrice) &&
        Objects.equals(this.response, product.response);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, currentPrice, response);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Product {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    currentPrice: ").append(toIndentedString(currentPrice)).append("\n");
    sb.append("    response: ").append(toIndentedString(response)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

