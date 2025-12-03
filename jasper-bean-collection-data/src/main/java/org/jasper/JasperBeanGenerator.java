package org.jasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jasper.mapper.XmlBeanMapper;
import org.jasper.pojo.HolidayPojo;

import java.io.IOException;
import java.util.List;


public class JasperBeanGenerator {
    public static void generate(String filename, String dirName) throws IOException, JRException {
        List<HolidayPojo> holidayList = XmlBeanMapper.parse();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(holidayList);
        JasperReport report = JasperCompileManager.compileReport(FileProvider.readJRXMLForBean()); // compiling the .jrxml file into .jasper

        JasperPrint print = JasperFillManager.fillReport(report, null, dataSource);
        // report viewer
        //JasperViewer.viewReport(print);

        JasperExportManager.exportReportToPdfFile(print, ExportProvider.exportToDesktop(dirName)+"/"+filename);
    }

    public static void main(String[] ar) throws JRException, IOException {
        generate("beanReport.pdf", "test");
    }
}
