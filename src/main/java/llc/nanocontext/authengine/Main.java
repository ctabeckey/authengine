package llc.nanocontext.authengine;

import llc.nanocontext.authengine.parser.BaseMessageParser;
import org.apache.commons.cli.*;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(final String[] argv) throws ParseException, IOException {
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(commandLineOptions(), argv);

        String inputFile = commandLine.getOptionValue('i');
        String outputFile = commandLine.getOptionValue('o');

        LineNumberReader reader = null;
        Writer writer = null;

        reader = inputFile != null
                ? new LineNumberReader(new FileReader(new File(inputFile)))
                : argv.length > 0 ? new LineNumberReader(new StringReader(asLineSeperatedString(argv)))
                : new LineNumberReader(new BufferedReader(new InputStreamReader(System.in)));
        writer = outputFile != null
                ? new FileWriter(new File(outputFile))
                : new OutputStreamWriter(System.out);

        try {
            process(reader, writer);
        } finally {
            reader.close();
            writer.close();
        }
    }

    private static String asLineSeperatedString(final String[] argv) {
        return Arrays.stream(argv)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /**
     *
     * @param reader
     * @param writer
     * @throws IOException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static void process(LineNumberReader reader, Writer writer) throws IOException {
        final BaseMessageParser parser = new BaseMessageParser();
        final AuthorizationStrategy authorizationStrategy = new NaiveAuthorizationStrategy();
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final MessageProcessor processor = new MessageProcessorImpl(parser, authorizationStrategy, validator);

        for(String message = reader.readLine(); message != null; message = reader.readLine()) {
            String response = processor.process(message);
            writer.write(response);
            writer.write(System.lineSeparator());
        }
    }

    /**
     *
     * @return
     */
    private static Options commandLineOptions() {
        Options options = new Options();

        options.addOption("i", true, "an optional input file");
        options.addOption("o", true, "an optional output file");

        return options;
    }
}
