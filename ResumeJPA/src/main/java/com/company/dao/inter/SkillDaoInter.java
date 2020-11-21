
package com.company.dao.inter;

import com.company.entity.Skill;
import java.util.List;

public interface SkillDaoInter {

    public List<Skill> getAll();

    public Skill getById(int id);

    public boolean add(Skill skill);

    public boolean update(Skill skill);

    public boolean delete(Skill skill);

    public boolean deleteById(int id);
}
