package org.jasper.styles;

import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;


import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

public class ColumnStylist {
    private final List<TextColumnBuilder<String>> stringFieldsBuilder = new ArrayList<>();
    private final List<TextColumnBuilder<LocalDate>> dateFieldsBuilder = new ArrayList<>();
    private final StyleBuilder columnStyle = stl.style();
    private final StyleBuilder columnHeaderStyle = stl.style();

    public ColumnStylist setStringColumns(String[] columns, String[] fields){
        for(int i = 0; i < columns.length; i++){
             stringFieldsBuilder.add(Columns.column(columns[i], fields[i].toUpperCase(),String.class));
        }
        return this;
    }
    public ColumnStylist setDateColumns(String[] columns, String[] fields){
        for(int i = 0; i < columns.length; i++){
            dateFieldsBuilder.add(Columns.column(columns[i], fields[i].toUpperCase(),LocalDate.class));
        }
        return this;
    }

    public ColumnStylist setColumnHeaderStyle(){
        columnHeaderStyle.setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        return this;
    }

    public ColumnStylist setColumnFont(int size){
        columnStyle.setFontSize(size);
        return this;
    }
    public ColumnStylist setColumnBackgroundColor(int r, int g, int b){
        columnStyle.setBackgroundColor(new Color(r, g, b));
        return this;
    }

    public ColumnStylist setColumnPaddings(int ... values){
        if(values.length == 0){
            columnStyle.setPadding(5);
        }else if(values.length == 4){
            columnStyle.setLeftPadding(values[0]);
            columnStyle.setTopPadding(values[1]);
            columnStyle.setRightPadding(values[2]);
            columnStyle.setBottomPadding(values[3]);
        }else if(values.length == 1) {
            columnStyle.setPadding(values[0]);
        }else{
            throw new RuntimeException("Padding values must be: 0 or 4 or 1");
        }
        return this;
    }

    public ColumnStylist setColumnTextAlignment() {
        columnStyle.setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
        return this;
    }

    public StyleBuilder buildColumnStyles(){
        return columnStyle;
    }

    public StyleBuilder buildHeaderStyles(){
        return columnStyle;
    }

    public List<TextColumnBuilder<String>> buildStringCols(){
        return stringFieldsBuilder;
    }

    public List<TextColumnBuilder<LocalDate>> buildDateCols(){
        return dateFieldsBuilder;
    }

}
