package org.jasper.styles;

import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;


import javax.swing.text.Style;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public class ColumnStylist {
    private List<TextColumnBuilder<String>> stringFieldsBuilder = new ArrayList<>();
    private List<TextColumnBuilder<LocalDate>> dateFieldsBuilder = new ArrayList<>();
    private StyleBuilder columnStyle = stl.style();

    public ColumnStylist setStringColumns(String[] columns, String[] fields){
        for(int i = 0; i < columns.length; i++){
             stringFieldsBuilder.add(Columns.column(columns[i], fields[i].toUpperCase(),String.class).setStyle());
        }
        return this;
    }
    public ColumnStylist setDateColumns(String[] columns, String[] fields){
        for(int i = 0; i < columns.length; i++){
            dateFieldsBuilder.add(Columns.column(columns[i], fields[i].toUpperCase(),LocalDate.class));
        }
        return this;
    }

    public ColumnStylist setColumnFont(int size){
        columnStyle.setFontSize(size);
        return this;
    }

    public StyleBuilder buildStyles(){
        return columnStyle;
    }

    public List<TextColumnBuilder<String>> buildStringCols(){
        return stringFieldsBuilder;
    }

    public List<TextColumnBuilder<LocalDate>> buildDateCols(){
        return dateFieldsBuilder;
    }

}
