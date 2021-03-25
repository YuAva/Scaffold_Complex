package cn.edu.bupt.hover.scaffold.service;

import cn.edu.bupt.hover.scaffold.entity.Person;
import cn.edu.bupt.hover.scaffold.mapper.mysql.MySqlKeepMapper;
import cn.edu.bupt.hover.scaffold.mapper.neo4j.Neo4jKeepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {
    @Autowired
    private MySqlKeepMapper mySqlKeepMapper;

    @Autowired
    private Neo4jKeepMapper neo4jKeepMapper;

    @Transactional
    public void testRollBack() {
        mySqlKeepMapper.insertOnePerson(new Person("Liu Xiangyu"));
        neo4jKeepMapper.insertOnePerson();
    }
}
