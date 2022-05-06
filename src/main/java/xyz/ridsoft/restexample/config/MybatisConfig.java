package xyz.ridsoft.restexample.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "xyz.ridsoft.restexample.dao")
public class MybatisConfig {

    @Value("${spring.datasource.mapper-locations}")
    String path;

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext context) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();

        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("xyz.ridsoft.restexample.dto");
        sqlSessionFactory.setMapperLocations(context.getResources(path));

        return sqlSessionFactory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {

        return new SqlSessionTemplate(sqlSessionFactory);
    }
}