package com.ink.config.util;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ink.config.po.PropertyItemVO;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class PropertyUtil {

    /**
     * 把属性值抓换成properties文件的键值对形式
     * @param nodePath
     * @param version
     * @param groupName
     * @param items
     * @return
     */
    public static List<String> formatPropertyLines(String nodePath,String version,String groupName, List<PropertyItemVO> items) {
        List<String> lines = Lists.newArrayList();
        lines.add(String.format("# Export from zookeeper configuration group: [%s] - [%s] - [%s].", nodePath,
                version, groupName));
        for (PropertyItemVO item : items){
            if (!Strings.isNullOrEmpty(item.getComment())) {
                lines.add("# " + item.getComment());
            }
            lines.add(item.getName() + "=" + item.getValue());
        }
        return lines;
    }

    public static List<PropertyItemVO> parseInputFile(InputStream inputstream)
            throws IOException {
        Splitter PROPERTY_SPLITTER = Splitter.on('=').limit(2);

        List<String> lines = IOUtils.readLines(inputstream, Charsets.UTF_8.name());
        List<PropertyItemVO> items = Lists.newArrayList();
        String previousLine = null;
        for (int i = 1; i < lines.size(); i++) {
            String line = (String)lines.get(i);
            if (!line.startsWith("#")) {
                Iterable<String> parts = PROPERTY_SPLITTER.split(line);
                if (Iterables.size(parts) == 2)  {
                    PropertyItemVO item = new PropertyItemVO(((String)Iterables.getFirst(parts, null)).trim(), ((String)Iterables.getLast(parts)).trim());
                    if ((previousLine != null) && (previousLine.startsWith("#"))) {
                        item.setComment(StringUtils.trimLeadingCharacter(previousLine, '#').trim());
                    }
                    items.add(item);
                }
            }
            previousLine = line;
        }
        return items;
    }
}
