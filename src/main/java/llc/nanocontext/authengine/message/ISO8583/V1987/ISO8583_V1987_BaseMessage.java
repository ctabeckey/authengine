package llc.nanocontext.authengine.message.ISO8583.V1987;

import llc.nanocontext.authengine.message.Message;
import llc.nanocontext.authengine.message.MessageTypeIndicator;

/**
 * The base class of immutable representations of an ISO 8583-1987 message.
 * Derived classes SHOULD also be implemented as immutable classes.
 *
 * The input message is made up of the following parts:
 * 1. A Message Type Indicator
 * 2. A bitmap that indicates the presence of fields
 * 3. Data elements, the actual data fields
 *
 * Message Type indicator is a 4-digit numeric field. Possible message types are
 * 0100 - Authorization request message
 * 0110 - Authorization response message
 *
 * This field is a 2-character lower case hex encoding of a 1-byte bitmap value, indicating which data elements are present.
 * Example: c8 represents 11001000 which means that, data elements, bit 1, bit 2 and bit 5 are provided in this message.
 *
 * Data Elements
 * Data elements are actual information fields
 * Here are the data elements and their type definition
 * Bit        Type        Data Element                                                    Mandatory
 * 1          LLVAR       PAN (credit card number) - 14-19 digit PAN                      REQUIRED
 * 2          NUMERIC(4)  Expiration Date (MMYY)                                          REQUIRED
 * 3          NUMERIC(10) Transaction amount in cents (e.g. for $10, value is 0000001000) REQUIRED
 * 4          ALPHA(2)    Response code                                                   REQUIRED on response
 * 5          LLVAR       Cardholder name
 * 6          NUMERIC(5)  ZIP code
 * 7          Not used
 * 8          Not used
 */
public abstract class ISO8583_V1987_BaseMessage extends Message {
    public ISO8583_V1987_BaseMessage(final MessageTypeIndicator messageTypeIndicator) {
        super(messageTypeIndicator);
    }
}
