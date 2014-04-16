package cucumber.jvm.parallel;

import java.io.StringWriter;

/**
 *
 * http://code.google.com/p/json-simple/issues/detail?id=22
 * http://code.google.com/p/json-simple/issues/attachmentText?id=22&aid=220009000&name=JSonWriter.java&token=JFPBdQPSUs1cIM6Bl0KdKxP5BUs%3A1397646495589
 * @author Elad Tabak
 * @since 28-Nov-2011
 * @version 0.1
 *
 */
public class JSONWriter extends StringWriter {

    private int indent = 0;

    @Override
    public void write(int c) {
        if (((char)c) == '[' || ((char)c) == '{') {
            super.write(c);
            super.write('\n');
            indent++;
            writeIndentation();
        } else if (((char)c) == ',') {
            super.write(c);
            super.write('\n');
            writeIndentation();
        } else if (((char)c) == ']' || ((char)c) == '}') {
            super.write('\n');
            indent--;
            writeIndentation();
            super.write(c);
        } else {
            super.write(c);
        }

    }

    private void writeIndentation() {
        for (int i = 0; i < indent; i++) {
            super.write("   ");
        }
    }
}
