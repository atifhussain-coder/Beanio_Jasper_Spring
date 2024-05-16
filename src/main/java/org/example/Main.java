package org.example;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import java.io.FileNotFoundException;

import org.springframework.util.ResourceUtils;

public class Main {
    public static void main(String[] args) throws JRException, FileNotFoundException {
        List<BodyRecord> records = task1();
        exportReport("html", records);
        exportReport("pdf", records);
    }

    private static BodyRecord createBodyRecord(String referenceNo, String amount) {
        BodyRecord record = new BodyRecord();
        record.setReferenceNo(referenceNo);
        record.setAmount(amount);
        return record;
    }

    private static BodyRecord createBodyRecordForReport(LocalDate date, String referenceNo, String amount, String status, String remark) {
        BodyRecord record = new BodyRecord();
        record.setReferenceNo(referenceNo);
        record.setAmount(amount);
        record.setDate(date);
        record.setStatus(status);
        record.setRemark(remark);
        return record;
    }

    private static List<BodyRecord> task1() {
        StreamFactory factory = StreamFactory.newInstance();
        factory.load("/home/atif/Downloads/test1/Task1/src/main/resources/mapping.xml");

//         Create a BeanWriter
        BeanWriter writer = null;
        try {
            writer = factory.createWriter("flatFile", new FileWriter("output.txt"));

            // Write header record
            HeaderRecord header = new HeaderRecord();
            writer.write("header", header);

            // Write body records
            List<BodyRecord> bodyRecords = Arrays.asList(
                    createBodyRecord( "6813162459", "RM2.00"),
                    createBodyRecord("2039229524", "RM10.00"),
                    createBodyRecord("2299488320", "RM5.00")
            );
            for (BodyRecord bodyRecord : bodyRecords) {
                writer.write("body", bodyRecord);
            }

            // Write trailer record
            TrailerRecord trailer = new TrailerRecord();
            writer.write("trailer", trailer);
            return bodyRecords;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
        return new ArrayList<>();
    }

    public static String exportReport(String reportFormat, List<BodyRecord> records) throws FileNotFoundException, JRException {
        String path = "/home/atif/Downloads/test1/Task1/";
        List<BodyRecord> bodyRecords = Arrays.asList(
                createBodyRecordForReport(LocalDate.now(),"6813162459", "RM2.00", "done", "successful"),
                createBodyRecordForReport(LocalDate.now(), "2039229524", "RM10.00", "done", "successful"),
                createBodyRecordForReport(LocalDate.now(), "2299488320", "RM5.00", "done", "successful")
        );

        //load file and compile it
        File file = ResourceUtils.getFile("classpath:template2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(bodyRecords);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Atif Hussain, atifhussain.nu@gmail.com");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "Neurogine_group_report.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "Neurogine_group_report.pdf");
        }

        return "report generated in path : " + path;
    }
}
