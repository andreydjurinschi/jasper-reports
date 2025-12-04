package org.jasper.styles;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;


public class TitleStyle {
    /**
     * MAKES TEXT BOLD AND CENTERED BY TWO PARTS
     * @return {@link StyleBuilder}
     */
    public static StyleBuilder boldCenteredTitle(){
        return stl.style().bold()
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
    }

    public static StyleBuilder setFontSize(int fontSize){
        return stl.style().setFontSize(fontSize);
    }

}
