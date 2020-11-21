
package com.company.dao.inter;

import com.company.entity.User;
import java.util.List;

public interface UserDaoInter {

    public List<User> getAll(String name, String surname, Integer nationalityId);

    public List<User> getAll();

    public User getById(int userId);


    public boolean update(User u);

    public boolean add(User u);

    public boolean delete(User user);

    public boolean deleteById(int id);
}
