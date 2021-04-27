package com.cicero.heroesapi.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import lombok.*;
import org.springframework.data.annotation.Id;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;


@Data
@Builder
@NoArgsConstructor
@DynamoDBTable(tableName = "Heroes_Table")
public class Heroes {

    @Id
    @DynamoDBHashKey(attributeName = "id")
    private String id;

    @DynamoDBAttribute(attributeName = "name")
    private String name;

    @DynamoDBAttribute(attributeName = "universe")
    private String universe;

    @DynamoDBAttribute(attributeName = "films")
    private int films;
}
