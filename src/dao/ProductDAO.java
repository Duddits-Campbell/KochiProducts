package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Product;

public class ProductDAO {
	private static final long serialVersionUID = 1L;
	private final String DRIVER_NAME = "org.h2.Driver";
    private final String JDBC_URL = "jdbc:h2:~/h2_db/kouchi";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

	public Product getProduct(int id) {
		String sql = "SELECT id, name, price, stock, description, image, status, category FROM products"
				+ " WHERE id = ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			result = statement.executeQuery();
			result.next();
			return new Product(
					result.getInt("id"),
					result.getString("name"),
					result.getInt("price"),
					result.getInt("stock"),
					result.getString("description"),
					result.getString("image"),
					result.getInt("status"),
					result.getString("category")
			);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			allClose(statement, connection);
		}
		return null;
	}

	public List<Product> getProducts() {
		String sql = "SELECT id, name, price, stock, description, image, status, category FROM products";
		List<Product> products = new ArrayList<>();
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);

			result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product(
						result.getInt("id"),
						result.getString("name"),
						result.getInt("price"),
						result.getInt("stock"),
						result.getString("description"),
						result.getString("image"),
						result.getInt("status"),
						result.getString("category")
				);
				// 11. リストにProductインスタンスを追加する
				products.add(product);
			}
			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			allClose(statement, connection);
		}
		return null;
	}

	public List<Product> getOpenProducts() {
		String sql = "SELECT id, name, price, stock, description, image, status, category FROM products WHERE status = 1";
		List<Product> products = new ArrayList<>();
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);

			result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product(
						result.getInt("id"),
						result.getString("name"),
						result.getInt("price"),
						result.getInt("stock"),
						result.getString("description"),
						result.getString("image"),
						result.getInt("status"),
						result.getString("category")
				);
				// 11. リストにProductインスタンスを追加する
				products.add(product);
			}
			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			allClose(statement, connection);
		}
		return null;
	}

	public List<Product> getProductsByKeyword(String keyword) {
		String sql = "SELECT id, name, price, stock, description, image, status, category FROM products"
				+ " WHERE name LIKE ? OR description LIKE ?";
		List<Product> products = new ArrayList<>();
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, "%" + keyword + "%");
			statement.setString(2, "%" + keyword + "%");
			result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product(
						result.getInt("id"),
						result.getString("name"),
						result.getInt("price"),
						result.getInt("stock"),
						result.getString("description"),
						result.getString("image"),
						result.getInt("status"),
						result.getString("category")
				);
				// 11. リストにProductインスタンスを追加する
				products.add(product);
			}
			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			allClose(statement, connection);
		}
		return null;
	}

	public List<Product> getProductsByCategory(String category) {
		String sql = "SELECT id, name, price, stock, description, image, status, category FROM products"
				+ " WHERE category = ?";
		List<Product> products = new ArrayList<>();
		Connection connection = null;
		ResultSet result = null;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, category);
			result = statement.executeQuery();
			while (result.next()) {
				Product product = new Product(
						result.getInt("id"),
						result.getString("name"),
						result.getInt("price"),
						result.getInt("stock"),
						result.getString("description"),
						result.getString("image"),
						result.getInt("status"),
						result.getString("category")
				);
				// 11. リストにProductインスタンスを追加する
				products.add(product);
			}
			return products;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			allClose(statement, connection);
		}
		return null;
	}

	public boolean insertProduct(
			String name,
			int price,
			int stock,
			String description,
			String image,
			int status,
			String category
	) {
		String sql = "INSERT INTO products (name, price, stock, description, image, status, category)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;
		int result = 0;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, stock);
			statement.setString(4, description);
			statement.setString(5, image);
			statement.setInt(6, status);
			statement.setString(7, category);
			result = statement.executeUpdate();
			if (result != 1) {
                return false;
            }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			allClose(statement, connection);
		}
		return true;
	}

	public boolean updateProduct(
			int id,
			String name,
			int price,
			int stock,
			String description,
			String image,
			int status,
			String category) {
		String sql = "UPDATE products SET name = ?, price = ?, stock = ?, description = ?, image = ?, status = ?, category = ? WHERE id = ? ";
		Connection connection = null;
		int result = 0;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setString(1, name);
			statement.setInt(2, price);
			statement.setInt(3, stock);
			statement.setString(4, description);
			statement.setString(5, image);
			statement.setInt(6, status);
			statement.setString(7, category);
			statement.setInt(8, id);
			result = statement.executeUpdate();
			if (result != 1) {
                return false;
            }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			allClose(statement, connection);
		}
		return true;
	}

	public boolean deleteProduct(int id) {
		String sql = "DELETE FROM products WHERE id = ? ";
		Connection connection = null;
		int result = 0;
		PreparedStatement statement = null;
		try {
			connection = connect();
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			result = statement.executeUpdate();
			if (result != 1) {
                return false;
            }
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			allClose(statement, connection);
		}
		return true;
	}

	private void allClose(PreparedStatement statement, Connection connection) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	private Connection connect() throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER_NAME);
		return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}
}
