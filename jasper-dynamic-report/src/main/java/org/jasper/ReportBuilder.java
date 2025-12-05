package org.jasper;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.group.GroupBuilder;
import net.sf.dynamicreports.report.builder.group.GroupBuilders;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jasper.mapper.XmlBeanMapper;
import org.jasper.styles.ColumnStylist;
import org.jasper.styles.TitleStylist;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;


public class ReportBuilder {
    private final JasperReportBuilder report = new JasperReportBuilder();

    public void build() throws IOException, DRException {
        // title design
        TitleStylist stylist = new TitleStylist();
        stylist.stylizeTitle(30).addColor(169, 0, 52).addPadding(50,50,50,20);
        StyleBuilder titleStyleBuilder = stylist.buildTitle();

        report.addTitle(
                Components.text("Dynamic Report Builder").setStyle(titleStyleBuilder)
        );



        // columns design
        ColumnStylist columnStylist = new ColumnStylist();
        columnStylist.setStringColumns(new String[]{"Holiday name", "Country name"},new String[]{"name", "country"});
        columnStylist.setDateColumns(new String[]{"Holiday date"},new String[]{"date"});

        columnStylist.setColumnFont(12)
                .setColumnBackgroundColor(205, 210, 238)
                .setColumnPaddings(15)
                .setColumnTextAlignment()
                .setColumnHeaderStyle();

        StyleBuilder columnStyle = columnStylist.buildColumnStyles();
        StyleBuilder headerColumnStyle = columnStylist.buildHeaderStyles();

        for(var col : columnStylist.buildStringCols()){
            report.addColumn(col.setStyle(headerColumnStyle));
        }
        for(var col : columnStylist.buildDateCols()){
            report.addColumn(col.setStyle(headerColumnStyle));
        }

        report.columns().setColumnStyle(columnStyle);

        // footer
        report.pageFooter(Components.pageXofY());
        report.setDataSource(new JRBeanCollectionDataSource(XmlBeanMapper.parse()));
        report.show();
    }

    public static void main(String[] a) throws DRException, IOException {
        ReportBuilder builder = new ReportBuilder(); builder.build();}
}
