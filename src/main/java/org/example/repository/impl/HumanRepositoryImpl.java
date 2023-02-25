package org.example.repository.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.example.constants.SqlQuery;
import org.example.entity.Car;
import org.example.entity.Human;
import org.example.repository.HumanRepository;
import org.simpleflatmapper.jdbc.Crud;
import org.simpleflatmapper.jdbc.CrudDSL;
import org.simpleflatmapper.jdbc.JdbcMapperFactory;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateCrud;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.jdbc.core.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.apache.commons.collections4.CollectionUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.constants.SqlQuery.*;
import static org.example.constants.TableName.carTable;
import static org.example.constants.TableName.humanTable;

@Repository
@RequiredArgsConstructor
public class HumanRepositoryImpl implements HumanRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Human> findAll() {
        return Optional.ofNullable(jdbcTemplate.query(getHumansQuery,
                        JdbcTemplateMapperFactory
                                .newInstance()
                                .addKeys("id")
                                .newResultSetExtractor(Human.class)))
                .orElse(Collections.emptyList())
                .stream()
                .peek(human -> human.getCars().removeIf(car -> Objects.isNull(car.getId())))
                .toList();
    }

    @Override
    public Optional<Human> findById(Long id) {
        return Objects.requireNonNull(jdbcTemplate.query(getHumansByIdQuery,
                        ps -> ps.setLong(1, id),
                        JdbcTemplateMapperFactory
                                .newInstance()
                                .addKeys("id")
                                .newResultSetExtractor(Human.class)))
                .stream()
                .peek(human -> human.getCars().removeIf(car -> Objects.isNull(car.getId())))
                .findFirst();
    }

    @Override
    public List<Human> findByName(String name) {
        return Objects.requireNonNull(jdbcTemplate.query(con -> con.prepareStatement(getHumansByNameQuery),
                        ps -> ps.setString(1, name),
                        JdbcTemplateMapperFactory
                                .newInstance()
                                .addKeys("id")
                                .newResultSetExtractor(Human.class)))
                .stream()
                .peek(human -> human.getCars().removeIf(car -> Objects.isNull(car.getId())))
                .toList();
    }

    @Override
    public Human updateBalance(Human human) {
        jdbcTemplate.update(
                "UPDATE human SET balance = ? where id = ?",
                ps -> {
                    ps.setBigDecimal(1, human.getBalance());
                    ps.setLong(2, human.getId());
                });
        return human;
    }

    @Override
    public Long count() {
        return jdbcTemplate.queryForObject(countQuery, Long.class);
    }

    @SneakyThrows
    @Override
    public Human save(@NonNull Human human) {
        var humanCrudForCreateAndUpdate = JdbcTemplateMapperFactory
                .newInstance()
                .crud(Human.class, Long.class).to(jdbcTemplate, humanTable);
        var carCrud = JdbcTemplateMapperFactory
                .newInstance()
                .crud(Car.class, Long.class).to(jdbcTemplate, carTable);

        if (Objects.isNull(human.getId())) {
            humanCrudForCreateAndUpdate.create(human);
            var id = jdbcTemplate.queryForObject(findMaxHumanIdQuery, Long.class);
            if (CollectionUtils.isNotEmpty(human.getCars()))
                carCrud.create(human.getCars()
                        .stream()
                        .peek(car -> car.setHumanId(id))
                        .toList());
        } else {
            humanCrudForCreateAndUpdate.update(human);
            if (CollectionUtils.isNotEmpty(human.getCars())) {
                carCrud.update(human.getCars());
            }
        }

        return human;
    }
}
