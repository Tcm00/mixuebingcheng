package com.example.milktea.utils;

import org.apache.ibatis.type.DoubleTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 * @author 小明
 * @date 2022/5/30
 * @description
 */
public class TypeHander extends DoubleTypeHandler {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Double parameter, JdbcType jdbcType)
            throws SQLException {
        ps.setDouble(i, parameter);
    }

    @Override
    public Double getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return toDoubleTwoFormat(rs.getDouble(columnName));
    }

    @Override
    public Double getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return toDoubleTwoFormat(rs.getDouble(columnIndex));
    }

    @Override
    public Double getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return toDoubleTwoFormat(cs.getDouble(columnIndex));
    }


    private Double toDoubleTwoFormat(double d) {
        DecimalFormat decimalFormat=new DecimalFormat(".00");
        return Double.parseDouble(decimalFormat.format(d));
    }
}
