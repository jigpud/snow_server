package com.jigpud.snow.util.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author jigpud
 */
@MappedTypes(List.class)
@MappedJdbcTypes(JdbcType.LONGVARCHAR)
public class StringListTypeHandler extends BaseTypeHandler<List<String>> {
    private static final String SEPARATOR = ",";

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, listToString(parameter));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return stringToList(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return stringToList(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return stringToList(cs.getString(columnIndex));
    }

    private List<String> stringToList(String str) {
        List<String> list = new ArrayList<>();
        if (str != null) {
            Arrays.stream(str.split(SEPARATOR))
                    .sequential()
                    .filter(s -> s != null && !s.isEmpty())
                    .forEach(list::add);
        }
        return list;
    }

    private String listToString(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        if (list != null) {
            for (int index = 0; index < list.size(); index++) {
                String str = list.get(index);
                stringBuilder.append(str);
                if (index != list.size() - 1) {
                    stringBuilder.append(SEPARATOR);
                }
            }
        }
        return stringBuilder.toString();
    }
}
