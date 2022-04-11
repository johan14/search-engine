package com.intelycare.engine.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document {

  @JsonProperty("ID")
  private String ID;
  private String tokens;

  @Override
  public String toString() {
    return "Document{" +
        "ID='" + ID + '\'' +
        ", tokens='" + tokens + '\'' +
        '}';
  }
}
