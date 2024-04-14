package pk.wieik.it.model;

import jakarta.servlet.ServletContext;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Tools {
    public static String getTemplate(String file, ServletContext context) throws IOException {
        StringBuffer output = new StringBuffer("");

        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);

        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while ((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else {
            output.append("No " + file + " file");
        }
        return output.toString();
    }

    public static String fill(String template, String tag,
                              String file, ServletContext context) throws IOException {
        StringBuffer output = new StringBuffer("");
        String text = "";
        InputStream is = context.getResourceAsStream("/WEB-INF/view/" + file);
        if (is != null) {
            InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(isr);
            while ((text = reader.readLine()) != null) {
                output.append(text).append("\n");
            }
        } else output.append("No " + file + " file");

        return template.replace("[[" + tag + "]]", output.toString());
    }
    public static int parseInteger(String input, int def) {
        int output = def;
        try {
            output = Integer.parseInt(input);
        } catch (NumberFormatException nfe) { // null or wrong format
            output = def;
        }
        return output;
    }

    public static String parsePage(String input, String valid)
    {
        String output = "main";
        String[] pages = valid.split(";");
        if (input==null) input="main";

        for (String proper: pages)
        {
            if (input.equals(proper)) {
                output = input;
                return output;
            }
        }
        return output;
    }
    public static String addScript(String template, String[] jsFiles) {
        StringBuilder scriptTags = new StringBuilder();
        for (String jsFile : jsFiles) {
            scriptTags.append("<script type='text/javascript' src='").append(jsFile).append("'></script>\n");
        }
        return template.replace("[[SCRIPTS]]", scriptTags.toString());
    }
}

