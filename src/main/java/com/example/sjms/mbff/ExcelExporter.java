package com.example.sjms.mbff;

/**
 * 具体子类：Excel 导出
 */
public class ExcelExporter extends AbstractExporter {

    @Override
    protected String fetchData() {
        System.out.println("查询报表数据");
        return "报表数据";
    }

    @Override
    protected void writeFile(String data) {
        System.out.println("生成 Excel 文件：" + data);
    }
}
