package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter @Setter @ToString
@JsonIgnoreProperties (ignoreUnknown = true)
public class Region {

    @JsonProperty ("region_id")
    // hey Jackson, find region_id  from Json response and convert new variable regionId
    private int regionId;
    @JsonProperty ("region_name")
    private String  regionName;
    private List<Link> links;




}
