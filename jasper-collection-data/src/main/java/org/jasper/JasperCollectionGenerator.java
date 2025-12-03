package org.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;


import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JasperCollectionGenerator {

    public static void generate(String dirName, String fileName) throws IOException, JRException {
        List<Map<String, ?>> allHolidays = XmlParser.parse();
        JasperReport report = JasperCompileManager.compileReport(FileProvider.readJRXML());

        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(allHolidays);

        JasperPrint print = JasperFillManager.fillReport(report,null ,dataSource);
        String dirCreating = ExportProvider.exportToDesktop(dirName);
        JasperExportManager.exportReportToPdfFile(print, dirCreating+"/"+fileName);
    }

    public static void main(String[] ar) throws JRException, IOException {
        generate("test", "mapCollection.pdf");
    }

}
