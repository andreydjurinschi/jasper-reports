package org.jasper.styles;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import java.awt.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;


public class TitleStylist {
    private StyleBuilder builder = stl.style();

    /**
     * MAKES TEXT BOLD, CENTERED BY TWO PARTS, COLORED
     * @return {@link StyleBuilder}
     */
    public TitleStylist stylizeTitle(int ... size){
        builder = size == null ?
               builder.setTextAlignment(
                       HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE
               ) : builder.bold().setTextAlignment(
                       HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE
               ).setFontSize(size[0]);
        return this;
    }


    public TitleStylist addColor(int r, int g, int b){
        builder.setForegroundColor(new Color(r,g,b));
        return this;
    }

    public TitleStylist addPadding(int ... values){
        if(values.length == 0){
            return this;
        } else if (values.length == 4) {
            builder.setLeftPadding(values[0]);
            builder.setTopPadding(values[1]);
            builder.setRightPadding(values[2]);
            builder.setBottomPadding(values[3]);
        } else if (values.length == 1) {
            builder.setPadding(values[0]);
        }else {
            throw new RuntimeException("Padding values must be: 0 or 4 or 1");
        }
        return this;
    }

    public StyleBuilder buildTitle(){
        return builder;
    }





}
