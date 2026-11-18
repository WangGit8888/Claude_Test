const { Document, Packer, Paragraph, TextRun, AlignmentType } = require("docx");
const fs = require("fs");

// 中文字体：正文宋体，标题黑体
const songTi = { ascii: "Times New Roman", eastAsia: "宋体", hAnsi: "Times New Roman" };
const heiTi = { ascii: "SimHei", eastAsia: "黑体", hAnsi: "SimHei" };

// 标题（红头，居中，二号加粗）
const title = new Paragraph({
  alignment: AlignmentType.CENTER,
  spacing: { after: 320 },
  children: [
    new TextRun({
      text: "河南科技大学关于2026年国庆节放假的通知",
      bold: true,
      color: "C00000",
      size: 44, // 22pt 二号
      font: heiTi,
    }),
  ],
});

// 正文段落（首行缩进 2 字符 = 640 twips，16pt 三号）
function body(text, opts = {}) {
  return new Paragraph({
    spacing: { line: 560, lineRule: "exact" },
    indent: opts.noIndent ? undefined : { firstLine: 640 },
    alignment: opts.alignment,
    children: [
      new TextRun({
        text,
        size: 32, // 16pt 三号
        font: opts.bold ? heiTi : songTi,
        bold: opts.bold,
      }),
    ],
  });
}

// 节标题（一、二、三）
function sectionHeading(text) {
  return new Paragraph({
    spacing: { line: 560, lineRule: "exact", before: 120 },
    indent: { firstLine: 640 },
    children: [
      new TextRun({ text, size: 32, font: heiTi, bold: true }),
    ],
  });
}

const children = [
  title,
  body("校属各单位、各部门：", { noIndent: true }),
  body("根据《国务院办公厅关于2026年部分节假日安排的通知》精神，结合学校工作实际，现将2026年国庆节放假有关事项通知如下："),
  sectionHeading("一、放假时间"),
  body("10月1日（星期四）至10月7日（星期三）放假调休，共7天。"),
  sectionHeading("二、调休及教学安排"),
  body("1．放假期间全校停课。"),
  body("2．放假前后调休上班（上课）安排，按学校教务处另行通知执行。"),
  sectionHeading("三、有关要求"),
  body("1．各单位要认真做好假期值班安排，落实值班责任，确保信息畅通。遇有重大突发事件，要按规定及时报告并妥善处置。"),
  body("2．放假前，各单位要开展安全隐患排查，加强对师生的安全教育，提高师生安全防范意识。"),
  body("3．请广大师生合理安排假期学习与生活，注意交通、人身和财产安全，度过一个安全、祥和的国庆假期。"),
  body("特此通知。"),
  body("河南科技大学", { alignment: AlignmentType.RIGHT }),
  body("2026年9月9日", { alignment: AlignmentType.RIGHT }),
];

const doc = new Document({
  styles: {
    default: {
      document: { run: { font: songTi, size: 32 } },
    },
  },
  sections: [
    {
      properties: {
        page: { margin: { top: 1440, bottom: 1440, left: 1800, right: 1800 } },
      },
      children,
    },
  ],
});

const out = "河南科技大学国庆节放假通知.docx";
Packer.toBuffer(doc).then((buf) => {
  fs.writeFileSync(out, buf);
  console.log("生成成功：" + out);
});
