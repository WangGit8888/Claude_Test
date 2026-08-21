package com.example.sjms.mbff;

/**
 * 模板方法模式：抽象模板类
 * 定义算法骨架（export），把可变步骤延迟到子类实现
 */
public abstract class AbstractExporter {

    // 模板方法：final 防止子类重写，固定执行步骤
    public final void export() {
        String data = fetchData();   // 1. 获取数据
        String formatted = format(data); // 2. 格式化（钩子，可覆盖）
        writeFile(formatted);        // 3. 写文件
        notifyUser();                // 4. 通知
    }

    // 抽象步骤：子类必须实现
    protected abstract String fetchData();

    protected abstract void writeFile(String data);

    // 钩子方法：提供默认实现，子类按需覆盖
    protected String format(String data) {
        return data;
    }

    protected void notifyUser() {
        System.out.println("导出完成");
    }
}
