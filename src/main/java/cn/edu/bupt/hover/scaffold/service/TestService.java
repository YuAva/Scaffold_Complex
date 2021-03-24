package cn.edu.bupt.hover.scaffold.service;

import cn.edu.bupt.hover.scaffold.mapper.mysql.MySqlKeepMapper;
import cn.edu.bupt.hover.scaffold.mapper.neo4j.Neo4jKeepMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    private MySqlKeepMapper mySqlKeepMapper;

    @Autowired
    private Neo4jKeepMapper neo4jKeepMapper;

    
}
