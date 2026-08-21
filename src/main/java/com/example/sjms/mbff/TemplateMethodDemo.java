package com.example.sjms.mbff;

/**
 * 模板方法模式演示
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {
        AbstractExporter excel = new ExcelExporter();
        excel.export();

        System.out.println("----------");

        AbstractExporter pdf = new PdfExporter();
        pdf.export();
    }
}
