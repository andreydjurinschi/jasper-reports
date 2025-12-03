package org.jasper.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasper.FileProvider;
import org.jasper.pojo.HolidayPojo;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;

public class XmlBeanMapper {
    public static List<HolidayPojo> parse() throws IOException {
        ObjectMapper mapper = new XmlMapper();
        mapper.registerModule(new JavaTimeModule());

        InputStream fileStream = FileProvider.readXml();
        JsonNode root = mapper.readTree(fileStream); //<Year-2021>elems</Year-2021>
        JsonNode allHolidays = root.get("holydays"); //<holydays>props</holydays>

        List<HolidayPojo> holidaysList = new ArrayList<>();
        for(var entry : allHolidays){
            HolidayPojo curr = new HolidayPojo();
            JsonNode nameNode = entry.get("NAME");
            JsonNode countryNode = entry.get("COUNTRY");
            JsonNode dateNode = entry.get("DATE");

            curr.setNAME(nameNode.asText());
            curr.setCOUNTRY(countryNode.asText());
            curr.setDATE(LocalDate.parse(dateNode.asText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));

            holidaysList.add(curr);
        }
        return holidaysList;
    }
}
