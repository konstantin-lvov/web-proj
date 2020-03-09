package ru.kl.proj.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import ru.kl.proj.Mappers.UserMapper;
import ru.kl.proj.entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getAllUsers() {
        String sql = "select * from public.users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    @Override
    public void createUser(User user) {
        if(user.getAuthority()==null){
            user.setAuthority("ROLE_USER");
        }
        user.setEnabled(true);

        System.out.println(user.getUsername() + " " + user.getPassword() + " " +
                user.getAuthority() + " " + user.isEnabled());

        String sql = "insert into public.users (username, password, enabled) values(?, ?, ?)";
        int tmpa =jdbcTemplate.update(sql, user.getUsername(), user.getPassword(), user.isEnabled());

        System.out.println(tmpa + " first query");

        System.out.println(user.getUsername() + " " + user.getPassword() + " " +
                user.getAuthority() + " " + user.isEnabled());

        sql = "insert into public.authorities (username, authority) values(?, ?)";
        int tmp = jdbcTemplate.update(sql, user.getUsername(), user.getAuthority());

        System.out.println(tmp + " second query");
    }

    @Override
    public User readUser(String username) {
        String sql = "select u.username, u.password, u.enabled, a.authority " +
                "from public.users u, public.authorities a where u.username = ? " +
                "and u.username = a.username";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
    }

    @Override
    public void updateUser(User user) {
        String sql = "update public.users set password = ?, enabled = ? where username = ?";
        jdbcTemplate.update(sql, user.getPassword(), user.isEnabled(), user.getUsername());
        sql = "update public.authorities set authority = ? where username = ?";
        jdbcTemplate.update(sql, user.getAuthority(), user.getUsername());
    }

    @Override
    public void deleteUser(String username) {
        String sql = "delete from public.users where username = ?";
        jdbcTemplate.update(sql, username);
    }
}
