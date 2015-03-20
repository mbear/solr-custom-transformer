/**
 * 
 */
package com.zhisland.data.transformer;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.DataImporter;
import org.apache.solr.handler.dataimport.Transformer;

/**
 * Unescapes a string containing entity escapes to a string
 * 
 * @author muzongyan
 *
 */
public class UnescapeHtmlTransformer extends Transformer {

    @Override
    public Object transformRow(Map<String, Object> row, Context context) {
        List<Map<String, String>> fields = context.getAllEntityFields();

        for (Map<String, String> field : fields) {
            // unescapeHtml
            // Check if this field has unescapeHtml="true" specified in the data-config.xml
            String unescapeHtml = field.get("unescapeHtml");
            if ("true".equals(unescapeHtml)) {
                // Apply unescapeHtml on this field
                String columnName = field.get(DataImporter.COLUMN);
                // Get this field's value from the current row
                Object value = row.get(columnName);
                // unescapeHtml and put the updated value back in the current row
                if (value != null)
                    row.put(columnName, StringEscapeUtils.unescapeHtml(value.toString()));
            }
        }

        return row;
    }

}
