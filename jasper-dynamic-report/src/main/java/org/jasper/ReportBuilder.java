package org.jasper;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jasper.mapper.XmlBeanMapper;
import org.jasper.styles.ColumnStylist;
import org.jasper.styles.TitleStylist;

import java.io.IOException;
import java.time.LocalDate;


public class ReportBuilder {
    private final JasperReportBuilder report = new JasperReportBuilder();

    public void build() throws IOException, DRException {
        // title design
        TitleStylist stylist = new TitleStylist();
        stylist.stylizeTitle(30).addColor(341.54f, 100.0f, 66.27f).addPadding(50);
        StyleBuilder titleStyleBuilder = stylist.buildTitle();

        report.addTitle(
                Components.text("Dynamic Report Builder").setStyle(titleStyleBuilder)
        );

        // columns design
        ColumnStylist columnStylist = new ColumnStylist();
        columnStylist.setStringColumns(new String[]{"Holiday name", "Country name"},new String[]{"name", "country"});
        columnStylist.setDateColumns(new String[]{"Holiday date"},new String[]{"date"});

        report.columns(columnStylist.setColumnFont(21).buildStringCols().toArray(new TextColumnBuilder[0]));
        report.columns(columnStylist.setColumnFont(21).buildDateCols().toArray(new TextColumnBuilder[0]));

        /*report.addColumn(Columns.column("Holiday name", "NAME", String.class));
        report.addColumn(Columns.column("Country", "COUNTRY", String.class));
        report.addColumn(Columns.column("Date", "DATE", LocalDate.class));*/

        // footer
        report.pageFooter(Components.pageXofY());

        report.setDataSource(new JRBeanCollectionDataSource(XmlBeanMapper.parse()));
        report.show();
    }

    public static void main(String[] a) throws DRException, IOException {
        ReportBuilder builder = new ReportBuilder(); builder.build();}
}
