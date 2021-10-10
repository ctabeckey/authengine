package llc.nanocontext.authengine.parser;

import llc.nanocontext.authengine.exceptions.MessageParseException;
import llc.nanocontext.authengine.message.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class BaseMessageParser {
    private static Logger LOGGER = LoggerFactory.getLogger(BaseMessageParser.class);

    /**
     * The input message is made up of the following parts:
     * 1. A Message Type Indicator, a 4-digit numeric field.
     * 2. A bitmap that indicates the presence of data fields,
     *   a 2-character lower case hex encoding of a 1-byte bitmap value
     * 3. Data elements, the actual data fields
     *
     * @param raw
     * @return
     */
    public Message parse(final String raw) throws MessageParseException {
        LOGGER.debug("parse({})", raw);
        if(raw == null)
            return null;

        MessageTypeIndicator mti = MessageTypeIndicator.builder().withRaw(raw).build();

        int dataIndex = 6;
        int primaryBitmap = Integer.parseInt(raw.substring(4, 6), 16);
        int secondaryBitmap = 0x00;
        int tertiaryBitmap = 0x00;

        // this implementation does not currently handle the data fields in the secondary
        // and tertiary bitmaps but this code will at least protect this parser from
        // mis-interpreting data that does contain those bitmaps
        if (PrimaryBitmapFields.AdditionalData.isPresent(primaryBitmap)) {
            secondaryBitmap = Integer.parseInt(raw.substring(6, 8), 16);
            dataIndex = 8;
            if(SecondaryBitmapFields.AdditionalData.isPresent(secondaryBitmap)) {
                tertiaryBitmap = Integer.parseInt(raw.substring(8, 10), 16);
                dataIndex = 10;
            }
        }

        MessageBuilder builder = MessageBuilderFactory.getMessageBuilder(mti);

        // iterate through the field types that could be present (i.e. PrimaryBitmapFields)
        // where the primaryBitmap indicates that the field should exist then try to parse it
        // starting at the current dataIndex.
        // the iteration ends when either all the data in the message is consumed or
        // the primary fields have been checked.
        // the secondary and tertiary fields could be implemented using this same pattern
        for (int primaryBitmapIndex = 0;
             dataIndex < raw.length() && primaryBitmapIndex < PrimaryBitmapFields.values().length;
             primaryBitmapIndex++) {

            PrimaryBitmapFields candidateField = PrimaryBitmapFields.values()[primaryBitmapIndex];
            if (candidateField.isPresent(primaryBitmap)) {
                FieldParserResult<?> result = candidateField.getFieldParser().parse(raw, dataIndex);

                builder.with(candidateField, result);

                dataIndex += result.getLength();
            }
        }

        return builder.build();
    }
}
