package crypticlib.util;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapHelper {

    private static final Pattern KEY_VALUE_TEXT_PATTERN = Pattern.compile("(\"[^\"]*\"|[^:,{]*):(\"[^\"]*\"|[^,{}]*)");

    /**
     * 用于将形如{a:b,c:d}这样的键值对文本转化为map
     * 支持用""包裹值以兼容更多字符,例如{a:" ab: ", b:c}
     * 文本可以没有{}包裹
     * 格式错误时,将会抛出{@link KeyValueTextParseException}
     *
     * @param keyValueText 形如{a:b,c:d}的键值对文本
     * @return 解析
     */
    @Contract("null -> null; !null -> !null")
    public static Map<String, String> keyValueText2Map(String keyValueText) throws KeyValueTextParseException {
        //若文本为null,返回null
        if (keyValueText == null) {
            return null;
        }

        //去除外包大括号
        keyValueText = keyValueText.trim().replaceAll("^\\{", "").replaceAll("}$", "");
        if (keyValueText.isEmpty()) {
            return new HashMap<>();
        }
        Map<String, String> resultMap = new HashMap<>();

        //使用正则表达式匹配键值对
        Matcher matcher = KEY_VALUE_TEXT_PATTERN.matcher(keyValueText);

        //逐一处理每个键值对
        while (matcher.find()) {
            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();

            //若值被引号包裹,去掉引号
            if (value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }

            //进行格式检查,确保键值对的格式有效
            if (key.isEmpty()) {
                throw new KeyValueTextParseException("Key can not be empty", keyValueText);
            }

            //若没有冒号后面的值(即值为空),则抛出异常
            if (value.isEmpty() && !keyValueText.contains(":")) {
                throw new KeyValueTextParseException("Can not found value of key: " + key, keyValueText);
            }

            resultMap.put(key, value);
        }

        //检查是否存在没有值的键(没有冒号的键值对)
        String[] pairs = keyValueText.split(",");
        for (String pair : pairs) {
            if (!pair.contains(":")) {
                throw new KeyValueTextParseException("Format incorrect: missing the separator \":\" at " + pair, keyValueText);
            }
        }

        return resultMap;
    }

    /**
     * 用于将map转为形如{a:b,c:d}这样的键值对文本
     * @param map 用于解析的map
     * @return 解析完成的文本
     */
    @Contract("null -> null; !null -> !null")
    public static String map2KeyValueText(Map<String, String> map) {
        //若map为null,返回null
        if (map == null) {
            return null;
        }

        //若map是空的,返回 "{}"
        if (map.isEmpty()) {
            return "{}";
        }

        StringBuilder result = new StringBuilder("{");

        //遍历map, 构建每个键值对
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            //处理值是否带有引号,若值中有空格或特殊字符,添加引号
            if (value.contains(" ") || value.contains(",") || value.contains(":")) {
                value = "\"" + value + "\"";
            }

            result.append(key).append(":").append(value).append(", ");
        }

        //去掉最后的多余的逗号和空格
        result.setLength(result.length() - 2);

        //加上尾部大括号
        result.append("}");

        return result.toString();
    }

    public static class KeyValueTextParseException extends RuntimeException {

        private final String keyValueText;

        public KeyValueTextParseException(String message, String keyValueText) {
            super(message);
            this.keyValueText = keyValueText;
        }

        public String getKeyValueText() {
            return keyValueText;
        }

    }
}
