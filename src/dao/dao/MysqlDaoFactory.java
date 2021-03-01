package dao.dao;

public class MysqlDaoFactory extends DaoFactory {

    @Override
    public BookDao getBookDao() {
        return new MysqlBookDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MysqlUserDao();
    }

}