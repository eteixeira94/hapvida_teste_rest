package br.com.isoftware.hapvida.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class HapvidaDateTimeDeserializer extends StdDeserializer<Date> {

    private static final long serialVersionUID = 1L;

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public HapvidaDateTimeDeserializer() {
        this(null);
    }

    public HapvidaDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
            throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            try {
                Long time = Long.valueOf(date);
                return new Date(time);
            } catch (NumberFormatException ex) {
            }
            throw new RuntimeException(e);
        }
    }

}
