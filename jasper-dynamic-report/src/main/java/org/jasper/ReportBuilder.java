package org.jasper;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.exception.DRException;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.jasper.mapper.XmlBeanMapper;
import org.jasper.styles.TitleStyle;

import java.io.IOException;
import java.time.LocalDate;


public class ReportBuilder {
    private final JasperReportBuilder report = new JasperReportBuilder();



    public void build() throws IOException, DRException {

        // title design
        report.addTitle(
                Components.text("Dynamic Report Builder").setStyle(TitleStyle.boldCenteredTitle()));

        // columns
        report.addColumn(Columns.column("Holiday name", "NAME", String.class));
        report.addColumn(Columns.column("Country", "COUNTRY", String.class));
        report.addColumn(Columns.column("Date", "DATE", LocalDate.class));

        // footer
        report.pageFooter(Components.pageXofY());

        report.setDataSource(new JRBeanCollectionDataSource(XmlBeanMapper.parse()));
        report.show();
    }

    public static void main(String[] a) throws DRException, IOException {
        ReportBuilder builder = new ReportBuilder(); builder.build();}
}
