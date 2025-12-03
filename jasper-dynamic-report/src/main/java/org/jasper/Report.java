package org.jasper;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.ColumnBuilders;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jasper.mapper.XmlBeanMapper;

import java.io.IOException;

public class Report {
    private void build() throws DRException, IOException {

        ColumnBuilders builders = new ColumnBuilders();
        TextColumnBuilder<String> name = builders.column("spookie", "NAME", String.class);
        JasperReportBuilder report = new JasperReportBuilder();

        report.setPageMargin(DynamicReports.margin().setLeft(250).setTop(25).setRight(55));
        report.columns(name);
        report.setDataSource(new JRBeanCollectionDataSource(XmlBeanMapper.parse()));
        report.show();

    }

    public static void main(String[] a) throws DRException, IOException {
        Report report = new Report();
        report.build();
    }
}
