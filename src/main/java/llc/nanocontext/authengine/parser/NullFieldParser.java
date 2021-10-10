package llc.nanocontext.authengine.parser;

import javax.lang.model.type.NullType;

public class NullFieldParser implements FieldParser<NullType> {
    @Override
    public Result parse(String raw, int offset) {
        return new Result();
    }

    public static class Result implements FieldParserResult<NullType> {
        private Result() {

        }

        @Override
        public NullType getValue() {
            return null;
        }

        @Override
        public int getLength() {
            return 0;
        }
    }
}
