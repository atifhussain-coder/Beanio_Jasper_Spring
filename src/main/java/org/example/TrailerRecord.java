package org.example;
import org.beanio.annotation.Field;
// Trailer record
public class TrailerRecord {
    @Field(at = 1, length = 1)
    private String recordType = "T";

    @Field(at = 2, length = 15)
    private String fileType = "FLAT_FILE";

    // getters and setters
}
