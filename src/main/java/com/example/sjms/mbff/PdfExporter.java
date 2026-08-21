package com.example.sjms.mbff;

/**
 * 具体子类：PDF 导出（覆盖钩子方法增加排版逻辑）
 */
public class PdfExporter extends AbstractExporter {

    @Override
    protected String fetchData() {
        System.out.println("查询报表数据");
        return "报表数据";
    }

    @Override
    protected String format(String data) {
        System.out.println("PDF 额外排版");
        return data;
    }

    @Override
    protected void writeFile(String data) {
        System.out.println("生成 PDF 文件：" + data);
    }
}
