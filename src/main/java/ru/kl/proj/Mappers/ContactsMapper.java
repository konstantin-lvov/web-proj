package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Contacts;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsMapper implements RowMapper<Contacts> {
    @Override
    public Contacts mapRow(ResultSet resultSet, int i) throws SQLException {
        Contacts contacts = new Contacts();
        contacts.setOid(resultSet.getInt("oid"));
        contacts.setName(resultSet.getString("name"));
        contacts.setSecondName(resultSet.getString("secondName"));
        contacts.setThirdName(resultSet.getString("thirdName"));
        contacts.setPhoneNumber(resultSet.getString("phoneNumber"));
        return contacts;
    }
}
