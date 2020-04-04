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
        contacts.setCid(resultSet.getInt("cid"));
        contacts.setContact(resultSet.getString("name") + " " +
                resultSet.getString("second_name") + " " +
                resultSet.getString("third_name") + " " +
                resultSet.getString("phone_number"));
        return contacts;
    }
}
