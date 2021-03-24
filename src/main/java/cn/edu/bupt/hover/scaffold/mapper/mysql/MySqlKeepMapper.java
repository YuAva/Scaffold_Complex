package cn.edu.bupt.hover.scaffold.mapper.mysql;

import cn.edu.bupt.hover.scaffold.entity.Person;
import org.apache.ibatis.annotations.Param;

public interface MySqlKeepMapper {
    int insertOnePerson(@Param("person") Person person);
}
