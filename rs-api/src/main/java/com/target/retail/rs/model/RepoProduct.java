package com.target.retail.rs.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.target.retail.rs.model.Price;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * RepoProduct
 */

public class RepoProduct   {
  private Long id = null;

  private Price currentPrice = null;

  public RepoProduct id(Long id) {
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

  public RepoProduct currentPrice(Price currentPrice) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RepoProduct repoProduct = (RepoProduct) o;
    return Objects.equals(this.id, repoProduct.id) &&
        Objects.equals(this.currentPrice, repoProduct.currentPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, currentPrice);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RepoProduct {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    currentPrice: ").append(toIndentedString(currentPrice)).append("\n");
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

