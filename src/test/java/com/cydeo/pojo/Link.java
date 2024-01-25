package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

//@Getter @Setter @ToString

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {

    private String rel;
    private String href;


}
