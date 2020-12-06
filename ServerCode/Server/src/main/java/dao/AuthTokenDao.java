package dao;

import com.couchbase.client.core.error.CouchbaseException;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryResult;
import model.AuthToken;

import java.sql.SQLException;

import static com.couchbase.client.java.query.QueryOptions.queryOptions;

/**
 * AuthToken Data Access Operator
 */
public class AuthTokenDao {

    private static final dao.DataBase db = DataBase.getInstance();

    /**
     * AuthTokenDao Constructor
     */
    public AuthTokenDao()
    {

    }

    /**
     * check to see if the given username exists in the auth table of the database
     * @param token the auth token
     * @return the object found, otherwise null
     */
    public static AuthToken find(String token) throws SQLException
    {
        AuthToken auth = null;
        try
        {
            Bucket b = CouchBaseDB.getInstance().getBucket("Auth");
            Collection collection = b.defaultCollection();
            GetResult getResult = collection.get(token);
            if (getResult != null)
            {
                auth = new AuthToken(getResult.contentAsObject().getString("userID"), token);
            }
        }
        catch (CouchbaseException e)
        {
            System.err.println("Auth has a problem finding: " + e.getMessage());
            return null;
        }
        return auth;
    }

//    /**
//     * check to see if the given auth token already exists
//     * if not, then add the given auth token to the database
//     * @param authToken the auth token
//     * @return true if the authToken was valid and was added
//     */
//    public static void insert(AuthToken authToken) throws SQLException
//    {
//        String update = "INSERT INTO Auth (Username, Token) VALUES (?, ?)";
//        PreparedStatement stmt = db.getPreparedStatement(update);
//        stmt.setString(1, authToken.getUsername());
//        stmt.setString(2, authToken.getToken());
//        db.executeUpdate(stmt);
//    }

    /**
     * check to see if the given auth token already exists
     * if not, then add the given auth token to the database
     * @param authToken the auth token
     * @return true if the authToken was valid and was added
     */
    public static void insert(AuthToken authToken) throws SQLException
    {
        try
        {
            Bucket b = CouchBaseDB.getInstance().getBucket("Auth");
            Collection collection = b.defaultCollection();
            collection.insert(
                    authToken.getToken(),
                    JsonObject.create().put("userID", authToken.getUsername())
            );
        }
        catch (CouchbaseException e)
        {
            System.err.println("Auth has a problem inserting: " + e.getMessage());
        }
    }

    /**
     * checks to see if the given auth token exists
     * if it does, then remove it
     * @param username username of the auth token
     * @return true if the auth token existed and was removed, otherwise false
     */
    public static void remove(String username) throws SQLException
    {
        try
        {
            Bucket b = CouchBaseDB.getInstance().getBucket("Auth");
            Collection collection = b.defaultCollection();
            QueryResult result = CouchBaseDB.query("select * from Auth where userID = ?", queryOptions().parameters(JsonArray.from(username)));
            if (result != null)
            {
                collection.remove(result.rowsAsObject().get(0).getString("token"));
            }
        }
        catch (CouchbaseException e)
        {
            System.err.println("Auth has a problem removing: " + e.getMessage());
        }
    }

    // validates the given token and returns a boolean as the result
    public static boolean validate(String token) throws SQLException
    {
        AuthToken auth = find(token);
        if (auth != null)
        {
            return true;
        }

        return false;
    }

    //clears Auth table in database
    public static void clear() throws SQLException
    {
        String update = "DELETE FROM Auth";
        db.executeUpdate(db.getPreparedStatement(update));
    }
}
