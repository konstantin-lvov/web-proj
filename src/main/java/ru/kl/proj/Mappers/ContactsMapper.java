package ru.kl.proj.Mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kl.proj.entity.Contacts;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsMapper implements RowMapper<Contacts> {
    @Override
    public Contacts mapRow(ResultSet resultSet, int i) throws SQLException {
        Contacts contacts = new Contacts();
        contacts.setOid(resultSet.getInt("oid"));
        contacts.setCid(resultSet.getInt("cid"));
        String contact = resultSet.getString("name") + " " +
                resultSet.getString("second_name") + " " +
                resultSet.getString("third_name") + " " +
                resultSet.getString("phone_number");
        String expression = "\\w+\\s\\w+\\s\\w+\\s\\w+";
        Pattern pattern = Pattern.compile(expression, Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(contact);
        if(matcher.matches()){
            contacts.setContact(contact);
        } else {
            contacts.setContact("");
        }
        return contacts;
    }
}
