package by.kotik.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import by.kotik.bean.User;
import by.kotik.dao.AdminDAO;
import by.kotik.dao.DAOException;
import by.kotik.dao.connectionpool.ConnectionPollException;
import by.kotik.dao.connectionpool.ConnectionPool;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLAdminDAO implements AdminDAO {
    private final String GET_ALL_USERS = "select * from user";
    private final String GET_USER_BY_ID = "select * from user where iduser=?";
    private final String DELETE_USER_BY_ID = "delete from user where iduser=?";
    private final String UPDATE_USER_BY_ID = "update user set username=?, password=?, balance=? where iduser=?";

    private final String DELETE_FROM_ORDER_LIST = "delete from order_list where idorder=?";
    private final String SELECT_USER_ORDERS = "select idorder from user_orders where iduser=?";
    private final String DELETE_FROM_USER_ORDERS = "delete from user_orders where iduser=?";
    private final String ADD_USER = "insert into user (username, password, balance, is_admin) values(?, ?, ?, ?)";

    private final String ID = "iduser";
    private final String USERNAME = "username";
    private final String BALANCE = "balance";
    private final String IS_ADMIN = "is_admin";

    private final String ID_ORDER = "idorder";

    @Override
    public boolean deleteUser(int id) throws DAOException {
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID)
                ) {
            preparedStatement.setInt(1, id);

            deleteOrders(id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DAOException{
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                ) {

            PreparedStatement preparedStatement;

            if (user.getPassword().isEmpty()) {
                preparedStatement = connection.prepareStatement("update user set username=?, balance=? where iduser=?");
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setBigDecimal(2, user.getBalance());
                preparedStatement.setInt(3, user.getId());
            } else {
                String hashPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
                user.setPassword(hashPassword);

                preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setBigDecimal(3, user.getBalance());
                preparedStatement.setInt(4, user.getId());
            }



            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DAOException{
        List<User> users = new ArrayList<>();

        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);
            ) {

            while (resultSet.next()) {
                boolean is_admin = resultSet.getBoolean(IS_ADMIN);

                if (!is_admin) {
                    int id = resultSet.getInt(ID);
                    String username = resultSet.getString(USERNAME);
                    BigDecimal balance = resultSet.getBigDecimal(BALANCE);

                    users.add(new User(id, username, balance));
                }
            }

        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }

        return users;
    }

    @Override
    public User getUserById(int id) throws DAOException {
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
                ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            int idUser;
            String username;
            BigDecimal balance;
            User user = new User();

            while (resultSet.next()) {
                idUser = resultSet.getInt(ID);
                username = resultSet.getString(USERNAME);
                balance = resultSet.getBigDecimal(BALANCE);

                user = new User(idUser, username, balance);
            }

            return user;
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
    }

    private boolean deleteOrders(int id) throws DAOException {
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();

                ) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_ORDERS);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id_order = resultSet.getInt(ID_ORDER);
                preparedStatement = connection.prepareStatement(DELETE_FROM_ORDER_LIST);
                preparedStatement.setInt(1, id_order);

                preparedStatement.executeUpdate();
            }

            preparedStatement = connection.prepareStatement(DELETE_FROM_USER_ORDERS);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

            return true;

        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean addUser(User user) throws DAOException{
        try (
                ConnectionPool connectionPool = ConnectionPool.getInstance();
                Connection connection = connectionPool.takeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
                ) {
            String hashPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(hashPassword);

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setBigDecimal(3, user.getBalance());
            preparedStatement.setBoolean(4, user.isAdmin());

            preparedStatement.executeUpdate();
        } catch (SQLException | ConnectionPollException e) {
            throw new DAOException(e);
        }

        return false;
    }
}
