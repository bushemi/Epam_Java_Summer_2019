package com.bushemi.dao.implementations;

import com.bushemi.dao.entity.dictionaries.Locale;
import com.bushemi.dao.interfaces.LocaleDao;
import com.bushemi.service.interfaces.DbConnectionService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
//@Primary
public class LocaleCachedDaoImpl implements LocaleDao {
    private Map<Long, Locale> localesById = new HashMap<>();
    private List<Locale> allLocales;
    private DbConnectionService dbConnector;

    public LocaleCachedDaoImpl(DbConnectionService dbConnector) {
        this.dbConnector = dbConnector;
        init();
    }

    private void init() {
        LocaleDao localeDao = new LocaleDaoImpl(dbConnector);
        allLocales = localeDao.findAll();
        allLocales.forEach(locale -> localesById.put(locale.getId(), locale));
    }

    @Override
    public Locale findById(Long id) {
        return localesById.get(id);
    }

    @Override
    public List<Locale> findAll() {
        return allLocales;
    }
}
