package com.dingxin.cloudclassroom.config;

import com.dingxin.cloudclassroom.utils.DateUtils;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class CommonDateDeserializer extends StdScalarDeserializer {

    public  static final CommonDateDeserializer INSTANCE = new CommonDateDeserializer(Date.class);

    protected CommonDateDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonToken t = jsonParser.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jsonParser.getText().trim();
            if (str.length() == 0) {
                return getEmptyValue(deserializationContext);
            }
            try {
                return DateUtils.parseDate(str);
            } catch (ParseException e) {
                return deserializationContext.handleWeirdStringValue(handledType(),str,"expected format \"%s\"");
            }
        }

        if (t == JsonToken.START_ARRAY && deserializationContext.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            jsonParser.nextToken();
            final Date parsed = _parseDate(jsonParser,deserializationContext);
            t = jsonParser.nextToken();
            if (t != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(jsonParser,deserializationContext);
            }
            return parsed;
        }
        return super._parseDate(jsonParser,deserializationContext);
    }
}