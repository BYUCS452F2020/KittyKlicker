package service;

import com.couchbase.client.core.error.CouchbaseException;
import dao.AuthTokenDao;
import dao.CouchBaseDB;
import dao.DataBase;
import model.AuthToken;

import java.sql.SQLException;
import java.util.UUID;

public class AuthService
{
    private static DataBase db = DataBase.getInstance();

    public AuthService()
    {

    }

    public static AuthToken generate(String username) throws SQLException
    {
        AuthTokenDao.remove(username);
        AuthToken auth = new AuthToken(username, UUID.randomUUID().toString().substring(0, 8));
        AuthTokenDao.insert(auth);
        return auth;
    }

    public static boolean validate(String token)
    {
        try
        {
            CouchBaseDB.openConnection();
            return AuthTokenDao.validate(token);
        }
        catch (SQLException e)
        {
            return false;
        }
        finally
        {
            try
            {
                CouchBaseDB.closeConnection();
            }
            catch (CouchbaseException e)
            {
                return false;
            }
        }
    }
}
