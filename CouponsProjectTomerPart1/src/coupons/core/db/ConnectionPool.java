package coupons.core.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import coupons.core.exceptions.ConnectionPoolException;

/**
 * Singleton - Manages a pool of connection to the database.
 */
public class ConnectionPool {
	private static ConnectionPool instance;
	private Set<Connection> connections = new HashSet<Connection>();
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private int size;
	private boolean closing;

	/**
	 * Read properties from files/app.properties and initializes the connection
	 * pool.
	 * 
	 * @throws ConnectionPoolException If an error occurred when reading from the
	 *                                 input stream.
	 * @throws ConnectionPoolException If a database access error occurs or the url
	 *                                 is null.
	 */
	private ConnectionPool() throws ConnectionPoolException {
		Properties properties = new Properties();
		File file = new File("files/app.properties");
		try (FileInputStream fin = new FileInputStream(file)) {
			properties.load(fin);
			this.dbUrl = properties.getProperty("db.url");
			this.dbUser = properties.getProperty("db.user");
			this.dbPassword = properties.getProperty("db.password");
			this.size = Integer.parseInt(properties.getProperty("db.conpool.size"));
		} catch (IOException e) {
			throw new ConnectionPoolException("ConnectionPool could not load properties", e);
		}
		// initialize pool
		try {
			for (int i = 0; i < size; i++) {
				Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				this.connections.add(con);
			}
//	    System.out.println("DEBUG ConnectionPool is initialized with " + this.connections.size() + " connections");
		} catch (SQLException e) {
			throw new ConnectionPoolException("ConnectionPool could not initialize", e);
		}
	}

	/**
	 * Create the connection pool instance if it's null and return it.
	 * 
	 * @return The ConnectionPool instance
	 * @throws ConnectionPoolException
	 */
	public static ConnectionPool getInstance() throws ConnectionPoolException {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	/**
	 * Return a connection from the pool to the caller, waits if there are no
	 * connections in the pool.
	 * 
	 * @return A connection to the database.
	 * @throws ConnectionPoolException If getConnection is called while trying to
	 *                                 close the connection pool.
	 */
	public synchronized Connection getConnection() throws ConnectionPoolException {
		// check if the connection pool is trying to close
		if (this.closing) {
			throw new ConnectionPoolException("could not get connection while closing the connection pool");
		}
		// wait if the connection pool is empty
		while (this.connections.isEmpty()) {
			try {
//		System.out.println("DEBUG getConnection is waiting");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// get a connection and return it
		Iterator<Connection> it = this.connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	/**
	 * Return a given connection to the pool and notify a connection has been added.
	 * 
	 * @param con the connection to return to the pool
	 */
	public synchronized void restoreConnection(Connection con) {
		// check if the pool is not full
		if (this.connections.size() < this.size) {
			this.connections.add(con);
//	    System.out.println("DEBUG restoreConnection is notifying");
			notify();
		}
	}

	/**
	 * Wait until all connections return to the pool and close it.
	 * 
	 * @throws ConnectionPoolException if a database access error occurs when
	 *                                 closing the connections.
	 */
	public synchronized void closeAllConnections() throws ConnectionPoolException {
		// don't let get connections
		this.closing = true;
		// wait until all the connections are back
		while (this.connections.size() < this.size) {
			try {
//		System.out.println("DEBUG closeAllConnections is waiting for the connections to return");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// iterate over connections and close them
		Iterator<Connection> it = this.connections.iterator();
		while (it.hasNext()) {
			Connection con = it.next();
			try {
				con.close();
			} catch (SQLException e) {
				throw new ConnectionPoolException("could not close connections to the database", e);
			}
		}
//	System.out.println("DEBUG successfully closed all connections to the database");
	}

}
