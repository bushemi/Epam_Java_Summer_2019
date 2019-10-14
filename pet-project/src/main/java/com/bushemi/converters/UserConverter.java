package com.bushemi.converters;

import com.bushemi.dao.entity.User;
import com.bushemi.dao.entity.dictionaries.Locale;
import com.bushemi.dao.entity.dictionaries.Role;
import com.bushemi.dao.implementations.LocaleCachedDaoImpl;
import com.bushemi.dao.interfaces.LocaleDao;
import com.bushemi.dao.implementations.RoleCachedDaoImpl;
import com.bushemi.dao.interfaces.RoleDao;
import com.bushemi.model.UserCreatingDto;
import com.bushemi.model.UserForSessionDto;
import com.bushemi.service.implementations.DbConnectionPoolServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    private LocaleDao localeDao ;
    private RoleDao roleDao;

    public UserConverter(LocaleDao localeDao, RoleDao roleDao) {
        this.localeDao = localeDao;
        this.roleDao = roleDao;
    }

    private static final long RU = 1L;
    private static final long STUDENT = 1L;

    public User userCreatingToUser(UserCreatingDto userCreatingDto) {
        User user = new User();
        user.setLogin(userCreatingDto.getLogin());
        user.setPassword(userCreatingDto.getPassword());
        user.setRoleId(STUDENT);
        user.setLocaleId(RU);
        user.setBlocked(false);

        return user;
    }

    public UserForSessionDto userToUserForSessionDto(User userFromDb) {
        UserForSessionDto user = new UserForSessionDto();
        user.setId(userFromDb.getId());
        user.setLogin(userFromDb.getLogin());
        user.setBlocked(userFromDb.isBlocked());
        Role role = roleDao.findById(userFromDb.getRoleId());
        user.setRole(role);
        Locale locale = localeDao.findById(userFromDb.getLocaleId());
        user.setLocale(locale);

        return user;
    }

}
