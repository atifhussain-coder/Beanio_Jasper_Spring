package org.example;

import org.beanio.annotation.Field;

// Header record
public class HeaderRecord {

    @Field(at = 1, length = 1)
    private String recordType = "H";

    @Field(at = 2, length = 15)
    private String fileType = "FLAT_FILE";

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
