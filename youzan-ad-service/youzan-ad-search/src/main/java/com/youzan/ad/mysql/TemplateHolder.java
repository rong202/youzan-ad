package com.youzan.ad.mysql;

import com.alibaba.fastjson.JSON;
import com.youzan.ad.mysql.constant.OpType;
import com.youzan.ad.mysql.dto.ParseTemplate;
import com.youzan.ad.mysql.dto.TableTemplate;
import com.youzan.ad.mysql.dto.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

/**
 * Created by baimugudu on 2019/4/11
 */
@Component
public class TemplateHolder {

    private ParseTemplate parseTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String sql = "SELECT\n" +
            "\ttable_schema,\n" +
            "\ttable_name,\n" +
            "\tcolumn_name,\n" +
            "\tordinal_position\n" +
            "FROM\n" +
            "\tinformation_schema.`COLUMNS`\n" +
            "WHERE\n" +
            "\tTABLE_SCHEMA = ?\n" +
            "AND TABLE_NAME = ?";

    @PostConstruct
    private  void init(){
        loadJson("template.json");
    }

    private void loadJson(String path){

        InputStream resourceAsStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(path);


        try {
            Template template =  JSON.parseObject(
                    resourceAsStream,
                    Charset.defaultCharset(),
                    Template.class
            );
            this.parseTemplate = ParseTemplate.parse(template);

            meteData();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void meteData(){
        for(Map.Entry<String, TableTemplate> entry:
                parseTemplate.getTableTemplateMap().entrySet()){
            TableTemplate table =  entry.getValue();


            List<String> updateFildes =
                    table.getOpTypeFieldMap().get(OpType.UPDATE);

            List<String> addFildes =
                    table.getOpTypeFieldMap().get(OpType.ADD);

            List<String> deleteFildes =
                    table.getOpTypeFieldMap().get(OpType.DELETE);



            jdbcTemplate.query(
                    sql,
                    new Object[]{
                         parseTemplate.getDatabase(),
                         table.getTableName()
                    },
                    (rs,i )->{
                        int pos = rs.getInt("ordinal_position");
                        String colName =  rs.getString("column_name");

                        if(null!=updateFildes&&updateFildes.contains(colName)
                        || null!=addFildes && addFildes.contains(colName)
                        || null!=deleteFildes && addFildes.contains(colName)){
                            table.getPosMap().put(pos-1,colName);
                        }


                        return null;
                    }
            );
        }
    }





}
